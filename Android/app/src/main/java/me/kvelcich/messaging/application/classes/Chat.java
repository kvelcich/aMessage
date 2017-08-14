package me.kvelcich.messaging.application.classes;

public class Chat {

    private int chatId;
    private String userIds;
    private String timestamp;

    public Chat(int chatId, String userIds, String timestamp) {
        this.chatId = chatId;
        this.userIds = userIds;
        this.timestamp = timestamp;
    }

    public int getChatId() {
        return chatId;
    }

    public String getUserIds() {
        return userIds;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
