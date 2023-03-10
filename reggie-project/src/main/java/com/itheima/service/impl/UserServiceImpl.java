package com.itheima.service.impl;

import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import com.itheima.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
