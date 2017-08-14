package me.kvelcich.messaging.application.classes;

public class Message {
    private int chatId;
    private int senderId;
    private String content;
    private String timestamp;

    public Message(int chatId, int senderId, String content, String timestamp) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Message(int senderId, String content, String timestamp) {
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getChatId() {
        return chatId;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Boolean isSent() {
        return senderId == -1;
    }
}
