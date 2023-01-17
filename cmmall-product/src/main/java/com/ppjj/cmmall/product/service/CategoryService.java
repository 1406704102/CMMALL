package com.ppjj.cmmall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author ppjj
 * @email 
 * @date 2022-12-27 14:54:14
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeByIds(List<Long> ids);

    List<Long> getCategoryPathById(Long categoryId);

    void updateByCatId(CategoryEntity categoryEntity);
}

