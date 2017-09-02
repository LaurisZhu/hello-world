package nmid.zhu.service;

import nmid.zhu.db.DBOperator;
import nmid.zhu.pojo.Manager;
import nmid.zhu.pojo.Student;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Lawrence on 2017/8/30.
 */
public class UserService {
    public boolean login(String id, String password) {
        DBOperator dbOperator = null;
        try {
            dbOperator = new DBOperator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Manager> managers = (ArrayList<Manager>) dbOperator.getPeople("manager");
        Iterator<Manager> iterator = managers.iterator();
        while (iterator.hasNext()) {
            Manager oneManager = iterator.next();
            if (oneManager.getWorkId().equals(id)) {
                return oneManager.getPassword().equals(password);
                }
        }
        return false;
        }

    public synchronized boolean addStudents(Student newStudent) {
        DBOperator dbOperator = null;
        try {
            dbOperator = new DBOperator();
            if (dbOperator.addStudetns(newStudent));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized boolean deleteStudent(String id) {
        DBOperator dbOperator = null;
        try {
            dbOperator = new DBOperator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dbOperator.deleteStudetns(id)) {
            return true;
        }
        return false;
    }

    public synchronized boolean modifyStudent(Student oneStudent) {
        DBOperator dbOperator = null;
        try {
            dbOperator = new DBOperator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dbOperator.modifyStudent(oneStudent)) {
            return true;
        }
        return false;
    }

    public ArrayList<Student> findStudents(String name, String id) {
        DBOperator dbOperator = null;
        try {
            dbOperator = new DBOperator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Student> students = new ArrayList<>();
        if (!id.equals("")) {
            Student oneStudent = dbOperator.getStudentByID(id);
            if (oneStudent == null) return null;
            students.add(oneStudent);
        } else if(!name.equals("")) {
            students = dbOperator.getStudentsByName(name);
        } else {
            return null;
        }
        return students;
    }
}
