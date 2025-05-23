package com.cow.service.impl;

import com.cow.dao.ProductSpecsDao;
import com.cow.entity.ProductSpecs;
import com.cow.service.ProductSpecsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *  
 * @description 商品规格业务逻辑
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductSpecsServiceImpl implements ProductSpecsService {
    @Autowired
    private ProductSpecsDao productSpecsDao;

    @Override
    public List<ProductSpecs> selectAll() {
        return productSpecsDao.selectAll();
    }

    @Override
    public List<String> selectAllByProId(Integer productId) {
        return productSpecsDao.selectAllByProId(productId);
    }

    @Override
    public Boolean insertData(ProductSpecs productSpecs) {
        return productSpecsDao.insertData(productSpecs);
    }

    @Override
    public Boolean updateById(ProductSpecs productSpecs) {
        return productSpecsDao.updateById(productSpecs);
    }

    @Override
    public Boolean deleteById(ProductSpecs productSpecs) {
        return productSpecsDao.deleteById(productSpecs);
    }

    @Override
    public Boolean insertBatch(List<ProductSpecs> productSpecsList) {
        return productSpecsDao.insertBatch(productSpecsList);
    }
}
