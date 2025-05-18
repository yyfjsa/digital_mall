package com.cow.controller;

import com.alibaba.fastjson.JSON;
import com.cow.entity.Logistics;
import com.cow.entity.Order;
import com.cow.entity.Product;
import com.cow.service.LogisticsService;
import com.cow.service.OrderService;
import com.cow.service.ProductService;
import com.cow.util.general.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;



@RestController
@CrossOrigin
public class OrderController {
    private static final String COLLECT_GOODS_STATE = "已收货";

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private LogisticsService logisticsService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping(value = "/order/findById")
    public CommonResult findOrderById(Integer orderId) {
        Order order = orderService.selectById(orderId);
        if (orderId != null) {
            return CommonResult.success("订单信息查询成功", order);
        }
        return CommonResult.error("订单信息查询失败");
    }

    @RequestMapping(value = "/order/findOrderInfo")
    public CommonResult findOrderInfo(String userAccount) {
        List<Map<String, Object>> orderMap = orderService.selectAllOrder(userAccount);
        if (orderMap != null) {
            return CommonResult.success("订单信息查询成功", orderMap);
        }
        return CommonResult.error("订单信息查询失败");
    }

    @RequestMapping(value = "/order/findAll")
    public CommonResult findAllOrder() {
        List<Order> orders = orderService.selectAll();
        if (orders != null) {
            return CommonResult.success("订单信息查询成功", orders);
        }
        return CommonResult.error("订单信息查询失败");
    }

    @RequestMapping(value = "/order/findCount")
    public CommonResult findCount() {
        Integer count = orderService.selectCount();
        if (count != null) {
            return CommonResult.success("订单数量查询成功", count);
        }
        return CommonResult.error("订单数量查询失败");
    }

    @PostMapping(value = "/order/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public CommonResult addOrder(@ModelAttribute Order order) {
        if (order != null) {
            return handleMerchandiseOrders(order);
        } else {
            return CommonResult.error("订单数据不完整");
        }
    }

    @RequestMapping(value = "/order/cartOrder")
    public CommonResult cartOrder(String orderNo, String ordersInfo, String cartIds) {
        try {
            List<String> cartIdList = JSON.parseArray(cartIds, String.class);
            List<Order> orders = JSON.parseArray(ordersInfo, Order.class);
            if (CollectionUtils.isEmpty(orders)) {
                return CommonResult.error("订单数据不完整");
            }

            List<String> orderInfo = new ArrayList<>();
            List<String> productInfo = new ArrayList<>();
            for (Order order : orders) {
                // 使用乐观锁扣减库存
                int affectedRows = productService.reduceStock(
                        order.getProductNo(),
                        order.getPayAmount()
                );
                if (affectedRows == 0) {
                    throw new RuntimeException("商品库存不足: " + order.getProductNo());
                }
                // 创建订单
                if (!orderService.insertData(order)) {
                    throw new RuntimeException("订单创建失败: " + order.getOrderNo());
                }
                orderInfo.add(order.getOrderNo());
                productInfo.add(order.getProductNo());
            }

            // 缓存关联信息
            String cartIdsInfo = String.join(",", cartIdList);
            String orderNoInfo = String.join(",", orderInfo);
            redisTemplate.opsForValue().set(
                    "order:sub:" + orderNo,
                    orderNoInfo,
                    1, TimeUnit.HOURS
            );
            redisTemplate.opsForValue().set(
                    "cart:order:" + orderNo,
                    cartIdsInfo,
                    1, TimeUnit.HOURS
            );
            return CommonResult.success("创建订单成功", String.join(",", productInfo));
        } catch (Exception e) {
            // 事务回滚会自动恢复库存
            return CommonResult.error("创建订单失败: " + e.getMessage());
        }
    }

    @RequestMapping(value = "/order/update")
    public CommonResult updateOrder(Order order) {
        if (orderService.updateById(order)) {
            return CommonResult.success("修改订单成功", order);
        }
        return CommonResult.error("修改订单失败");
    }

    @RequestMapping(value = "/order/delete")
    public CommonResult deleteOrder(Integer orderId) {
        if (orderService.deleteById(orderId)) {
            return CommonResult.success("删除订单成功", "订单id：" + orderId);
        }
        return CommonResult.error("删除订单失败");
    }

    @RequestMapping(value = "/order/receipt")
    public CommonResult updateOrder(Integer orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderState(COLLECT_GOODS_STATE);
        if (orderService.updateById(order)) {
            return CommonResult.success("商品收货成功", order);
        }
        return CommonResult.error("商品收货失败");
    }

    @RequestMapping(value = "/orderDetail/orderInfo")
    public CommonResult orderInfo(String orderNo) {
        ArrayList<Object> resultList = new ArrayList<>();
        Order order = orderService.selectByKey(orderNo);
        Logistics logistics = logisticsService.selectOrderNo(orderNo);
        if (order != null) {
            resultList.add(order);
        }
        if (logistics != null) {
            resultList.add(logistics);
        }
        return CommonResult.success("订单详情查询成功", resultList);
    }
    /**
     * 处理商品订单
     *
     * @param order 订单信息
     */
    private CommonResult handleMerchandiseOrders(Order order) {
        try {
            // 基础校验（保持不变）
            if (order == null) {
                return CommonResult.error("订单数据无效");
            }
            Product product = productService.selectByKey(order.getProductNo());
            if (product == null) {
                return CommonResult.error("商品未找到");
            }

            // 乐观锁扣减库存（替换原有逻辑）
            int affectedRows = productService.reduceStock(
                    order.getProductNo(),
                    order.getPayAmount()
            );
            if (affectedRows == 0) {
                return CommonResult.error("商品库存不足或并发冲突"); // 失败说明库存不足或并发冲突
            }

            // 创建订单（原有逻辑）
            if (orderService.insertData(order)) {
                redisTemplate.opsForValue().set(order.getOrderNo(), order.getOrderNo(), 24, TimeUnit.HOURS);
                return CommonResult.success("创建订单成功", order);
            } else {
                throw new RuntimeException("订单创建失败"); // 触发事务回滚
            }
        } catch (Exception e) {
            throw new RuntimeException("处理订单失败", e); // 触发事务回滚
        }
    }

}
