package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> getAll() {
        return userMapper.selectList(null);
    }

}
