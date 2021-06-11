package 生产者消费者;

import java.util.List;

public class Producer extends Thread{
    private List list;

    public Producer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            synchronized (list) {
                if (list.size() < 3) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Object o = new Object();
                    list.add(o);
                    if (list.size() == 3) {
                        list.notify();
                    }
                    System.out.println(Thread.currentThread().getName());
                } else {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
