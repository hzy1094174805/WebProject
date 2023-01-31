package com.itheima.reggie.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 套餐
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("setmeal")
@ApiModel(value = "Setmeal对象", description = "套餐")
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    @ApiModelProperty(value = "菜品分类id")
    @TableField("category_id")
    private Long categoryId;

    @ApiModelProperty(value = "套餐名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "套餐价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty(value = "状态 0:停用 1:启用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "描述信息")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "图片")
    @TableField("image")
    private String image;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField("create_user")
    private Long createUser;

    @ApiModelProperty(value = "修改人")
    @TableField("update_user")
    private Long updateUser;

    @ApiModelProperty(value = "是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;


}
