package cz.uhk.pro2.chat.model;

import java.time.LocalDateTime;

//Message - from String, to String, msg String, datetime LocalDateTime,
public class Message {
    private String from;
    private String to;
    private String msg;
    private LocalDateTime datetime;

    public Message(String from, String to, String msg, LocalDateTime datetime) {
        this.from = from;
        this.to = to;
        this.msg = msg;
        this.datetime = datetime;
    }

    public Message(){}

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getMsg() {
        return msg;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", msg='" + msg + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
