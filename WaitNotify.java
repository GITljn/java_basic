public class WaitNotify {
    public static void main(String[] args) {
        Object obj = new Object();
        new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("顾客：小二来20个包子");
                    try {
                        obj.wait();
                        System.out.println("顾客：真香");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj) {
                    System.out.println("小二：这位爷，您的包子");
                    obj.notify();
                }
            }
        }.start();
    }
}
