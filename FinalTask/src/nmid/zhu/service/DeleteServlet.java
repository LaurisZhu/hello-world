package nmid.zhu.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.regex.Pattern;

/**
 * Created by Lawrence on 2017/8/31.
 */
@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet implements ControlWord {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        //设置格式
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");

        //获取输入
        String id = request.getParameter("stdNumber").trim();

        String msg = null;
        if (userService.deleteStudent(id)) {
            msg = "删除成功";
        } else {
            msg = "删除失败";
        }

        //将操作结果返回该页面
        request.setAttribute("status",CONTROL_DELETE);
        request.setAttribute("msg",msg);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
