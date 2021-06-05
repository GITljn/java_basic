package army;

import java.util.ArrayList;

public class Army {
    private ArrayList<Weapon> weapons;
    private int weaponNum;

    public Army(int n) {
        weapons = new ArrayList<>();
        weaponNum = n;
    }

    public void addWeapon(Weapon weapon) throws WeaponNumException {
        if (weapons.size() == weaponNum) {
            throw new WeaponNumException("武器已经达到最大数量");
        }
        weapons.add(weapon);
    }

    public void attackAll() {
        boolean flag = false;
        for (Weapon weapon : weapons) {
            if (weapon instanceof Gun) {
                flag = true;
                ((Gun) weapon).attack();
            }
        }
        if (!flag) {
            System.out.println("没有可以用于攻击的武器");
        }
    }

    public void moveAll() {
        boolean flag = false;
        for (Weapon weapon : weapons) {
             if (weapon instanceof Car) {
                 flag = true;
                ((Car) weapon).move();
            }
        }
        if (!flag) {
            System.out.println("没有可以用于移动的武器");
        }
    }
}
