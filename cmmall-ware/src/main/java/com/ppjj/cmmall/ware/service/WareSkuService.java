package com.ppjj.cmmall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author ppjj
 * @email 
 * @date 2022-12-28 10:41:52
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

