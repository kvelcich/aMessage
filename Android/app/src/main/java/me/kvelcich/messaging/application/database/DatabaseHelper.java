package me.kvelcich.messaging.application.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import me.kvelcich.messaging.application.classes.Message;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "Database.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.MessageTable.SQL_CREATE_MESSAGES);
        sqLiteDatabase.execSQL(DatabaseContract.ChatTable.SQL_CREATE_MESSAGES);
        sqLiteDatabase.execSQL(DatabaseContract.UserTable.SQL_CREATE_MESSAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DatabaseContract.MessageTable.SQL_DELETE_MESSAGES);
        sqLiteDatabase.execSQL(DatabaseContract.ChatTable.SQL_DELETE_MESSAGES);
        sqLiteDatabase.execSQL(DatabaseContract.UserTable.SQL_DELETE_MESSAGES);
        onCreate(sqLiteDatabase);
    }
}