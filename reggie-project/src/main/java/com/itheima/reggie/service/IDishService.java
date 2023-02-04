package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-31
 */
public interface IDishService extends IService<Dish> {
    //保存菜品：
    //我们需要将接收到的数据菜品表放入菜品表，口味表放入口味表，要发送多条sql的数据，业务复杂因此我们需要单独写Service方法
    void saveDishAndFlavor(DishDto dishDto);


    Page<DishDto> pageDishAndCategory(Integer page, Integer pageSize, String name);

    DishDto findDishDtoById(Long id);

    void updateDishAndFlavor(DishDto dishDto);
}
