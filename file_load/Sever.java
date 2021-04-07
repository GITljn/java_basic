package file_upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever {
    public static void main(String[] args) throws IOException {
        ServerSocket sever = new ServerSocket(8888);
        // 使服务器一直处于监听状态，死循环accept
        while (true) {
            // 只有当客户端发起请求时才会往下执行，否则将一直阻塞
            Socket socket = sever.accept();
            // 不要忘记开启线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        FileInputStream fis = (FileInputStream) socket.getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(fis);
                        FileOutputStream fos = new FileOutputStream("basic_project\\src\\file_upload\\psnr.jpg");
                        BufferedOutputStream bos = new BufferedOutputStream(fos);
                        byte[] bytes = new byte[1024];
                        int len;
                        while ((len=bis.read(bytes))!=-1) {
                            bos.write(bytes, 0, len);
                        }
                        OutputStream os = socket.getOutputStream();
                        os.write("上传完成！".getBytes());
                        bos.flush();
                        bos.close();
                        fos.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
