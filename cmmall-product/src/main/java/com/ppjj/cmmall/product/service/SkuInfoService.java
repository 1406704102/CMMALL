package com.ppjj.cmmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author ppjj
 * @email 
 * @date 2022-12-27 14:54:14
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

