package CS3;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;

public class Client extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JButton viewButton, addButton, updateButton, deleteButton;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client() {
        setTitle("个人通讯录系统");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        JPanel buttonPanel = new JPanel();
        viewButton = new JButton("查看联系人");
        viewButton.addActionListener(this);
        addButton = new JButton("添加联系人");
        addButton.addActionListener(this);
        updateButton = new JButton("修改联系人");
        updateButton.addActionListener(this);
        deleteButton = new JButton("删除联系人");
        deleteButton.addActionListener(this);
        buttonPanel.add(viewButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, "South");

        setVisible(true);

        try {
            socket = new Socket("localhost", 8888);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewButton) {
            out.println("view");
            try {
                String response = in.readLine();
                textArea.setText(response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == addButton) {
            String name = JOptionPane.showInputDialog("请输入姓名：");
            String address = JOptionPane.showInputDialog("请输入住址：");
            String phone = JOptionPane.showInputDialog("请输入电话：");
            out.println("add," + name + "," + address + "," + phone);
            try {
                String response = in.readLine();
                JOptionPane.showMessageDialog(this, response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == updateButton) {
            String name = JOptionPane.showInputDialog("请输入姓名：");
            String address = JOptionPane.showInputDialog("请输入新的住址：");
            String phone = JOptionPane.showInputDialog("请输入新的电话：");
            out.println("update," + name + "," + address + "," + phone);
            try {
                String response = in.readLine();
                JOptionPane.showMessageDialog(this, response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == deleteButton) {
            String name = JOptionPane.showInputDialog("请输入姓名：");
            out.println("delete," + name);
            try {
                String response = in.readLine();
                JOptionPane.showMessageDialog(this, response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
