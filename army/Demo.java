package army;

public class Demo {
    public static void main(String[] args) {
        Army army = new Army(5);
        army.attackAll();
        army.moveAll();
        try {
            army.addWeapon(new Gun());
            army.addWeapon(new Gun());
            army.addWeapon(new Gun());
            army.addWeapon(new Car());
            army.addWeapon(new Car());
            army.addWeapon(new Car());
        } catch (WeaponNumException e) {
//            e.getMessage();
            System.out.println(e.getMessage());
        }
        army.attackAll();
        army.moveAll();
    }
}
