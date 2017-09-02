package nmid.zhu.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lawrence on 2017/8/29.
 */
public class LoginServlet extends javax.servlet.http.HttpServlet implements ControlWord {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //request.setCharacterEncoding("GB2312");
        response.setContentType("text/html;charset=utf-8");
        UserService userService = new UserService();
        HttpSession session = request.getSession();
        HashMap<String,String> loginUsers = new HashMap<>();

        //获取输入
        String id = request.getParameter("uuid").trim();
        String password = request.getParameter("passwd").trim();
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/login.jsp");

        if (userService.login(id, password)) {
            PrintWriter out = response.getWriter();
            out.println("登陆成功");
            out.println("3秒后跳转到学生信息管理页面……");
        } else {
            String msg = "用户名或密码错误";
            request.setAttribute("msg",msg);
            dispatcher.forward(request,response);
        }

            //设置cookie，session防止重复登陆
            Cookie cookie = new Cookie("id",id);
            cookie.setMaxAge(COOKIE_SURVIVAL);
            response.addCookie(cookie);
            loginUsers.put(request.getRemoteAddr(),id);
            session.setAttribute("loginUser",loginUsers);
            response.setHeader("refresh", "3;url=/index.jsp");
        }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
