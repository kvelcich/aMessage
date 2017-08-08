package me.kvelcich.messaging.application.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import me.kvelcich.messaging.application.classes.Message;

public class MessageHandler {

    public MessageHandler(Context context) {
    }

    public void insertMessage(Message message) {
    }

    public ArrayList<Message> getMessages(String contact) {
        ArrayList<Message> messages = new ArrayList<Message>();

        messages.add(new Message("Kevin", "Hey friend", true));
        messages.add(new Message("Kevin", "Do you want to grab some snacks?", true));
        messages.add(new Message("Kevin", "Nah.", false));
        messages.add(new Message("Kevin", "That's cool too", true));

        return messages;
    }
}