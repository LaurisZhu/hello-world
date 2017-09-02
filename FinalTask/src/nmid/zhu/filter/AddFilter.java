package nmid.zhu.filter;

import nmid.zhu.service.ControlWord;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Created by Lawrence on 2017/9/2.
 */
@WebFilter(filterName = "AddFilter")
public class AddFilter implements Filter,ControlWord {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //转换输入输出类型
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpResp = (HttpServletResponse) resp;
        httpReq.setCharacterEncoding("utf-8");
        httpResp.setContentType("text/html;charset=utf-8");

        //获取输入
        String name = httpReq.getParameter("name").trim();
        String age = httpReq.getParameter("age").trim();
        String id = httpReq.getParameter("stdNumber").trim();
        String msg = null;
        RequestDispatcher dispatcher =httpReq.getRequestDispatcher("/index.jsp");
        httpReq.setAttribute("status",CONTROL_ADD);
        //对输入信息进行校验
        if ((httpReq.getParameter("major") == null)||name.equals("") || id.equals("") || age.equals("")) {
            msg = "输入不能为空";
            httpReq.setAttribute("msg", msg);
            dispatcher.forward(httpReq, httpResp);
            return;
        } else if (!Pattern.matches("[\\u4e00-\\u9fa5]{2,4}",name)||!Pattern.matches("[1-9][0-9]",age)
                ||!Pattern.matches("[1-9][0-9]{9}",id)) {
            msg = "非法输入";
            httpReq.setAttribute("msg",msg);
            dispatcher.forward(httpReq,httpResp);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
