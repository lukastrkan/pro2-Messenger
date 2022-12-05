package cz.uhk.pro2.chat.services;

import cz.uhk.pro2.chat.model.Message;
import cz.uhk.pro2.chat.model.Room;

import java.util.List;

public interface ChatService {
    /**
     * Try to login user with given username and password
     * @param username username
     * @param password password
     * @return true if login was successful, false otherwise
     */
    boolean login(String username, String password);

    /**
     * Send message to given room
     * @param message message to send
     * @param roomId id of room to send message to
     */
    void sendMessage(Message message, int roomId);

    /**
     * Search for messages in given room
     * @param term term to search for
     * @param roomId id of room to search in
     * @return list of messages
     */
    List<Message> search(String term, int roomId);

    /**
     * Get all messages in given room
     * @param roomId id of room to get messages from
     * @return list of messages
     */
    List<Message> getMyMessages(int roomId);

    /**
     * Get all rooms
     * @return list of rooms
     */
    List<Room> getAllRooms();
}
