package com.ppjj.cmmall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.coupon.entity.CouponHistoryEntity;

import java.util.Map;

/**
 * 优惠券领取历史记录
 *
 * @author ppjj
 * @email 
 * @date 2022-12-27 17:23:14
 */
public interface CouponHistoryService extends IService<CouponHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

