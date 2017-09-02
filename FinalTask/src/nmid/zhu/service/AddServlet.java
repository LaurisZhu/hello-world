package nmid.zhu.service;

import nmid.zhu.pojo.Student;
import sun.plugin.com.Dispatcher;
import sun.security.util.DisabledAlgorithmConstraints;

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
 * Created by Lawrence on 2017/8/30.
 */
@WebServlet(name = "AddServlet")
public class AddServlet extends HttpServlet implements ControlWord {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=utf-8");
        ServletContext servletContext =getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");

        UserService userService = new UserService();
        String name = request.getParameter("name").trim();
        String age = request.getParameter("age").trim();
        String id = request.getParameter("stdNumber").trim();
        String major = request.getParameter("major").trim();
        String msg = null;
        if (userService.addStudents(new Student(name,new Integer(age),id,major))) {
            msg = "添加成功";
        } else {
            msg = "添加失败";
        }
        request.setAttribute("status",CONTROL_ADD);
        request.setAttribute("msg",msg);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
