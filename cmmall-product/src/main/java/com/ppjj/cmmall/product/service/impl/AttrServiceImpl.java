package com.ppjj.cmmall.product.service.impl;

import com.ppjj.cmmall.product.dao.AttrAttrgroupRelationDao;
import com.ppjj.cmmall.product.dao.AttrGroupDao;
import com.ppjj.cmmall.product.dao.CategoryDao;
import com.ppjj.cmmall.product.entity.AttrAttrgroupRelationEntity;
import com.ppjj.cmmall.product.entity.AttrGroupEntity;
import com.ppjj.cmmall.product.entity.CategoryEntity;
import com.ppjj.cmmall.product.service.AttrAttrgroupRelationService;
import com.ppjj.cmmall.product.vo.AttrRespVo;
import com.ppjj.cmmall.product.vo.AttrVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.common.utils.Query;

import com.ppjj.cmmall.product.dao.AttrDao;
import com.ppjj.cmmall.product.entity.AttrEntity;
import com.ppjj.cmmall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    CategoryDao categoryDao;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        save(attrEntity);

        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
        attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
        attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
    }

    @Override
    public PageUtils queryBasePage(Map<String, Object> params, Long catId) {
        String key = (String) params.get("key");
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if (catId != 0) {
            queryWrapper.eq("catelog_id", catId);
        }
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and(a -> a.eq("attr_id", key).or().like("attr_name", key));
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);

        List<AttrEntity> records = page.getRecords();
        List<AttrRespVo> attrRespVoList = records.stream().map(m -> {
            AttrRespVo attrRespVo = new AttrRespVo();
            BeanUtils.copyProperties(m, attrRespVo);
            AttrAttrgroupRelationEntity attrAttrgroupRelation = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrRespVo.getAttrId()));
            if (attrAttrgroupRelation != null) {
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrAttrgroupRelation.getAttrGroupId());
                attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
            }
            CategoryEntity categoryEntity = categoryDao.selectById(attrRespVo.getCatelogId());
            if (categoryEntity != null) {
                attrRespVo.setCatelogName(categoryEntity.getName());
            }
            return attrRespVo;
        }).collect(Collectors.toList());

        pageUtils.setList(attrRespVoList);
        return pageUtils;
    }

}