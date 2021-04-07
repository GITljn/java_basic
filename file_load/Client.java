package file_upload;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        FileInputStream fis = new FileInputStream("basic_project\\src\\file_upload\\psnr.png");
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] bytes = new byte[1024];
        int len;
        while((len=bis.read(bytes))!=-1) {
            bos.write(bytes, 0, len);
        }
        bos.flush();
        socket.shutdownOutput();
//        已经将套接字的输出流禁用了
//        bos.close();
        InputStream is = socket.getInputStream();
        len = is.read(bytes);
        System.out.println(new String(bytes, 0, len));
        bis.close();
        fis.close();
//        客户端关闭的同时会将客户端的字节流一同关闭
        socket.close();
    }
}
