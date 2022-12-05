package cz.uhk.pro2.chat;

import com.github.javafaker.Faker;
import cz.uhk.pro2.chat.gui.ChatTableModel;
import cz.uhk.pro2.chat.model.Message;
import cz.uhk.pro2.chat.services.ChatService;
import cz.uhk.pro2.chat.services.FakeChatService;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChatMainWindow extends javax.swing.JFrame {

    private final List<Message> messages = new java.util.ArrayList<>();
    private ChatTableModel chatTableModel = new ChatTableModel(messages);

    private ChatService chatService = new FakeChatService();

    public ChatMainWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() {
        JTable t = new JTable(chatTableModel);
        add(new JScrollPane(t), java.awt.BorderLayout.CENTER);
        pack();

        createFakeMessages();

    }

    private void createFakeMessages() {
        var faker = new Faker();
        for (int i = 0; i < 10; i++) {
            messages.add(new Message(
                    faker.name().fullName(),
                    faker.name().fullName(), faker.lorem().sentence(),
                    faker.date().past(10, TimeUnit.MINUTES).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        }
        chatTableModel.fireTableDataChanged();
    }

    private void refreshMessages() {
        messages.addAll(chatService.getMyMessages(1));
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