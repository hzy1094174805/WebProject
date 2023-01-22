package com.itheima.mapper;

import com.itheima.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

/**
 * <p>
 * 用户信息 Mapper 接口
 * </p>
 *
 * @author HeZhongYu
 * @since 2023-01-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
