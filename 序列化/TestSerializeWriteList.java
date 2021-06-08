package 序列化;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TestSerializeWriteList {
    public static void main(String[] args) {
        Student s1 = new Student(1, "zhangsan");
        Student s2 = new Student(2, "lisi");
        Student s3 = new Student(3, "wangwu");
        ArrayList<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("basic_project/src/序列化/students_list"));
            oos.writeObject(students);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
