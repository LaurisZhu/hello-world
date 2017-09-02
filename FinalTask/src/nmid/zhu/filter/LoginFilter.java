package nmid.zhu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Lawrence on 2017/9/2.
 */
@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //转换输入输出类型
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        httpReq.setCharacterEncoding("utf-8");
        httpResp.setContentType("text/html;charset=utf-8");

        //获取输入
        String id = httpReq.getParameter("uuid").trim();
        String password = httpReq.getParameter("passwd").trim();

        RequestDispatcher dispatcher = httpReq.getRequestDispatcher("/login.jsp");
        //对输入校验
        String msg=null;
        if (id.equals("") || password.equals("")) {
            if (id.equals("")) {
                msg = "账号为空，请按要求输入";
            } else {
                msg = "密码为空，请按要求输入";
            }
            httpReq.setAttribute("msg", msg);
            dispatcher.forward(httpReq, httpResp);
            return;
        } else if (!Pattern.matches("[1-9][0-9]{9}",id)) {
            msg = "账号格式错误，请按要求输入！";
            httpReq.setAttribute("msg",msg);
            dispatcher.forward(httpReq,httpResp);
            return;
        }
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
