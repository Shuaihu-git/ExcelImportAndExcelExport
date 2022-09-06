package cn.excel.utils;

import cn.excel.service.Impl.StudentServerImpl;
import cn.excel.service.StudentServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.UUID;
/**
 * @Author 张帅虎
 * @Data 2022/9/6
 */
public class TimeControl extends TimerTask  {
    @Override
    public void run() {
        StudentServer studentServer=new StudentServerImpl();
        List<String> list=studentServer.saveList();
        System.out.println(list);
        File file=new File("C:\\Users\\张帅虎\\Desktop\\out.dat");
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);


            String temp= String.valueOf(list);

                temp=temp.replaceAll("\\[","");
                temp=temp.replaceAll("\\]","");
                temp=temp.replaceAll("\\,","");
            System.out.println(temp);

            fileOutputStream.write(temp.getBytes());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
