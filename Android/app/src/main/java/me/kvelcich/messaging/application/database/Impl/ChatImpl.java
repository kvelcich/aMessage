package me.kvelcich.messaging.application.database.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import me.kvelcich.messaging.application.classes.Chat;
import me.kvelcich.messaging.application.database.DatabaseContract;

public class ChatImpl {

    public static boolean insertChat(SQLiteDatabase db, String userIds) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ChatTable.USER_IDS, userIds);

        return db.insert(DatabaseContract.ChatTable.TABLE_NAME, null, values) != -1;
    }

    public static List<Chat> retrieveChats(SQLiteDatabase db) {
        String query = "SELECT " + DatabaseContract.ChatTable._ID + ", " +
                DatabaseContract.ChatTable.USER_IDS + ", " +
                DatabaseContract.ChatTable.TIMESTAMP +
                " FROM " + DatabaseContract.ChatTable.TABLE_NAME +
                " ORDER BY " + DatabaseContract.ChatTable.TIMESTAMP + " DESC";
        Cursor cursor = db.rawQuery(query, null);

        List<Chat> chats = new ArrayList<>();
        while (cursor.moveToNext()) {
            chats.add( new Chat(
                    cursor.getInt(cursor.getColumnIndex(DatabaseContract.ChatTable._ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ChatTable.USER_IDS)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ChatTable.TIMESTAMP))
            ));
        }

        cursor.close();
        return chats;
    }

    public static Chat retrieveChat(SQLiteDatabase db, int chatId) {
        String query = "SELECT " + DatabaseContract.ChatTable._ID + ", " +
                DatabaseContract.ChatTable.USER_IDS + ", " +
                DatabaseContract.ChatTable.TIMESTAMP +
                " FROM " + DatabaseContract.ChatTable.TABLE_NAME +
                " WHERE " + DatabaseContract.ChatTable._ID + " = " + Integer.toString(chatId);
        Cursor cursor = db.rawQuery(query, null);

        Chat chat = null;
        while (cursor.moveToFirst()) {
            chat = new Chat(
                    cursor.getInt(cursor.getColumnIndex(DatabaseContract.ChatTable._ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ChatTable.USER_IDS)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ChatTable.TIMESTAMP))
            );
        }

        cursor.close();
        return chat;
    };
}
