package com.ppjj.cmmall.product.dao;

import com.ppjj.cmmall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author ppjj
 * @email 
 * @date 2022-12-27 14:54:14
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
