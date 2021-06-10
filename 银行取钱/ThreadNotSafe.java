package 银行取钱;

public class ThreadNotSafe implements Runnable{
    private AccountNotSafe account;
    private double withDrawMoney;

    public ThreadNotSafe(AccountNotSafe account, double withDrawMoney) {
        this.account = account;
        this.withDrawMoney = withDrawMoney;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "开始取钱，当前余额为：" + account.getMoney());
        boolean flag = account.withdraw(withDrawMoney);
        if (flag) {
            System.out.println(name + "提取到的金额为：" + withDrawMoney);
        } else {
            System.out.println(name + "提取到的金额为：" + 0);
        }
        System.out.println(name + "取钱结束，当前余额为：" + account.getMoney());
    }
}
