package com.ppjj.cmmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author ppjj
 * @email 
 * @date 2022-12-27 14:54:14
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateByBrandId(BrandEntity brand);
}

