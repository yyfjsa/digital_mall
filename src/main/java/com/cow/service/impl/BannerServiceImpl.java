package com.cow.service.impl;

import com.cow.dao.BannerDao;
import com.cow.entity.Banner;
import com.cow.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 *  
 * @description 商品轮播图业务逻辑
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public Banner selectById(Integer bannerId) {
        return bannerDao.selectById(bannerId);
    }

    @Override
    public List<Banner> selectAll() {
        return bannerDao.selectAll();
    }

    @Override
    public Boolean insertData(Banner banner) {
        return bannerDao.insertData(banner);
    }

    @Override
    public Boolean updateById(Banner banner) {
        return bannerDao.updateById(banner);
    }

    @Override
    public Boolean deleteById(Integer bannerId) {
        return bannerDao.deleteById(bannerId);
    }
}
