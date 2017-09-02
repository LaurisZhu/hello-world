package nmid.zhu.filter;

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
public class LoginControlFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //转换输入输出类型
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        //获取Cookie和Session
        HttpSession session = request.getSession();
        Cookie[] cookies1 = request.getCookies();

        //获取用户ip
        String request_ip = request.getRemoteAddr();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");

        if (session.getAttribute("loginUser") == null) {
            dispatcher.forward(request,response);
        }
        ArrayList<Cookie> cookies = (ArrayList<Cookie>) Arrays.asList(cookies1);


        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
