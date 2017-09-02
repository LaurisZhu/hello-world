package nmid.zhu.db;

import nmid.zhu.pojo.Manager;
import nmid.zhu.pojo.Student;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.sort;

/**
 * Created by Lawrence on 2017/8/30.
 */
public class DBOperator {

    private String dbUrl = "jdbc:mysql://localhost:3306/studentsinfomanager" +
            "?useUnicode=true&characterEncoding=utf-8";
    private String dbUser = "root";
    private String passWord = "1111";

    public DBOperator() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUser, passWord);
    }

    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closePrepStmt(PreparedStatement prepStmt) {
        try {
            if (prepStmt != null) {
                prepStmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取全部详细信息
     * @param people students或是manager，确定搜索的表。
     * @return
     */
    public Collection getPeople(String people) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String preSql = "SELECT * FROM " + people;
            prepStmt = conn.prepareStatement(preSql);
            rs = prepStmt.executeQuery();
            ArrayList arrayList = new ArrayList();

            if (people.equals("students")) {
                while (rs.next()) {
                    Student oneStudent = new Student(rs.getString(1), rs.getInt(2),
                            rs.getString(3), rs.getString(4));
                    arrayList.add(oneStudent);
                }
                return arrayList;
            } else if (people.equals("manager")){
                while (rs.next()) {
                    Manager oneManager = new Manager(rs.getString(1), rs.getString(2),
                            rs.getString(3));
                    arrayList.add(oneManager);
                }
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closePrepStmt(prepStmt);
            closeConnection(conn);
        }
        return null;
    }

    public Student getStudentByID(String id) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        Student oneStudent = null;
        int temp = 0;
        try {
            conn = getConnection();
            String preSql = "SELECT * FROM students WHERE 学号 = ?";
            prepStmt = conn.prepareStatement(preSql);
            prepStmt.setString(1, id);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                oneStudent = new Student(rs.getString(1), rs.getInt(2),
                        rs.getString(3), rs.getString(4));
                temp++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closePrepStmt(prepStmt);
            closeConnection(conn);
        }
        if (temp == 0) return null;
        return oneStudent;
    }

    public ArrayList<Student> getStudentsByName(String name) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet rs = null;
        int temp = 0;
        ArrayList<Student> studentFound = new ArrayList<>();
        try {
            conn = getConnection();
            String preSql = "SELECT * FROM students WHERE 姓名 = ?";
            prepStmt = conn.prepareStatement(preSql);
            prepStmt.setString(1,name);
            rs = prepStmt.executeQuery();
            while (rs.next()) {
                studentFound.add(new Student(rs.getString(1),rs.getInt(2),
                        rs.getString(3),rs.getString(4)));
                temp++;
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            closeResultSet(rs);
            closePrepStmt(prepStmt);
            closeConnection(conn);
        }
        if (temp==0) {
            return null;
        }
        sort(studentFound);
        return studentFound;
    }

    public boolean addStudetns(Student newStudent) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        try {
            conn = getConnection();
            String preSql = "INSERT INTO students (姓名, 年龄, 学号, 专业) VALUES (?,?,?,?)";
            prepStmt = conn.prepareStatement(preSql);
            prepStmt.setString(1,newStudent.getName());
            prepStmt.setInt(2,newStudent.getAge());
            prepStmt.setString(3,newStudent.getId());
            prepStmt.setString(4,newStudent.getMajor());
            if (prepStmt.executeUpdate() == 1) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closePrepStmt(prepStmt);
            closeConnection(conn);
        }
        return false;

    }

    public boolean deleteStudetns(String id) {
        Connection conn = null;
        PreparedStatement prepStmt = null;
        int temp = 0;
        try {
            conn = getConnection();
            String preSql = "DELETE FROM students WHERE 学号 = ?";
            prepStmt = conn.prepareStatement(preSql);
            prepStmt.setString(1,id);
            temp += prepStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closePrepStmt(prepStmt);
            closeConnection(conn);
        }
        return temp == 1;
    }

    public boolean modifyStudent(Student oneStudent) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        try {
            conn = getConnection();
            String preSql = "UPDATE students SET 姓名 = ?,年龄 = ?, 专业 = ? WHERE 学号 = ?";
            preStmt = conn.prepareStatement(preSql);
            preStmt.setString(1,oneStudent.getName());
            preStmt.setInt(2,oneStudent.getAge());
            preStmt.setString(3,oneStudent.getMajor());
            preStmt.setString(4,oneStudent.getId());
            preStmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closePrepStmt(preStmt);
            closeConnection(conn);
        }
        return true;
    }
}

