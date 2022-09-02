import cn.excel.utils.TimeControl;

import java.util.Timer;

public class TimeTest {//测试定时任务读取数据库数据至.dat文件中
    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimeControl(),0,1000*60*3);//3分钟执行一次
    }

}
