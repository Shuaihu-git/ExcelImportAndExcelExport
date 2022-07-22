package cn.excel.dao;

import cn.excel.po.Student;

import java.util.List;

public interface StudentDao {
    /**
     * 解析每一个 Student对象
     * @param student
     */
    void parse(Student student) ;

    /**
     * 查询student表中所有字段信息
     * @return
     */
    List<Student> findAll();


    List<String> saveList();

}
