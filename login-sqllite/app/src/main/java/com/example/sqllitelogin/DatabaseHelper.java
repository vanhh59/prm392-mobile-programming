package com.example.sqllitelogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ANHLPV_FPT_Database.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và cột
    public static final String TABLE_USER = "Tbl_user";
    public static final String COLUMN_EMAIL = "Email";
    public static final String COLUMN_PASSWORD = "pass";
    public static final String COLUMN_REPASSWORD = "repass";

    // Hash password
    public static final String HASH_PASSWORD = "HASH_PASSWORD";


    // Tạo bảng SQL
    private static final String TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " (" +
                    COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_REPASSWORD + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
