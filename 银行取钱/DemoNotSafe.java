package 银行取钱;

public class DemoNotSafe {
    public static void main(String[] args) {
        AccountNotSafe account = new AccountNotSafe("123", "zhangsan");
        account.setMoney(5000f);
        Thread t1 = new Thread(new ThreadNotSafe(account, 5000f));
        Thread t2 = new Thread(new ThreadNotSafe(account, 5000f));
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
