package com.example.happybaby;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATA_BASE_VERSION = 1;

    public static final String DATA_BASE_NAME = "Login.db";

    public static final String TABLE_NAME = "users";

    private static final String USERNAME = "username";

    private static final String PASSWORD = "password";
    //private static final String KEY_ID = "id";T

    public DBHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + USERNAME + "TEXT PRIMARY KEY," + PASSWORD + "TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    public Boolean insertData( /*String id,*/ String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(KEY_ID, id);
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) return false;
        else
            return true;

    }

    public void create(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // values.put(KEY_ID, user.getId());
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where USERNAME = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password =?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public int update(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());

        int message = db.update(TABLE_NAME, values, USERNAME + " =?",
                new String[]{String.valueOf(user.getUsername())});
        db.close();
        return message;

    }

    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        String quarry = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(quarry, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setUsername(cursor.getString(0));
                user.setPassword(cursor.getString(1));
                users.add(user);
            } while (cursor.moveToNext());

        }
        db.close();
        return users;
    }
    public void delete(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, USERNAME + " = ?",
                new String[]{String.valueOf(user.getUsername())});
        db.close();
    }
}
