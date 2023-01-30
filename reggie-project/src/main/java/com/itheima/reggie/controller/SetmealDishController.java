package com.itheima.reggie.controller;


import com.itheima.reggie.domain.SetmealDish;
import com.itheima.reggie.service.ISetmealDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 套餐菜品关系 前端控制器
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-22
 */
@RestController
@RequestMapping("/setmeal-dish")
public class SetmealDishController {

    @Autowired
    public ISetmealDishService setmealDishService;

    @GetMapping
    public List<SetmealDish> findAllSetmealDish() {
        return setmealDishService.getAll();
    }
}
