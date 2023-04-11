package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WEBUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = userService.loginUser(new User(null,username,password,null));
        //调用service方法处理业务，根据返回结果进行处理
        if(loginUser==null){
            System.out.println("用户名或密码错误！");
            req.setAttribute("msg","用户名或密码错误！");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);

        }else {
            System.out.println("登录成功！");
            req.getSession().setAttribute("user",loginUser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        //获取谷歌验证码的key值
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //获取以后要马上销毁，防止表单重复提交
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");

        User user = WEBUtils.copyParamToBean(req.getParameterMap(),new User());

//        if(token!=null&&token.equals(code)){
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

        //}
//        else{
//            System.out.println("验证码["+code+"]错误");
//            req.setAttribute("msg","验证码错误！");
//            req.setAttribute("username",username);
//            req.setAttribute("email",email);
//            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
//        }
    }


    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        boolean existsUsername = userService.existsUsername(username);

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);

    }
}
