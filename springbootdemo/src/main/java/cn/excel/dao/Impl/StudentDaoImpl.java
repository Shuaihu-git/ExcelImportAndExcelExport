package cn.excel.dao.Impl;

import cn.excel.dao.StudentDao;
import cn.excel.po.Student;
import cn.excel.utils.DBUtil_druid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    Connection connection = null;
    private int age;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StudentDaoImpl() {}

    @Override
    public void parse(Student student) {
        PreparedStatement ps = null;
        //如果把获取连接对象的代码作为成员变量，可能会导致有一些代码还需要使用这个连接，时连接已经被关闭的情况，运行代码的过程中遇到了这个坑，所以在这里做个记录，把获取连接对象的代码
        connection = DBUtil_druid.getConnection();
        try {
            String sql = "insert into student values(?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,student.getId());
            ps.setString(2,student.getName());
            ps.setInt(3,student.getAge());
            ps.setString(4,student.getSex());
            ps.setString(5,student.getHabit());
            ps.setInt(6,student.getDepartmentid());
            ps.setInt(7,student.getCourseid());
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
    public void batchInsert(List<Student> students){
        PreparedStatement ps = null;
        //如果把获取连接对象的代码作为成员变量，可能会导致有一些代码还需要使用这个连接，时连接已经被关闭的情况，运行代码的过程中遇到了这个坑，所以在这里做个记录，把获取连接对象的代码
        connection = DBUtil_druid.getConnection();
        String sql ="insert into student values(?,?,?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1,students.get(i).getId());
                ps.setString(2,students.get(i).getName());
                ps.setInt(3,students.get(i).getAge());
                ps.setString(4,students.get(i).getSex());
                ps.setString(5,students.get(i).getHabit());
                ps.setInt(6,students.get(i).getDepartmentid());
                ps.setInt(7,students.get(i).getCourseid());
            }

            @Override
            public int getBatchSize() {
                return students.size();
            }
        });
    }

    @Override
    public List<Student> findAll() {
        String sql = "select id,name,age,sex,habit,department_id, course_id from student";
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
                Integer departmentid = rs.getInt(6);
                Integer courseid = rs.getInt(7);
                studentList.add(new Student(id,name,age,sex,habit, departmentid, courseid));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }


    @Override
    public List<String> saveList() {
        String sql = "select id,name,age,sex,habit,department_id, course_id from student";
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

                Integer departmentid = rs.getInt(6);

                Integer courseid = rs.getInt(7);

                studentList.add(id+"|"+name+"|"+age+"|"+sex+"|"+habit+"|"+departmentid+"|"+courseid+"\r\n");

            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }
}
