package com.itheima.reggie.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 员工控制器
 * <p>
 * 员工信息 前端控制器
 * </p>
 *
 * @author HeZhongYu
 * @date 2023/01/30
 * @since 2023-01-22
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    /**
     * 员工服务
     */
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 登录
     *
     * @param employee           员工
     * @param httpServletRequest http servlet请求
     * @return {@link R}<{@link Employee}>
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest httpServletRequest) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Employee> wrapper = queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee empForMysql = employeeService.getOne(wrapper);

        if (empForMysql == null) {
            return R.error("用户名不存在");
        }
        if (!empForMysql.getPassword().equals(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes()))) {
            return R.error("密码错误");
        }
        if (empForMysql.getStatus() == 0) {
            return R.error("用户已被禁用");
        }

        httpServletRequest.getSession().setAttribute("employee", empForMysql);
        return R.success(empForMysql);
    }

    /**
     * 注销
     *
     * @param httpServletRequest http servlet请求
     * @return {@link R}
     */
    @PostMapping("/logout")
    public R logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        return R.success("退出成功");
    }

    /**
     * 保存
     *
     * @param employee           员工
     * @param httpServletRequest http servlet请求
     * @return {@link R}
     */
    @PostMapping
    public R save(@RequestBody Employee employee, HttpServletRequest httpServletRequest) {
        /*employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        Employee empForSession = (Employee) httpServletRequest.getSession().getAttribute("employee");
        employee.setCreateUser(empForSession.getId());
        employee.setUpdateUser(empForSession.getId());*/
        String md5DigestAsHex = DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setPassword(md5DigestAsHex);
        employeeService.save(employee);
        return R.success("添加成功");
    }


    /**
     * 分页查询
     *
     * @param page     页面
     * @param pageSize 页面大小
     * @return {@link R}
     */
    @GetMapping("/page")
    public R page(Integer page, Integer pageSize, String name) {
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);//name like '%name%'
        Page<Employee> pageInfo = new Page<>(page, pageSize);
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }


    /**
     * 更新,禁用或启用三合一功能
     *
     * @param employee           员工
     * @param httpServletRequest http servlet请求
     * @return {@link R}
     */
    @PutMapping
    public R update(@RequestBody Employee employee, HttpServletRequest httpServletRequest) {
        log.info("EmployeeController类中此时的线程id为:{}", Thread.currentThread().getId());

        Employee empForSession = (Employee) httpServletRequest.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empForSession.getId());
        employeeService.updateById(employee);
        return R.success("修改成功");
    }

    /**
     * 通过id
     *
     * @param eid eid
     * @return {@link R}<{@link Employee}>
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable("id") Long eid) {
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(eid);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }
}

