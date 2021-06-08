package 序列化;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class TestSerializeReadList {
    public static void main(String[] args) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("basic_project/src/序列化/students_list"));
            Object o = ois.readObject();
            if (o instanceof ArrayList) {
                ArrayList<Student> students = (ArrayList<Student>)o;
                for (Student student : students) {
                    System.out.println(student);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
