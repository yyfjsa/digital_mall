/*
 Navicat Premium Dump SQL

 Source Server         : jsa
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44-log)
 Source Host           : localhost:3306
 Source Schema         : digital_mall

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44-log)
 File Encoding         : 65001

 Date: 18/05/2025 19:28:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `banner_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品广告牌id',
  `product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品链接',
  `banner_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告宣传栏链接',
  PRIMARY KEY (`banner_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品广告轮播图' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for inf_file
-- ----------------------------
DROP TABLE IF EXISTS `inf_file`;
CREATE TABLE `inf_file`  (
  `id` varchar(188) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `type` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `content` longblob NULL COMMENT '文件内容',
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '是否删除'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for logistics
-- ----------------------------
DROP TABLE IF EXISTS `logistics`;
CREATE TABLE `logistics`  (
  `logistic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '物流id',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `sender` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货人',
  `sender_tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货人联系方式',
  `sender_add` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发货人联系地址',
  `receiver` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `receiver_tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人联系方式',
  `receiver_add` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人联系地址',
  `parcel_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物流公司',
  PRIMARY KEY (`logistic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物流表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `product_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品编号',
  `product_specs` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品规格',
  `user_account` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `contact_way` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `pay_price` double NOT NULL COMMENT '商品金额',
  `pay_amount` int(11) NOT NULL COMMENT '购买数量',
  `pay_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付方式',
  `order_from` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单来源',
  `order_state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态',
  `accept_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址',
  `return_state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '退货状态',
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `order_no`(`order_no`) USING BTREE,
  INDEX `product_no`(`product_no`) USING BTREE,
  INDEX `order_ibfk_2`(`user_account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21083 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品编号',
  `product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类别',
  `product_describe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '副标题',
  `product_brand` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌',
  `in_price` double NOT NULL COMMENT '进价',
  `out_price` double NOT NULL COMMENT '售价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `lowest_stock` int(11) NOT NULL COMMENT '最低库存',
  `is_stockout` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否缺货',
  `is_new` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否新品',
  `is_sale` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否上架',
  `sale_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上架时间',
  `product_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品图片',
  PRIMARY KEY (`product_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`product_no`) USING BTREE,
  INDEX `product_type`(`product_type`) USING BTREE,
  INDEX `product_brand`(`product_brand`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for product_brand
-- ----------------------------
DROP TABLE IF EXISTS `product_brand`;
CREATE TABLE `product_brand`  (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `brand_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌名称',
  `brand_describe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌描述',
  PRIMARY KEY (`brand_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`brand_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品品牌表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for product_review
-- ----------------------------
DROP TABLE IF EXISTS `product_review`;
CREATE TABLE `product_review`  (
  `review_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户评论id',
  `account_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户帐号',
  `product_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品编号',
  `product_specs` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品规格',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间',
  `star_level` double NOT NULL COMMENT '商品评星',
  `product_review` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品评价',
  PRIMARY KEY (`review_id`) USING BTREE,
  INDEX `account_number`(`account_number`) USING BTREE,
  INDEX `product_no`(`product_no`) USING BTREE,
  INDEX `order_no`(`order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品评价' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for product_specs
-- ----------------------------
DROP TABLE IF EXISTS `product_specs`;
CREATE TABLE `product_specs`  (
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `specs_id` int(11) NOT NULL COMMENT '规格id',
  INDEX `product_id`(`product_id`) USING BTREE,
  INDEX `specs_id`(`specs_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品规格表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别id',
  `type_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别名称',
  `type_describe` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别描述',
  `type_url_left` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '左侧宣传图',
  `type_url_top` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '横幅宣传图',
  PRIMARY KEY (`type_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`type_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类别表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '采购id',
  `purchase_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '采购编号',
  `purchase_number` int(11) NOT NULL COMMENT '进货数量',
  `purchase_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '进货时间',
  `receipt_time` timestamp NULL DEFAULT NULL COMMENT '收货时间',
  `product_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品编号',
  `product_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `supplier_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商编号',
  `supplier_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商名称',
  `account_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员编号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员名称',
  `receipt_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '收货状态',
  PRIMARY KEY (`purchase_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`purchase_no`) USING BTREE,
  INDEX `product_no`(`product_no`) USING BTREE,
  INDEX `supplier_no`(`supplier_no`) USING BTREE,
  INDEX `account_number`(`account_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '采购表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for return_goods
-- ----------------------------
DROP TABLE IF EXISTS `return_goods`;
CREATE TABLE `return_goods`  (
  `return_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '退货id',
  `apply_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `order_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `user_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `return_price` double NOT NULL COMMENT '退款金额',
  `operator_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员账号',
  `operator_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作员名称',
  `deal_time` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `return_reason` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退货原因',
  `return_state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退货状态',
  PRIMARY KEY (`return_id`) USING BTREE,
  INDEX `user_name`(`user_name`) USING BTREE,
  INDEX `return_goods_ibfk_3`(`operator_number`) USING BTREE,
  INDEX `return_goods_ibfk_1`(`order_no`) USING BTREE,
  INDEX `return_goods_ibfk_2`(`user_number`) USING BTREE,
  INDEX `return_goods_ibfk_4`(`return_reason`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品退货表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for return_reason
-- ----------------------------
DROP TABLE IF EXISTS `return_reason`;
CREATE TABLE `return_reason`  (
  `reason_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '退货id',
  `reason_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '退货理由',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
  PRIMARY KEY (`reason_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`reason_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退货原因表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_describe` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色描述',
  `role_state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = COMPRESSED;

-- ----------------------------
-- Table structure for shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `shopping_cart`;
CREATE TABLE `shopping_cart`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `account_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户帐号',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `pay_amount` int(11) NOT NULL COMMENT '购买数量',
  `product_specs` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品规格',
  PRIMARY KEY (`cart_id`) USING BTREE,
  INDEX `account_number`(`account_number`) USING BTREE,
  INDEX `product_id`(`product_id`) USING BTREE,
  CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`account_number`) REFERENCES `user` (`account_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `shopping_cart_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for specs
-- ----------------------------
DROP TABLE IF EXISTS `specs`;
CREATE TABLE `specs`  (
  `specs_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格id',
  `specs_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格类型',
  `product_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类别',
  PRIMARY KEY (`specs_id`) USING BTREE,
  INDEX `product_type`(`product_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品规格表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `supplier_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商id',
  `supplier_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商编号',
  `supplier_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '供应商名称',
  `product_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品类别',
  `principal` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '负责人',
  `contact_way` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系方式',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否可用',
  PRIMARY KEY (`supplier_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`supplier_no`) USING BTREE,
  INDEX `product_type`(`product_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `user_sex` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户性别',
  `telephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `login_time` timestamp NULL DEFAULT NULL COMMENT '登录时间',
  `user_state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '用户状态',
  `summary` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `user_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `avatar_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `background_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '背景图片',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户身份',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UNIQUE`(`account_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Event structure for product_event
-- ----------------------------
DROP EVENT IF EXISTS `product_event`;
delimiter ;;
CREATE EVENT `product_event`
ON SCHEDULE
EVERY '1' DAY STARTS '2021-01-07 02:00:00'
ON COMPLETION PRESERVE
DO BEGIN


		UPDATE product SET is_new = 0 WHERE sale_time<(CURRENT_TIMESTAMP() + INTERVAL -15 DAY);


		DELETE FROM qiu.`order` WHERE order_time < DATE_SUB(CURDATE(),INTERVAL 1 DAY) AND order_state='待付款';	


		DELETE FROM vip WHERE overdue_time <= CURDATE(); 


	END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table order
-- ----------------------------
DROP TRIGGER IF EXISTS `order_delete`;
delimiter ;;
CREATE TRIGGER `order_delete` BEFORE DELETE ON `order` FOR EACH ROW BEGIN


	UPDATE product SET product_stock = product_stock + old.pay_amount WHERE product_no = old.product_no;


    END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table user
-- ----------------------------
DROP TRIGGER IF EXISTS `user_insert`;
delimiter ;;
CREATE TRIGGER `user_insert` AFTER INSERT ON `user` FOR EACH ROW BEGIN


	DECLARE roleId INT;


	SET roleId = (SELECT role_id FROM role WHERE role_name ='顾客');


		INSERT user_role VALUES(new.user_id,roleId);


    END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
