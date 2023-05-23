package hr.fer.ChatbotService.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//@Entity
//@Table(name = "messages")
public class Message {

    public String userId;
    public String text;
    public String from;
    public String to;
    public String displayName;


    public Message() {
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
