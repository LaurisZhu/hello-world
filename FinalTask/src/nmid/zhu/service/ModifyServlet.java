package nmid.zhu.service;

import nmid.zhu.pojo.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

/**
 * Created by Lawrence on 2017/8/31.
 */
@WebServlet(name = "ModifyServlet")
public class ModifyServlet extends HttpServlet implements ControlWord {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        UserService userService = new UserService();

        //获取输入
        String name = request.getParameter("name").trim();
        String age = request.getParameter("age").trim();
        String id = request.getParameter("stdNumber").trim();
        String major = request.getParameter("major").trim();
        String msg = "";

        //判断修改结果
        if (userService.modifyStudent(new Student(name,new Integer(age),id,major))) {
            msg = "修改成功";
        } else {
            msg = "修改失败";
        }

        //返回修改结果
        request.setAttribute("status",CONTROL_MODIFY);
        request.setAttribute("msg",msg);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
