package me.kvelcich.messaging.application.classes;

public class User {
    private int userId;
    private String contact;

    public User(int userId, String contact) {
        this.userId = userId;
        this.contact = contact;
    }

    public User(String contact) {
        this.contact = contact;
    }

    public int getUserId() {
        return userId;
    }

    public String getContact() {
        return contact;
    }
}
