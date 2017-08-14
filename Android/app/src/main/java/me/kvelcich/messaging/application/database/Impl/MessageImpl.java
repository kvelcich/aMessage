package me.kvelcich.messaging.application.database.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import me.kvelcich.messaging.application.classes.Message;
import me.kvelcich.messaging.application.database.DatabaseContract;

public class MessageImpl {

    public static boolean insertMessage(SQLiteDatabase db, int chatId, int senderId, String content) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.MessageTable.CHAT_ID, chatId);
        values.put(DatabaseContract.MessageTable.SENDER_ID, senderId);
        values.put(DatabaseContract.MessageTable.CONTENT, content);

        return (db.insert(DatabaseContract.MessageTable.TABLE_NAME, null, values) != -1);
    }

    public static List<Message> retrieveMessages(SQLiteDatabase db, int chatId) {
        String query = "SELECT " + DatabaseContract.MessageTable.SENDER_ID + ", " +
                DatabaseContract.MessageTable.CONTENT + ", " +
                DatabaseContract.MessageTable.TIMESTAMP +
                " FROM " + DatabaseContract.MessageTable.TABLE_NAME +
                " WHERE " + DatabaseContract.MessageTable.CHAT_ID + " = " + Integer.toString(chatId) +
                " ORDER BY " + DatabaseContract.MessageTable.TIMESTAMP + " DESC";
        Cursor cursor = db.rawQuery(query, null);

        List<Message> messages = new ArrayList<>();
        while (cursor.moveToNext()) {
            messages.add( new Message(
                    cursor.getInt(cursor.getColumnIndex(DatabaseContract.MessageTable.SENDER_ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.MessageTable.CONTENT)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.MessageTable.TIMESTAMP))
            ));
        }

        cursor.close();
        return messages;
    }
}
