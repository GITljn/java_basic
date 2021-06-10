package 银行取钱;

public class AccountNotSafe {
    private String id;
    private String name;
    private double money;

    public AccountNotSafe(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public boolean withdraw(double money) {
        double before = this.money;
        if (before >= money) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.money = before - money;
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
