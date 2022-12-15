package cz.uhk.pro2.chat.services;

import cz.uhk.pro2.chat.model.Message;
import cz.uhk.pro2.chat.model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FakeChatService implements ChatService {

    private List<Message> messages = new ArrayList<>();

    @Override
    public boolean login(String username, String password) {
        return username.equals("admin") && password.equals("admin");
    }

    @Override
    public void sendMessage(Message message, int roomId) {
        messages.add(message);
    }

    @Override
    public List<Message> search(String term, int roomId) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<Message> getMyMessages(int roomId) {

        return Collections.unmodifiableList(messages);
    }

    @Override
    public List<Room> getAllRooms() {
        var tmp = new ArrayList<Room>();
        tmp.add(new Room("PRO 1", 1));
        tmp.add(new Room("PRO 2", 2));
        tmp.add(new Room("Hospoda", 3));
        return tmp;
    }
}
