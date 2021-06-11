package 生产者消费者;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        List list = new ArrayList();
        Producer t1 = new Producer(list);
        Thread t2 = new Thread(new Consumer(list));
        t1.setName("生产者");
        t2.setName("消费者");
        t1.start();
        t2.start();
    }
}
