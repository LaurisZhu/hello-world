package nmid.zhu.service;

import nmid.zhu.pojo.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * Created by Lawrence on 2017/8/31.
 */
@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet implements ControlWord {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        UserService userService = new UserService();
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/index.jsp");

        ArrayList<Student> students = new ArrayList<>();
        String name = request.getParameter("name").trim();
        String id = request.getParameter("stdNumber").trim();
        String msg = "";
        students = userService.findStudents(name,id);
        if (students == null){
            msg = "找不到该学生";
        }

        request.setAttribute("status",CONTROL_SEARCH);
        request.setAttribute("msg",msg);
        request.setAttribute("students",students);
        dispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
