package com.ppjj.cmmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.coupon.entity.SeckillSkuRelationEntity;

import java.util.Map;

/**
 * 秒杀活动商品关联
 *
 * @author ppjj
 * @email 
 * @date 2022-12-27 17:23:14
 */
public interface SeckillSkuRelationService extends IService<SeckillSkuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

