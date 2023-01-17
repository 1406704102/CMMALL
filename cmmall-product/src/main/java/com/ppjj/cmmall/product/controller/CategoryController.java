package com.ppjj.cmmall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ppjj.cmmall.product.entity.CategoryEntity;
import com.ppjj.cmmall.product.service.CategoryService;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.common.utils.R;



/**
 * 商品三级分类
 *
 * @author ppjj
 * @email 
 * @date 2022-12-27 14:54:14
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 商品分类数列表
     */
    @RequestMapping("/list/tree")
    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = categoryService.queryPage(params);
        List<CategoryEntity> categoryEntityList = categoryService.listWithTree();
        return R.ok(categoryEntityList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok(category);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);
        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateByCatId(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] catIds){
		categoryService.removeByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
