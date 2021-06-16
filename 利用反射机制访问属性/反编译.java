package 利用反射机制访问属性;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class 反编译 {
    public static void main(String[] args) throws Exception{
        Class s = Class.forName("java.lang.String");
        System.out.println("public class " + s.getSimpleName() +" {");
        for (Field field : s.getDeclaredFields()) {
            if (field.getModifiers() == 0) {
                System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName() + ";");
            } else {
                System.out.println("\t" + Modifier.toString(field.getModifiers()) + " " +
                        field.getType().getSimpleName() + " " + field.getName() + ";");
            }
        }
        System.out.println("}");
    }
}
