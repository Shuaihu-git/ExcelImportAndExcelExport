package cn.excel.dao.Impl;

import cn.excel.dao.StudentDao;
import cn.excel.po.Student;
import cn.excel.utils.DBUtil_druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    Connection connection = null;
    private int age;

    public StudentDaoImpl() {}

    @Override
    public void parse(Student student) {
        PreparedStatement ps = null;
        //如果把获取连接对象的代码作为成员变量，可能会导致有一些代码还需要使用这个连接，时连接已经被关闭的情况，运行代码的过程中遇到了这个坑，所以在这里做个记录，把获取连接对象的代码
        connection = DBUtil_druid.getConnection();
        try {
            String sql = "insert into students values(?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,student.getId());
            ps.setString(2,student.getName());
            ps.setInt(3,student.getAge());
            ps.setString(4,student.getSex());
            ps.setString(5,student.getHabit());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Student> findAll() {
        String sql = "select id,name,age,sex,habit from students";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;
        connection = DBUtil_druid.getConnection();
        List<Student> studentList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String sex = rs.getString(4);
                String habit = rs.getString(5);
                studentList.add(new Student(id,name,age,sex,habit));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }


    @Override
    public List<String> saveList() {
        String sql = "select id,name,age,sex,habit from students";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;
        connection = DBUtil_druid.getConnection();
        List<String> studentList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                int age = rs.getInt(3);
                String sex = rs.getString(4);
                String habit = rs.getString(5);
                studentList.add(id+"|"+name+"|"+age+"|"+sex+"|"+habit+"|\r\n");

            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }
}
