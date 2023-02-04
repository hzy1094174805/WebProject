package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.dto.DishDto;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.DishFlavor;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.ICategoryService;
import com.itheima.reggie.service.IDishFlavorService;
import com.itheima.reggie.service.IDishService;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-31
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {
    @Autowired
    private IDishFlavorService dishFlavorService;
    @Autowired
    private ICategoryService categoryService;

    /**
     * 节省菜肴和风味
     *
     * @param dishDto 菜dto
     */
    @Transactional
    @Override
    public void saveDishAndFlavor(DishDto dishDto) {
//        添加菜品
//        这个保存操作只能保存菜品，口味无法保存
        System.out.println(dishDto.getId());//null
        super.save(dishDto);
        System.out.println(dishDto.getId());//有id
//        insert into dishflavor value("辣度","微辣","1");
//        为每个口味，建立与菜品的关联，dishId字段
        for (DishFlavor flavor : dishDto.getFlavors()) {
            flavor.setDishId(dishDto.getId());
        }
        dishFlavorService.saveBatch(dishDto.getFlavors());
    }

    /**
     * 菜品分页查询，并且还要查询每个菜品的分类名称
     *
     * @param page     页面
     * @param pageSize 页面大小
     * @param name     名字
     * @return {@link Page}<{@link DishDto}>
     */
    @Override
    public Page<DishDto> pageDishAndCategory(Integer page, Integer pageSize, String name) {
//        TODO: 查询菜品的分页数据，并且还要查询每个菜品的分类名称
        Page<Dish> dishPage = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        dishLambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
        super.page(dishPage, dishLambdaQueryWrapper);
//        TODO: 根据某个商品的分类id查询对应的分类名称，并封装到dishDto集合中
        BeanUtils.copyProperties(dishPage, dishDtoPage,"records");
        ArrayList<DishDto> dishDtos = new ArrayList<>();
        for (Dish dish : dishPage.getRecords()) {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(dish, dishDto);

            Category category = categoryService.getById(dish.getCategoryId());
            dishDto.setCategoryName(category.getName());
            dishDtos.add(dishDto);
        }

        dishDtoPage.setRecords(dishDtos);

        return dishDtoPage;
    }

    /**
     * 根据id查询菜品和口味
     *
     * @param id 菜品id
     * @return {@link DishDto}
     */
    @Override
    public DishDto findDishDtoById(Long id) {
        Dish byId = super.getById(id);
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(byId, dishDto);
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, id);
        List<DishFlavor> dishFlavors = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
        dishDto.setFlavors(dishFlavors);
        return dishDto;
    }

    /**
     * 根据闯入的dto修改菜品和口味
     *
     * @param dishDto 菜品dto
     */
    @Override
    public void updateDishAndFlavor(DishDto dishDto) {
//        修改菜品
        super.updateById(dishDto);
//        修改口味
//        先删除原来的口味
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(dishFlavorLambdaQueryWrapper);
//        再添加新的口味
        for (DishFlavor flavor : dishDto.getFlavors()) {
            flavor.setDishId(dishDto.getId());
        }
        dishFlavorService.saveBatch(dishDto.getFlavors());

    }

}

