package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class RegistServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1、获取请求的参数
       String username = req.getParameter("username");
       String password = req.getParameter("password");
       String email = req.getParameter("email");


        //获取谷歌验证码的key
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);

        //获取以后要马上销毁，防止表单重复提交
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");

        if(token!=null&&token.equals(code)){
            // 3、检查 用户名是否可用
             if(userService.existsUsername(username)){
                 // 跳回注册页面
                 System.out.println("用户名["+username+"]已存在");
                 req.setAttribute("msg","用户名已存在！");
                 req.setAttribute("username",username);
                 req.setAttribute("email",email);
                 req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
             }else{
                 // 可用
                 // 调用 Sservice 保存到数据库
                 userService.registUser(new User(null,username,password,email));
                 // 跳到注册成功页面 regist_success.jsp
                 req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
             }

        }else{
            System.out.println("验证码["+code+"]错误");
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }


    }
}
