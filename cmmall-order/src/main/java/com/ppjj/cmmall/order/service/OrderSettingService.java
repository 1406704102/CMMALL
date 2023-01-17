package com.ppjj.cmmall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.order.entity.OrderSettingEntity;

import java.util.Map;

/**
 * 订单配置信息
 *
 * @author ppjj
 * @email 
 * @date 2022-12-28 10:29:55
 */
public interface OrderSettingService extends IService<OrderSettingEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

