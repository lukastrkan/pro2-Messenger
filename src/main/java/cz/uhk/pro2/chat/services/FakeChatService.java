package cz.uhk.pro2.chat.services;

import cz.uhk.pro2.chat.model.Message;
import cz.uhk.pro2.chat.model.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeChatService implements ChatService {

    private List<Message> messages = new ArrayList<>();

    @Override
    public boolean login(String username, String password) {
        return true;
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
        //TODO: show only messages from me
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
