package cn.excel.service;

import cn.excel.po.Student;

import java.util.List;

public interface StudentServer {
    /**
     * 对传入的List<Student>中的每个Student调用parse方法
     * @param read
     */
    void parse(List<Student> read);

    /**
     * 查询student表中的所有数据
     * @return
     */
    List<Student> findAll();
}
