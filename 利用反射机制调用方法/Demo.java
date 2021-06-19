package 利用反射机制调用方法;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) {
        try {
            Class studentC = Class.forName("利用反射机制调用方法.Student");
            // 调用空参的构造方法
            Object s1 = studentC.newInstance();
            Method setName = studentC.getDeclaredMethod("setName", String.class);
            setName.invoke(s1, "张三");
            Method learning = studentC.getDeclaredMethod("learning", String.class);
            learning.invoke(s1, "数学");
            // 调用有参的构造方法
            Constructor student = studentC.getConstructor(String.class, int.class);
            Object s2 = student.newInstance("李四", 12);
            learning.invoke(s2, "语文");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
