package me.kvelcich.messaging.application.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    private DatabaseContract() {}

    public static class MessageTable implements BaseColumns {
        public static final String TABLE_NAME = "message";
        public static final String CHAT_ID = "chat_id";
        public static final String SENDER_ID = "sender_id";
        public static final String CONTENT = "content";
        public static final String TIMESTAMP = "timestamp";

        public static final String SQL_CREATE_MESSAGES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CHAT_ID + " INTEGER, " +
                SENDER_ID + " INTEGER, " +
                CONTENT + " TEXT, " +
                TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

        public static final String SQL_DELETE_MESSAGES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String CONTACT = "contact";

        public static final String SQL_CREATE_MESSAGES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        CONTACT + " TEXT)";

        public static final String SQL_DELETE_MESSAGES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static class ChatTable implements BaseColumns {
        public static final String TABLE_NAME = "chat";
        public static final String USER_IDS = "user_ids";
        public static final String TIMESTAMP = "timestamp";

        public static final String SQL_CREATE_MESSAGES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                USER_IDS + " TEXT," +
                TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

        public static final String SQL_DELETE_MESSAGES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
