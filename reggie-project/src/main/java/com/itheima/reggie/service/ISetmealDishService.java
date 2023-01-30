package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.domain.SetmealDish;

import java.util.List;

/**
 * <p>
 * 套餐菜品关系 服务类
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-22
 */
public interface ISetmealDishService extends IService<SetmealDish> {

    List<SetmealDish> getAll();
}
