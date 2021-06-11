package 两个线程交替输出连续的整数;

public class Demo {
    public static void main(String[] args) {
        Int i = new Int();
        ThreadA t1 = new ThreadA(i);
        t1.setName("t1");
        ThreadB t2 = new ThreadB(i);
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
