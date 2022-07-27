package cc.honghuan.jucdemo.netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author honghuan.Liu
 * @date 2022/7/27 10:44
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务端已启动");

        while (true) {
            Socket socket = serverSocket.accept();
            executorService.execute(() -> {
                myHandle(socket);
            });
        }

    }

    private static void myHandle(Socket socket) {
        System.out.println("当前线程：" + Thread.currentThread().getName());
        byte[] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("当前通信线程：" + Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if(read!=-1){
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
