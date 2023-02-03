package com.itheima.reggie.common;

/**
 * ThreadLocal的操作工具类
 * 主要为当前线程取数据
 *
 * @author ilovend
 * @date 2023/02/03
 */
public class BaseContext {
    /**
     * 线程本地
     * 真正干活的是这个线程
     *
     */
    private static final ThreadLocal<Long> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public static void setUserId(Long userId) {
        THREAD_LOCAL.set(userId);
    }

    /**
     * 得到用户id
     *
     * @return {@link Long}
     */
    public static Long getUserId() {
        return THREAD_LOCAL.get();
    }

    /**
     * 删除
     */
    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
