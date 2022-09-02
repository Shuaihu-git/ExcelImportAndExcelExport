import cn.excel.po.Student;
import cn.excel.service.Impl.StudentServerImpl;
import cn.excel.service.StudentServer;
import com.alibaba.druid.sql.visitor.functions.Char;
import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {//测试.dat文件读出带有分隔符”|“的数据导入数据库中
    public static void main(String[] args) throws Exception {
        File file=new File("C:\\Users\\张帅虎\\Desktop\\in.dat");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        String  string="1|zhangsan|22|男|学习|\r\n2|lisi|23|女|打游戏|\r\n";
        fileOutputStream.write(string.getBytes());
        FileReader  fileReader=new FileReader(file);
        char [] bytes=new char[1024];
        int length=fileReader.read(bytes);
        String tmp=new String(bytes,0,length);
        System.out.println(tmp);
        int row=tmp.split("\r\n").length;
        System.out.println(row);
        String split[]=tmp.split("\\|");
        for (String t:split
             ) {
            System.out.println(t);
        }
        System.out.println(row);
        List<Student> studentList=new ArrayList<>();
        for (String temp:split
             ) {
            temp.replaceAll("\r\n","");
        }

        for (int i = 0; i < row; i++) {
            if (i==1){
                i=5;
                split[5]=split[5].replaceAll("\r\n","");
                List<Student> list=new ArrayList<>();
                Student student = new Student();
                student.setId(Integer.parseInt(split[i]));
                student.setName(split[i+1]);
                student.setAge(Integer.parseInt(split[i+2]));
                student.setSex(split[i+3]);
                student.setHabit(split[i+4]);
                list.add(student);
                System.out.println(list);
                StudentServer studentServer=new StudentServerImpl();
                studentServer.parse(list);
            }
                List<Student> list=new ArrayList<>();
                Student student = new Student();
                student.setId(Integer.parseInt(split[i]));
                student.setName(split[i+1]);
                student.setAge(Integer.parseInt(split[i+2]));
                student.setSex(split[i+3]);
                student.setHabit(split[i+4]);
                list.add(student);
                System.out.println(list);
                StudentServer studentServer=new StudentServerImpl();
                studentServer.parse(list);
        }
            System.out.println("插入数据成功");

    }
}
