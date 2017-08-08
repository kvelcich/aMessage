package me.kvelcich.messaging.application.classes;

import java.io.Serializable;
import java.util.Calendar;

public class Message implements Serializable {
    public String contact;
    public String content;
    public boolean sent;
    public long time;

    public Message() {
    }
    public Message(String contact, String content, boolean sent) {
        this.contact = contact;
        this.content = content;
        this.sent = sent;
        this.time = Calendar.getInstance().getTimeInMillis();
    }
}