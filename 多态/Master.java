package 多态;

public class Master {
    public void feed(Pet pet) {
        if (pet instanceof Dog) {
            Dog dog = (Dog) pet;
            dog.eat();
        } else if (pet instanceof Cat) {
            Cat cat = (Cat) pet;
            cat.eat();
        } else if (pet instanceof YingWu) {
            YingWu yingWu = (YingWu) pet;
            yingWu.eat();
        }
    }
}
