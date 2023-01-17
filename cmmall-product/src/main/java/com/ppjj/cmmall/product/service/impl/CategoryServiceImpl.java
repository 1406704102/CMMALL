package com.ppjj.cmmall.product.service.impl;

import com.ppjj.cmmall.product.service.CategoryBrandRelationService;
import io.prometheus.client.Collector;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.common.utils.Query;

import com.ppjj.cmmall.product.dao.CategoryDao;
import com.ppjj.cmmall.product.entity.CategoryEntity;
import com.ppjj.cmmall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        return categoryEntities.stream().filter(f -> f.getParentCid() == 0)
                .peek(p -> p.setChildren(getChildren(categoryEntities, p)))
                .sorted(Comparator.comparingInt(s -> (s.getSort() == null ? 0 : s.getSort())))
                .collect(Collectors.toList());
    }

    @Override
    public void removeByIds(List<Long> ids) {
        //TODO 1.检查当前分类是否被其他地方引用

        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public List<Long> getCategoryPathById(Long categoryId) {
        List<Long> categoryPath = new ArrayList<>();
        List<Long> parent = this.getParent(categoryId, categoryPath);
        Collections.reverse(parent);
        return categoryPath;
    }

    @Transactional
    @Override
    public void updateByCatId(CategoryEntity category) {
        categoryDao.updateById(category);
        if (!StringUtils.isEmpty(category.getName())) {
            categoryBrandRelationService.updateBrandByCatId(category.getCatId(), category.getName());
        }
    }

    private List<Long> getParent(Long catId, List<Long> categoryPath) {
        categoryPath.add(catId);
        CategoryEntity byId = this.getById(catId);
        if (byId.getParentCid() != 0) {
            CategoryEntity parent = this.getById(byId.getParentCid());
            this.getParent(parent.getCatId(), categoryPath);
        }
        return categoryPath;
    }

    private List<CategoryEntity> getChildren(List<CategoryEntity> categoryEntities, CategoryEntity categoryEntity) {
        return categoryEntities.stream()
                .filter(f -> f.getParentCid().equals(categoryEntity.getCatId()))
                .peek(p -> p.setChildren(getChildren(categoryEntities, p)))
                .sorted(Comparator.comparingInt(s -> (s.getSort() == null ? 0 : s.getSort())))
                .collect(Collectors.toList());
    }
}