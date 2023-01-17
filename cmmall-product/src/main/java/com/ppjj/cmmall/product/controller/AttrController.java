package com.ppjj.cmmall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ppjj.cmmall.product.entity.AttrEntity;
import com.ppjj.cmmall.product.vo.AttrVo;
import com.ppjj.cmmall.product.service.AttrService;



/**
 * 商品属性
 *
 * @author ppjj
 * @email 
 * @date 2022-12-27 14:54:14
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    // attr/base/list/

    @GetMapping("/base/list/{catId}")
    public R baseAttr(@RequestParam Map<String, Object> params, @PathVariable("catId") Long catId) {
        PageUtils page = attrService.queryBasePage(params, catId);
        return R.ok().put("page", page);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateById(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
