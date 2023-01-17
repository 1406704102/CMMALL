package com.ppjj.cmmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ppjj.cmmall.common.utils.PageUtils;
import com.ppjj.cmmall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author ppjj
 * @email 
 * @date 2022-12-28 10:35:33
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

