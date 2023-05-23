package hr.fer.ChatbotService.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @Column(name = "chat_user_id")
    private String userId;
    private ArrayList<String> allMessages;


    public Chat() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<String> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(ArrayList<String> allMessages) {
        this.allMessages = allMessages;
    }
}
