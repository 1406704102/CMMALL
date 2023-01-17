package com.ppjj.cmmall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.ppjj.cmmall.common.valid.group.AddGroup;
import com.ppjj.cmmall.common.valid.group.UpdateGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;


/**
 * 品牌
 *
 * @author ppjj
 * @email
 * @date 2022-12-27 14:54:14
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId
    @Null(message = "新增不能指定Id", groups = {AddGroup.class})
    @NotNull(message = "修改必须指定Id", groups = {UpdateGroup.class})
    private Long brandId;
    /**
     * 品牌名
     */
    @NotEmpty(groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @NotEmpty(groups = {AddGroup.class})
    @URL(message = "logo必须是一个合法的url", groups = {AddGroup.class, UpdateGroup.class})
    private String logo;
    /**
     * 介绍
     */
    @NotEmpty(groups = {AddGroup.class})
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    private Integer enable;


    private Integer showStatus;
    /**
     * 检索首字母
     */
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母", groups = {AddGroup.class, UpdateGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull
    @Min(value = 0, message = "排序必须大于等于0")
    private Integer sort;

}
