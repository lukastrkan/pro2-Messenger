package cz.uhk.pro2.chat.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import cz.uhk.pro2.chat.model.Message;
import cz.uhk.pro2.chat.model.Room;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.List;

public class HttpChatService implements ChatService {
    String token = "";

    @Override
    public boolean login(String username, String password) {
        var data = Form.form().add("username", username).add("password", password).build();

        String response = null;
        try {
            response = Request.Post("https://chat-http-server.herokuapp.com/login")
                    .bodyForm(data).execute().returnContent().asString();
            if (response.equals("")) {
                return false;
            } else {
                token = response;
                return true;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMessage(Message message, int roomId) {
        try {
            Request.Post("https://chat-http-server.herokuapp.com/message")
                    .bodyForm(
                            Form.form().add("token", token)
                                    .add("to", message.getTo())
                                    .add("text", message.getText())
                                    .add("roomId", String.valueOf(roomId))
                                    .build()).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Message> search(String term, int roomId) {
        return null;
    }

    @Override
    public List<Message> getMyMessages(int roomId) {
        try {
            var jsonStr = Request.Get("https://chat-http-server.herokuapp.com/messages?token=" + token + "&roomId=" + roomId)
                    .execute().returnContent().asString();

            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .registerModule(new JavaTimeModule());

            return mapper.readValue(jsonStr, new TypeReference<List<Message>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Room> getAllRooms() {
        return null;
    }
}
