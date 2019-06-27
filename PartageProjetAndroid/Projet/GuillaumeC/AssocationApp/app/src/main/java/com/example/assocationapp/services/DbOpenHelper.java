package com.example.assocationapp.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DbOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ASSOCIATION_PING";

    public DbOpenHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String createPeople =
                "CREATE TABLE personnes(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, prenom TEXT, password TEXT, email TEXT)";

        sqLiteDatabase.execSQL(createPeople);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
