package two;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private static Map<String, Contact> contacts = new HashMap<>();

    public static void main(String[] args) {
        int port = 8888;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务器已启动，等待客户端连接...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("客户端已连接：" + clientSocket);

                // 处理客户端请求的线程
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket clientSocket;
        private BufferedReader reader;
        private PrintWriter writer;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            try {
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                String request;
                while ((request = reader.readLine()) != null) {
                    String[] requestData = request.split(":");
                    String command = requestData[0];

                    switch (command) {
                        case "ADD":
                            String name = requestData[1];
                            String address = requestData[2];
                            String phoneNumber = requestData[3];
                            contacts.put(name, new Contact(name, address, phoneNumber));
                            writer.println("Contact added successfully!");
                            break;
                        case "GET":
                            String contactName = requestData[1];
                            Contact contact = contacts.get(contactName);
                            if (contact != null) {
                                writer.println(contact.toString());
                            } else {
                                writer.println("Contact not found!");
                            }
                            break;
                        // 同样，可以添加修改和删除联系人的功能，类似于上面的ADD和GET操作
                        // case "UPDATE":
                        //     // 更新联系人信息
                        //     break;
                        // case "DELETE":
                        //     // 删除联系人
                        //     break;
                        default:
                            writer.println("Invalid command!");
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Contact {
        private String name;
        private String address;
        private String phoneNumber;

        public Contact(String name, String address, String phoneNumber) {
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Address: " + address + ", Phone Number: " + phoneNumber;
        }
    }
}
