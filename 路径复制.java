package day17;

import java.io.*;

public class 路径复制 {
    public static void main(String[] args) {
        File from = new File("D:\\master\\Java");
        File to = new File("C:\\Users\\ljn\\Desktop");
        copyPath(from, to);
    }

    public static void copyPath(File from, File to) {
        if (from.isFile()) {
            try {
                copyFile(from, to);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            File pathTo = new File(to, from.getName());
            pathTo.mkdirs();
            File[] files = from.listFiles();
            for (File file : files) {
                copyPath(file, pathTo);
            }
        }
    }

    public static void copyFile(File from, File to) throws Exception {
        if (!from.isFile()) {
            throw new Exception("不是文件");
        }
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(from));
            bos = new BufferedOutputStream(new FileOutputStream(new File(to, from.getName())));
            int len;
            byte[] bytes = new byte[1024];
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
