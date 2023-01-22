package com.itheima.service.impl;

import com.itheima.domain.Category;
import com.itheima.mapper.CategoryMapper;
import com.itheima.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-22
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
