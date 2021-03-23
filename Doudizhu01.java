package day06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Doudizhu01 {
    public static void main(String[] args) {
        String[] colors = {"♠", "♥", "♣", "♦"};
        String[] sizes = {"2", "1", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        ArrayList<String> sizeAndColor = new ArrayList<>();
        sizeAndColor.add("大王");
        sizeAndColor.add("小王");
        for (String size : sizes) {
            for (String color : colors) {
                sizeAndColor.add(color + size);
            }
        }
//        Collections.reverse(sizeAndColor);
        System.out.println(sizeAndColor);
        HashMap<Integer, String> sizeAndColorEncode = new HashMap<>();
        for (int i = 0; i < sizeAndColor.size(); i++) {
            sizeAndColorEncode.put(i, sizeAndColor.get(i));
        }
        System.out.println(sizeAndColorEncode);
        ArrayList<Integer> playerA = new ArrayList<>();
        ArrayList<Integer> playerB = new ArrayList<>();
        ArrayList<Integer> playerC = new ArrayList<>();
        ArrayList<Integer> dipai = new ArrayList<>();
        ArrayList<Integer> numEncode = new ArrayList<>();
        for (int i = 0; i < 54; i++) {
            numEncode.add(i);
        }
        Collections.shuffle(numEncode);
        for (int i = 0; i < numEncode.size(); i++) {
            if (i > 50) {
                dipai.add(numEncode.get(i));
                continue;
            }
            if (i % 3 == 1) {
                playerA.add(numEncode.get(i));
            } else if (i % 3 == 2) {
                playerB.add(numEncode.get(i));
            } else {
                playerC.add(numEncode.get(i));
            }
        }
        Collections.sort(playerA);
        Collections.sort(playerB);
        Collections.sort(playerC);
        Collections.sort(dipai);
        System.out.println("玩家A的牌为：");
        for (Integer integer : playerA) {
            System.out.print(sizeAndColorEncode.get(integer) + "  ");
        }
        System.out.println("\n玩家B的牌为：");
        for (Integer integer : playerB) {
            System.out.print(sizeAndColorEncode.get(integer) + "  ");
        }
        System.out.println("\n玩家C的牌为：");
        for (Integer integer : playerC) {
            System.out.print(sizeAndColorEncode.get(integer) + "  ");
        }
        System.out.println("\n底牌为：");
        for (Integer integer : dipai) {
            System.out.print(sizeAndColorEncode.get(integer) + "  ");
        }
    }
}
