package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 类别控制器
 * <p>
 * 菜品及套餐分类 前端控制器
 * </p>
 *
 * @author HeZhongYu
 * @date 2023/02/03
 * @since 2023-01-22
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    /**
     * 目录服务
     */
    @Autowired
    private ICategoryService categoryService;


    /**
     * 保存
     * 添加分类
     *
     * @param category 类别
     * @return {@link R}
     */
    @PostMapping
    public R save(@RequestBody Category category) {
        categoryService.save(category);
        return R.success(null);
    }

    /**
     * 页面
     *
     * @param page     页面
     * @param pageSize 页面大小
     * @return {@link R}<{@link Page}>
     */
    @GetMapping("/page")
    public R<Page> page(Integer page, Integer pageSize) {
//        设置排序条件
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Category::getSort);
//        设置分页条件
        Page<Category> pageInfo = new Page<>(page, pageSize);
        categoryService.page(pageInfo, lambdaQueryWrapper);
        return R.success(pageInfo);
    }


    /**
     * 删除
     * 删除分类
     *
     * @param id id
     * @return {@link R}
     */
    @DeleteMapping
    public R deleteById(Long id) {
//        要删除套餐分类或者菜单分类 的时候，先查询一下该分类下是否有菜品或者套餐，如果有，就不能删除，并抛出异常
        categoryService.removeById(id);
        return R.success(null);
    }

    @PutMapping
    public R update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success(null);
    }


}
