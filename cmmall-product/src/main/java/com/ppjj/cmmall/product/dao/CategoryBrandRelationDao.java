package com.ppjj.cmmall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ppjj.cmmall.product.entity.CategoryBrandRelationEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 品牌分类关联
 * 
 * @author leifengyang
 * @email leifengyang@gmail.com
 * @date 2019-11-17 21:25:25
 */
@Mapper
public interface CategoryBrandRelationDao extends BaseMapper<CategoryBrandRelationEntity> {

    void updateCategory(@Param("catId") Long catId, @Param("name") String name);

}
