package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.service.IDishService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public R save(@RequestBody DishDto dishDto) {
        dishService.saveDishAndFlavor(dishDto);
        return R.success(null);
    }

    /*
     *
     * 菜品的分页
     *
     * */
    @GetMapping("/page")
    public R<Page<DishDto>> page(Integer page, Integer pageSize, String name) {
//        Page<Dish> pageInfo = new Page<>(page, pageSize);
//        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
//        lambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
//        dishService.page(pageInfo, lambdaQueryWrapper);

        Page<DishDto> pageInfo= dishService.pageDishAndCategory(page,pageSize,name);
        return R.success(pageInfo);
    }


    /*
     *
     * 修改菜品的回显操作
     *
     * */
    @GetMapping("/{id}")
    public R<DishDto> findDishDtoById(@PathVariable Long id) {
        DishDto dishDto = dishService.findDishDtoById(id);
        return R.success(dishDto);
    }

    /*
     *
     * 修改菜品
     *
     * */

    @PutMapping
    public R update(@RequestBody DishDto dishDto) {
        dishService.updateDishAndFlavor(dishDto);
        return R.success(null);
    }
}



