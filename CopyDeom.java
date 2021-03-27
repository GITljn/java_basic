import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyDeom {
    public static void main(String[] args) throws IOException {
        File from = new File("D:\\master\\课程作业");
        File to = new File("C:\\Users\\ljn\\Desktop");
        copyPath(from, to);
    }

    public static void copyPath(File from, File to) throws IOException {
        if (!to.exists())
            to.mkdirs();
        File new_to = new File(to, from.getName());
        new_to.mkdir();
        File[] path = from.listFiles();
        for (File file : path) {
            if (file.isFile())
                copyFile(file, new File(new_to, file.getName()));
            else {
                copyPath(file, new_to);
            }
        }
    }

    public static void copyFile (File from, File to) throws IOException {
        FileInputStream fis = new FileInputStream(from);
        FileOutputStream fos = new FileOutputStream(to);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fis.close();
        fos.close();
    }
}
