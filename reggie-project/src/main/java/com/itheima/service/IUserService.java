package com.itheima.service;

import com.itheima.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;


import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-22
 */

public interface IUserService extends IService<User> {

    List<User> getAll();

}
