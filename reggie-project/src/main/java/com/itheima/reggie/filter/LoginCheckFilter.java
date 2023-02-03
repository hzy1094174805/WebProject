package com.itheima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("LoginCheckFilter类中此时的线程id为:{}", Thread.currentThread().getId());

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        String[] urls = {
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**"
        };


        if (check(urls, uri)) {
            chain.doFilter(req, resp);
            return;
        }

        Employee emp = (Employee) req.getSession().getAttribute("employee");
        if (emp != null) {
//            说明已经登录过,要放行
            BaseContext.setUserId(emp.getId());
            chain.doFilter(req, resp);//controller metaObjectHandler
            BaseContext.remove();
            return;
        }

        R<Object> r = R.error("NOTLOGIN");
        String json = JSON.toJSONString(r);
//        resp.setContentType("application/json;charset=utf-8");乱码取消注释
        resp.getWriter().write(json);
    }


    /**
     * 检查uri是否在urls中
     *
     * @param urls
     * @param uri
     * @return true:在urls中
     */

    public boolean check(String[] urls, String uri) {
        AntPathMatcher PATH_MATCHER = new AntPathMatcher();
        for (String url : urls) {
            if (PATH_MATCHER.match(url, uri)) {
                return true;
            }
        }
        return false;
    }
}
