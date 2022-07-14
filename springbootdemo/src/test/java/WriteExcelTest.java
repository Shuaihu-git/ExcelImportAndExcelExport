import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用poi将数据写入excel文件中
 */
public class WriteExcelTest {
    public static void main(String[] args) {

        XSSFWorkbook xssfWorkbook = null;
        FileOutputStream out = null;
        try {
            //创建工作簿
            xssfWorkbook = new XSSFWorkbook();
            //创建表
            XSSFSheet xssfSheet = xssfWorkbook.createSheet("first_sheet");
            //创建行，这里创建四行
            XSSFRow row1 = xssfSheet.createRow(0);
            XSSFRow row2 = xssfSheet.createRow(1);
            XSSFRow row3 = xssfSheet.createRow(2);
            XSSFRow row4 = xssfSheet.createRow(3);
            //为第一行创建单元格并赋值
            row1.createCell(0).setCellValue("姓名");
            row1.createCell(1).setCellValue("年龄");
            row1.createCell(2).setCellValue("性别");
            //为第二行创建单元格并赋值
            row2.createCell(0).setCellValue("张三");
            row2.createCell(1).setCellValue("18");
            row2.createCell(2).setCellValue("男");
            //为第三行创建单元格并赋值
            row3.createCell(0).setCellValue("李四");
            row3.createCell(1).setCellValue("17");
            row3.createCell(2).setCellValue("男");
            //为第四行创建单元格并赋值
            row4.createCell(0).setCellValue("黄蓉");
            row4.createCell(1).setCellValue("20");
            row4.createCell(2).setCellValue("女");
            //创建一个输出流
            out = new FileOutputStream("D:/output.xlsx");
            //将创建的工作簿内容写入磁盘
            xssfWorkbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭资源,需做非空判断
                if (out != null) {
                    out.close();
                }
                if (xssfWorkbook != null) {
                    xssfWorkbook.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("输出成功！");
    }
}
