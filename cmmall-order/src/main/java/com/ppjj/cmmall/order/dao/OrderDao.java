package com.ppjj.cmmall.order.dao;

import com.ppjj.cmmall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author ppjj
 * @email 
 * @date 2022-12-28 10:29:55
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
