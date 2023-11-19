package two;
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int serverPort = 8081;

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // 添加联系人
            writer.println("ADD:John Doe:123 Main St:555-1234");

            // 获取联系人信息
            writer.println("GET:John Doe");

            // 在这里可以添加修改和删除联系人的操作，类似于上面的ADD和GET操作
            // writer.println("UPDATE:John Doe:456 New St:555-5678");
            // writer.println("DELETE:John Doe");

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
