package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Category;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-31
 */
public interface ICategoryService extends IService<Category> {
    void removeById(Long id);
}
