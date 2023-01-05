package cz.uhk.pro2.chat.model;

import java.time.LocalDateTime;

//Message - from String, to String, msg String, datetime LocalDateTime,
public class Message {
    private String from;
    private String to;
    private String text;
    private LocalDateTime localDateTime;

    public Message(String from, String to, String text, LocalDateTime localDateTime) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.localDateTime = localDateTime;
    }

    public Message(){}

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", msg='" + text + '\'' +
                ", datetime=" + localDateTime +
                '}';
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
