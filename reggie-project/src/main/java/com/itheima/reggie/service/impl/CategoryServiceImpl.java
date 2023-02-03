package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-31
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private DishServiceImpl dishService;
    @Autowired
    private SetmealServiceImpl setmealService;




    /**
     * 删除通过id
     *
     * @param id id
     */
    @Override
    public void removeById(Long id) {//id 分类的id
//        查询菜品分类是否关联菜品表
        LambdaQueryWrapper<Dish> dishLambdaQueryWrappers = new LambdaQueryWrapper<>();
        dishLambdaQueryWrappers.eq(Dish::getCategoryId, id);
        long count = dishService.count(dishLambdaQueryWrappers);
        if (count > 0) {
            throw new CustomException("该分类下有菜品，不能删除");
        }
//        查询套餐分类是否关联套餐表
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        long count1 = setmealService.count(setmealLambdaQueryWrapper);
        if (count1 > 0) {
            throw new CustomException("该分类下有套餐，不能删除");
        }

        super.removeById(id);//super才能调用父类真正的删除操作

    }
}
