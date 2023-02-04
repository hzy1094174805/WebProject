package com.itheima.reggie.controller;


import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-22
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private IDishService dishService;

    @PostMapping
    public R save(@RequestBody Dish dish) {
        dishService.save(dish);
        return R.success(null);
    }
}
