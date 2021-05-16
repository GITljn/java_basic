package container;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Maps_ {
    public static void testHashMap() {
        // 由于key不能重复，因此key为自定义类型时，应重写hashcode和equals
        HashMap<String, String> 德云社 = new HashMap<>();
        德云社.put("郭德纲", "于谦");
        德云社.put("岳云鹏", "孙越");
        德云社.put("孟鹤堂", "周九良");
        德云社.put("烧瓶", "郎鹤严");
        德云社.put("郭麒麟", "阎鹤祥");
        // 键值可以为null，HashTable不可以
        德云社.put(null, null);
        System.out.println("HashMap");
        System.out.println(德云社.containsKey("曹云金"));
        System.out.println("通过键找值的方式遍历：");
        for (String s : 德云社.keySet()) {
            System.out.println("捧哏：" + s + "    逗哏：" + 德云社.get(s));
        }
        德云社.remove("郭麒麟");
        System.out.println("通过Entry的方式遍历：");
        for (Map.Entry<String, String> names : 德云社.entrySet()) {
            System.out.println("捧哏：" + names.getKey() + "    逗哏：" + names.getValue());
        }
    }

    public static void testLinkedHashMap() {
        // 由于key不能重复，因此key为自定义类型时，应重写hashcode和equals
        LinkedHashMap<String, String> 德云社 = new LinkedHashMap<>();
        德云社.put("郭德纲", "于谦");
        德云社.put("岳云鹏", "孙越");
        德云社.put("孟鹤堂", "周九良");
        德云社.put("烧瓶", "郎鹤严");
        德云社.put("郭麒麟", "阎鹤祥");
        // 键值可以为null，HashTable不可以
        德云社.put(null, null);
        System.out.println("LinkedHashMap");
        System.out.println(德云社.containsKey("曹云金"));
        System.out.println("通过键找值的方式遍历：");
        for (String s : 德云社.keySet()) {
            System.out.println("捧哏：" + s + "    逗哏：" + 德云社.get(s));
        }
        德云社.remove("郭麒麟");
        System.out.println("通过Entry的方式遍历：");
        for (Map.Entry<String, String> names : 德云社.entrySet()) {
            System.out.println("捧哏：" + names.getKey() + "    逗哏：" + names.getValue());
        }
    }

    public static void main(String[] args) {
        testHashMap();
        testLinkedHashMap();
    }
}
