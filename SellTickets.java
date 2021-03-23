package sell_tickets;

public class SellTickets implements Runnable{
    private int tickets = 100;
//    锁对象不能出现在run里面，否则不是相同的锁对象
    Object obj = new Object();
    @Override
    public void run() {
//        while不能在同步代码块里面，否则会全部被一个线程卖掉
        while (true) {
//            等待程序不能写在同步代码块中，否则等待的时候其他线程也不会执行
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj) {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + "正在卖出第" + tickets + "张票");
                    tickets--;
                }
            }
            if (tickets <= 0) {
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + "票已售空");
    }
}
