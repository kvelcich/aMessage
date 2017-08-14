package me.kvelcich.messaging.application.database.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import me.kvelcich.messaging.application.classes.Chat;
import me.kvelcich.messaging.application.classes.User;
import me.kvelcich.messaging.application.database.DatabaseContract;

public class UserImpl {

    public static void insertChat(SQLiteDatabase db, Context context, String contact) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.MessageTable.CONTENT, contact);

        db.insert(DatabaseContract.MessageTable.TABLE_NAME, null, values);
    }

    public static User retrieveUser(SQLiteDatabase db, Context context, int userId) {
        String query = "SELECT " + DatabaseContract.UserTable._ID + ", " +
                DatabaseContract.UserTable.CONTACT + ", " +
                " FROM " + DatabaseContract.UserTable.TABLE_NAME +
                " WHERE " + DatabaseContract.UserTable._ID + " = " + Integer.toString(userId);
        Cursor cursor = db.rawQuery(query, null);

        User user = null;
        while (cursor.moveToFirst()) {
            user = new User(
                    cursor.getInt(cursor.getColumnIndex(DatabaseContract.UserTable._ID)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.UserTable.CONTACT))
            );
        }

        cursor.close();
        return user;
    };
}
