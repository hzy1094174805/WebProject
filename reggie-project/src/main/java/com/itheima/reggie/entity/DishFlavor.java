package com.itheima.reggie.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜品口味关系表
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dish_flavor")
@ApiModel(value = "DishFlavor对象", description = "菜品口味关系表")
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.NONE)
    private Long id;

    @ApiModelProperty(value = "菜品")
    @TableField("dish_id")
    private Long dishId;

    @ApiModelProperty(value = "口味名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "口味数据list")
    @TableField("value")
    private String value;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    @ApiModelProperty(value = "是否删除")
    @TableField("is_deleted")
    private Integer isDeleted;


}
