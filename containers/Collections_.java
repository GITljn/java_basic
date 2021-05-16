package container;

import java.util.*;

public class Collections_ {
    // list的特点：顺序存取
    public static void testArrayList() {
        ArrayList<String> 蘑菇屋 = new ArrayList<>();
//        蘑菇屋.add("何囧");
//        蘑菇屋.add("黄磊");
        // 一次添加多条元素
        Collections.addAll(蘑菇屋, "何炅", "黄磊");
        蘑菇屋.add("彭昱畅");
        蘑菇屋.add("张子枫");
        蘑菇屋.add("刘宪华");

        蘑菇屋.set(0, "何炅");

        // 按对象删除
        蘑菇屋.remove("刘宪华");
        // 按索引删除
        蘑菇屋.remove(蘑菇屋.size() - 1);
        蘑菇屋.add("张子枫");
        if (蘑菇屋.contains("刘宪华")) {
            System.out.println("有刘宪华");
        } else {
            System.out.println("没有刘宪华");
        }

        System.out.println("普通for循环，按添加顺序输出：");
        for (int i = 0; i < 蘑菇屋.size(); i++) {
            System.out.println(蘑菇屋.get(i));
        }

        System.out.println("增强for循环，按打乱顺序输出：");
        Collections.shuffle(蘑菇屋);
        for(String name : 蘑菇屋) {
            System.out.println(name);
        }

        System.out.println("使用迭代器遍历, 按排序顺序输出：");
        Collections.sort(蘑菇屋, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        Iterator<String> moguwu_iterator = 蘑菇屋.iterator();
        while (moguwu_iterator.hasNext()) {
            System.out.println(moguwu_iterator.next());
        }

        System.out.println("将ArrayList转换为数组");
        Object[] names_moguwu = 蘑菇屋.toArray();
        for (Object name : names_moguwu) {
            System.out.println(name);
        }

        蘑菇屋.clear();
        System.out.println("蘑菇屋的人数为：" + 蘑菇屋.size());

        if (蘑菇屋.isEmpty()) {
            System.out.println("蘑菇屋没有人了");
        }
    }

    public static void testLinkedList() {
        // 主要是一些对首尾的操作
        LinkedList<String> 快乐家族 = new LinkedList<>();
        快乐家族.add("何炅");
        快乐家族.add("谢娜");
        快乐家族.add("李维嘉");
        快乐家族.addFirst("吴昕");
        快乐家族.addLast("杜海涛");
        // 可以利用索引，但不是直接得到目标地址
        快乐家族.add(快乐家族.size() - 1, "无名氏");
        System.out.println(快乐家族.getFirst());
        System.out.println(快乐家族.getLast());
        快乐家族.removeFirst();
        快乐家族.removeLast();
        for (String name : 快乐家族) {
            System.out.println(name);
        }
    }

    // Set的特点：不允许有重复的元素,因此存储自定义类型的元素时必须重写hashcode和equals函数
    public static void testHashSet() {
        // 哈希表：数组+链表/红黑树，速度快
        HashSet<String> 吐槽大会 = new HashSet<>();
        吐槽大会.add("张绍刚");
        吐槽大会.add("李诞");
        吐槽大会.add("王建国");
        吐槽大会.add("池子");
        吐槽大会.add("王建国");
        吐槽大会.remove("池子");
        for (String name : 吐槽大会) {
            System.out.println(name);
        }
    }

    public static void testLinkedHashSet() {
        // 哈希表：数组+链表/红黑树+链表，另外一个链表用于记录存储的顺序
        LinkedHashSet<String> 吐槽大会 = new LinkedHashSet<>();
        吐槽大会.add("张绍刚");
        吐槽大会.add("李诞");
        吐槽大会.add("王建国");
        吐槽大会.add("池子");
        吐槽大会.add("王建国");
        吐槽大会.remove("池子");
        for (String name : 吐槽大会) {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        testArrayList();
        testLinkedList();
        testHashSet();
        testLinkedHashSet();
    }
}
