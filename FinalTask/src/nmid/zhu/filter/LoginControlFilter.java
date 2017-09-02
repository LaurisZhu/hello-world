package nmid.zhu.filter;

import nmid.zhu.service.ControlWord;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Lawrence on 2017/9/2.
 */
@WebFilter(filterName = "LoginControlFilter")
public class LoginControlFilter implements Filter,ControlWord {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //转换输入输出类型
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取Cookie和Session
        HttpSession session = request.getSession();
        Cookie[] cookies1 = request.getCookies();
        Cookie idCookie = null;

        //获取用户ip
        String request_ip = request.getRemoteAddr();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");

        String loginStatus = (String)session.getAttribute("isLogin");
        String userIP = (String) session.getAttribute("ip");
        String userID = (String) session.getAttribute("id");

        if (cookies1 == null) {
            dispatcher.forward(request,response);
        }
        for (Cookie cookie:cookies1
                ) {
            if (cookie.getName().equals("id")){
                idCookie = cookie;
            }
        }


        //游览器有无cookie_id 存储
        if (idCookie == null) {
            dispatcher.forward(request,response);
        } else {
            //服务器有无loginStatus的session
            if (loginStatus == null) {
                dispatcher.forward(request, response);
                //当前访问客户是否为登陆状态
            } else if (!loginStatus.equals(IS_LOGIN)) {
                dispatcher.forward(request, response);
            } else {
                //当前访问者的IP与session记录的是否相同
                if (!request_ip.equals(userIP)) {
                    dispatcher.forward(request, response);
                    //当前访问者的id与Session记录的是否相同
                } else if (!userID.equals(idCookie.getValue())) {
                    dispatcher.forward(request,response);
                }
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
