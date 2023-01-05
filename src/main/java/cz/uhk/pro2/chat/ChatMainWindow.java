package cz.uhk.pro2.chat;

import com.github.javafaker.Faker;
import cz.uhk.pro2.chat.gui.ChatTableModel;
import cz.uhk.pro2.chat.model.Message;
import cz.uhk.pro2.chat.services.ChatService;
import cz.uhk.pro2.chat.services.FakeChatService;
import cz.uhk.pro2.chat.services.HttpChatService;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChatMainWindow extends javax.swing.JFrame {

    private final List<Message> messages = new java.util.ArrayList<>();
    private ChatTableModel chatTableModel = new ChatTableModel();
    private ChatService chatService = new HttpChatService();
    private int roomId = 2;

    private Timer timer;

    public ChatMainWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() {
        chatTableModel.setMessages(messages);
        JTable t = new JTable(chatTableModel);
        JButton b = new JButton("Send");
        JTextField tf = new JTextField(20);
        JPanel p = new JPanel();
        p.add(tf);
        p.add(b);
        add(new JScrollPane(t), java.awt.BorderLayout.CENTER);
        add(p, java.awt.BorderLayout.SOUTH);
        pack();

        b.addActionListener(e -> {
            var message = new Message(
                    "",
                    "",
                    tf.getText(),
                    LocalDateTime.now()

            );
            tf.setText("");
            chatService.sendMessage(message, roomId);
        });

        String username = JOptionPane.showInputDialog("Enter username");
        String password = JOptionPane.showInputDialog("Enter password");
        var success = chatService.login(username, password);
        if (!success) {
            JOptionPane.showMessageDialog(this, "Login failed");
            System.exit(1);
        }

        timer = new Timer(2000, e -> {
            System.out.println("Tick");
             refreshMessages();
        });
        timer.start();

        //createFakeMessages();
    }

    private void createFakeMessages() {
        var faker = new Faker();
        for (int i = 0; i < 10; i++) {
            messages.add(new Message(
                    faker.pokemon().name(),
                    faker.name().fullName(), faker.lorem().sentence(),
                    faker.date().past(10, TimeUnit.MINUTES).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        }
        chatTableModel.fireTableDataChanged();
    }

    private void refreshMessages() {
        messages.clear();
        messages.addAll(chatService.getMyMessages(roomId));
        chatTableModel.fireTableDataChanged();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var window = new ChatMainWindow();
            window.init();
            window.setSize(500, 500);
            window.setVisible(true);
        });
    }


}