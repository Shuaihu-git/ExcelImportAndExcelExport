package cn.excel.exercute;


import cn.excel.po.Student;
import cn.excel.service.Impl.StudentServerImpl;
import cn.excel.service.StudentServer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramTest {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        System.out.println("请您输入操作数：1 读取本地excel文件，并将数据导入到数据库中 2 读取数据库中的表数据并写入本地的excel文件");
        //根据不同的数据做不同的操作
        int num = sc.nextInt();
        StudentServer studentServer = new StudentServerImpl();
        if(num == 1){
            //读取excel文件中的内容
            System.out.println("请您输入需要读取的excel文件的路径");
            String path = "D:\\TempExcel\\students.xlsx";
            //将封装好的对象信息导入到数据库中
            List<Student> read = read(path);
            //将List<Student>解析并写入数据库中
            studentServer.parse(read);
            System.out.println("文件写入数据库完成");
        }else if(num == 2){
            //查找数据库中student表的信息，并将数据封装成一个studentList集合
            List<Student> studentList = studentServer.findAll();
            //将studentList集合中的数据写入磁盘
            System.out.println("请您输入需要写入的文件路径：");
            //通过控制台输入路径
            String path = "D:\\TempExcel\\students.xlsx";
            //将封装好的对象进行解析，并写入本地excel文件
            write(studentList,path);
            //程序运行完毕后提示运行成功
            System.out.println("写入成功");
        }else {
            System.out.println("请您重新输入");
        }
    }

    /**
     * 读取excel文件后，将一行数据封装成一个Student对象，多行返回一个List对象即可
     * param path 输出的文件路径
     */
    public static List<Student> read(String path){
        XSSFWorkbook xssfWorkbook = null;
        List<Student> studentList = new ArrayList<>();
        try {
            //创建工作簿
            xssfWorkbook = new XSSFWorkbook("D:\\TempExcel\\studentstoup.xlsx");
            SXSSFWorkbook sxssfWorkbook=new SXSSFWorkbook(xssfWorkbook,-1);

            //创建工作表
            XSSFSheet sheet = sxssfWorkbook.getXSSFWorkbook().getSheetAt(0);
            //获取最后一行是第几行
            int lastRowNum = sheet.getLastRowNum();
            //由于第一行是字段名称，不做读取，后面建表的时候生成字段，因此这里从第二行开始读取，注意第二行的下标是1
            for (int i = 1; i <= lastRowNum; i++) {
                //获取行
                XSSFRow row = sheet.getRow(i);
                //进行行的非空判断后，在遍历，避免空指针
                if(row != null){
                    //实例化一个List集合，用于存放一行读取出来的所有单元数据
                    List<String> list = new ArrayList<>();
                    //遍历行
                    for (Cell cell : row) {
                        //获得单元格，对单元格进行非空判断
                        if(cell!= null){
                            //设置单元格数据的类型为字符串，这样即使表中数据有其他类型，也不用考虑类型转换，考虑不周有可能发生的类型转换异常
                            cell.setCellType(CellType.STRING);
                            //获取单元格的数据
                            String value = cell.getStringCellValue();
                            //将每个单元格数据加入List集合中

                            list.add(value);
                        }
                        System.out.println(list);
                    }
                    //将读取出的每个行的数据封装成一个Student对象
                    String  a=String.valueOf(list.get(0));
                    System.out.println(new String(a).replaceAll("\\.0", ""));
                    System.out.println(a);
                    Student student = new Student(Integer.parseInt(String.valueOf(list.get(0).replaceAll("\\.0", ""))), list.get(1), Integer.parseInt(String.valueOf(list.get(2)).replaceAll("\\.0", "")), list.get(3), list.get(4));
                    //将每个Student对象添加到studentList集合中，相当于一个studentList集合装的就是一个sheet表的数据
                    studentList.add(student);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public static void write(List<Student> studentList,String path){
        System.out.println(studentList);
        //创建一个工作簿
        SXSSFWorkbook xssfWorkbook = new SXSSFWorkbook();
        //创建一个表
        Sheet student = xssfWorkbook.createSheet("学生信息");
        //创建第一行
        Row row1 = student.createRow(0);
        //为第一行的每个单元格赋值
        row1.createCell(0).setCellValue("编号");
        row1.createCell(1).setCellValue("姓名");
        row1.createCell(2).setCellValue("年龄");
        row1.createCell(3).setCellValue("性别");
        row1.createCell(4).setCellValue("爱好");
        FileOutputStream out = null;
        try {
            //创建一个输出流
            out = new FileOutputStream(path);
            //遍历studentList集合，并为每行的每一个单元格赋值
            for (int i = 0; i < studentList.size(); i++) {
                Row row = student.createRow(i + 1);
                row.createCell(0).setCellValue(studentList.get(i).getId());
                row.createCell(1).setCellValue(studentList.get(i).getName());
                row.createCell(2).setCellValue(studentList.get(i).getAge());
                row.createCell(3).setCellValue(studentList.get(i).getSex());
                row.createCell(4).setCellValue(studentList.get(i).getHabit());
            }
            xssfWorkbook.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try{
                if(out != null){
                    out.close();
                }
                if(xssfWorkbook != null){
                    xssfWorkbook.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
