package 定时器;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class Demo {
    public static void main(String[] args) {
        Timer timer = new Timer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date time = sdf.parse("2021-06-11 16:45:25");
            timer.schedule(new MyTimerTask(), time, 1000 * 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
