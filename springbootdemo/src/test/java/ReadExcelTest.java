import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;

/**
 * 使用poi读取excel文件中的内容
 */
public class ReadExcelTest {//读取Excel测试
    public static void main(String[] args) {
        XSSFWorkbook workbook = null;
        try {
            //创建工作簿
            workbook = new XSSFWorkbook("D:/test1.xlsx");
            //获取工作表
            XSSFSheet sheet = workbook.getSheetAt(0);
            //遍历工作表
            for (Row cells : sheet) {
                //遍历行，获取单元格
                for (Cell cell : cells) {
                    //获取单元格的值,判断是数字还是字符串，然后分别输出
                    if(cell.getCellType() == CellType.NUMERIC){
                        System.out.print(cell.getNumericCellValue() + '\t');
                    }else if(cell.getCellType() == CellType.STRING){
                        System.out.print(cell.getStringCellValue()+ '\t');
                    }
                }
                //每打印一行就换一行
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭资源
                if (workbook != null) {
                    workbook.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
