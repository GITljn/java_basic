package 利用反射机制访问属性;

import java.lang.reflect.Field;

public class FieldTest {
    public static void main(String[] args) {
        try {
            Class s = Class.forName("利用反射机制访问属性.Student");
//            System.out.println(s.getSimpleName());
//            for (Field field : s.getDeclaredFields()) {
//                System.out.println(Modifier.toString(field.getModifiers()) + "\t" +
//                        field.getType().getSimpleName() + "\t" + field.getName());
//            }
            Object o = s.newInstance();
            Field name = s.getDeclaredField("id");
            name.setAccessible(true);
            name.set(o, 0);
            System.out.println(name.get(o));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
