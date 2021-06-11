package 两个线程交替输出连续的整数;

public class ThreadB extends Thread{
    private Int i;

    public ThreadB(Int i) {
        this.i = i;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (i) {
                if (i.getI() % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + "-->" + i.getI());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i.setI(i.getI() + 1);
                    i.notify();
                } else {
                    try {
                        i.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
