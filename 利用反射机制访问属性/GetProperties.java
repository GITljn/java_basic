package 利用反射机制访问属性;

import java.util.ResourceBundle;

public class GetProperties {
    public static void main(String[] args) {
        // 获取绝对路径
//        String path = Thread.currentThread().getContextClassLoader().getResource("druid.properties").getPath();
//        System.out.println(path);
//        Properties pro = new Properties();
//        try {
//            pro.load(new FileInputStream(path));
//            for (String field : pro.stringPropertyNames()) {
//                System.out.println(field + "=" + pro.getProperty(field));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // 直接获取流
//        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("druid.properties");
//        Properties pro = new Properties();
//        try {
//            pro.load(is);
//            for (String field : pro.stringPropertyNames()) {
//                System.out.println(field + ":" + pro.getProperty(field));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // 资源绑定器
        ResourceBundle bundle = ResourceBundle.getBundle("druid");
        String maxActive = bundle.getString("maxActive");
        System.out.println(maxActive);
    }
}
