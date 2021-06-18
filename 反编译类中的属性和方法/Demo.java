package 反编译类中的属性和方法;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class Demo {
    public static void main(String[] args) {
        // public class Stu {}
        StringBuilder sb = new StringBuilder();
        try {
//            Class stuC = Class.forName("反编译类中的属性和方法.Stu");
            Class stuC = Class.forName("java.lang.String");
            sb.append(Modifier.toString(stuC.getModifiers()));
            sb.append(" class ");
            sb.append(stuC.getSimpleName());
            sb.append(" {\n");
            // 反编译属性
            Field[] fields = stuC.getDeclaredFields();
            for (Field field : fields) {
                // private static String birth;
                sb.append("\t");
                sb.append(Modifier.toString(field.getModifiers()));
                if (field.getModifiers() != 0) {
                    sb.append(" ");
                }
                sb.append(field.getType().getSimpleName());
                sb.append(" ");
                sb.append(field.getName());
                sb.append(";\n");
            }
            // 反编译方法
            Method[] methods = stuC.getDeclaredMethods();
            for (Method method : methods) {
                // public void learning(String name) {}
                sb.append("\t");
                sb.append(Modifier.toString(method.getModifiers()));
                if (method.getModifiers() != 0) {
                    sb.append(" ");
                }
                sb.append(method.getReturnType());
                sb.append(" ");
                sb.append(method.getName());
                sb.append("(");
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    sb.append(parameter.getType().getSimpleName());
                    sb.append(",");
                }
                if (parameters.length > 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                sb.append(") {\n\t}\n");
            }
            sb.append("\n}");
            System.out.println(sb);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
