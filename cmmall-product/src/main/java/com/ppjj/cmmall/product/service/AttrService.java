package com.ppjj.cmmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppjj.cmmall.product.entity.AttrEntity;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author ppjj
 * @email 
 * @date 2022-12-27 14:54:14
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);
}

