package com.atguigu.web;

import com.atguigu.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;


public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();//提交事务
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//将错误抛给tomcat服务器，展示错误页面
        }
    }

    @Override
    public void destroy() {

    }
}
