import cn.excel.utils.TimeControl;

import java.util.Timer;

public class TimeTest {
    public static void main(String[] args) {
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new TimeControl(),0,1000*60*3);
    }

}
