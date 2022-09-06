package cn.excel.service.Impl;

import cn.excel.dao.Impl.StudentDaoImpl;
import cn.excel.dao.StudentDao;
import cn.excel.po.Student;
import cn.excel.service.StudentServer;

import java.util.List;
/**
 * @Author 张帅虎
 * @Data 2022/9/6
 */
public class StudentServerImpl implements StudentServer {
    private StudentDao studentDao = new StudentDaoImpl();

    /**
     * 遍历List<Student>，对里面的每一个produce对象调用parse方法
     * @param read
     */
    @Override
    public void parse(List<Student> read) {
        for (Student student : read) {
            studentDao.parse(student);
        }
    }

    /**
     * 查找student表中的所有数据，并返回成一个List对象
     * @return
     */
    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public List<String> saveList() {
        return studentDao.saveList();
    }
}
