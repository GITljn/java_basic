package 多态;

public class Demo {
    public static void main(String[] args) {
        Master master = new Master();
        Dog dog = new Dog();
        Cat cat = new Cat();
        YingWu yingWu = new YingWu();
        master.feed(dog);
        master.feed(cat);
        master.feed(yingWu);
    }
}
