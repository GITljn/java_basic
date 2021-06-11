package 定时器;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sdf.format(date);
        System.out.println(time);
    }
}
