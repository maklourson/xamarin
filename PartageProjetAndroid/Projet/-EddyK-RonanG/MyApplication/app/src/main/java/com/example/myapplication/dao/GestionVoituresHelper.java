package com.example.myapplication.dao;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestionVoituresHelper extends SQLiteOpenHelper
{

    private static final String DATABASE_NAME = "GestionVoitures.db";
    private static final int DATABASE_VERSION = 1;

    public GestionVoituresHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_VOITURES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP_TABLE_VOITURES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion); // Idem onUpgrade()
    }


    private static final String TYPE_TEXT = " TEXT ";
    private static final String TYPE_INT = " INTEGER ";
    private static final String TYPE_FLOAT = " REAL ";
    private static final String TYPE_BOOLEAN = " BOOLEAN ";
    private static final String VIRGULE = ", ";

    private static final String SQL_CREATE_TABLE_VOITURES =
            "CREATE TABLE " + GestionVoituresContract.Voitures.TABLE_NAME + " (" +
                    GestionVoituresContract.Voitures._ID + " INTEGER PRIMARY KEY," +
                    GestionVoituresContract.Voitures.COL_NAME_NOM + TYPE_TEXT + VIRGULE +
                    GestionVoituresContract.Voitures.COL_NAME_PLAQUE + TYPE_TEXT + VIRGULE +
                    GestionVoituresContract.Voitures.COL_NAME_PRIX + TYPE_TEXT + VIRGULE +
                    GestionVoituresContract.Voitures.COL_NAME_LOUEE + TYPE_INT +");";

    private static final String SQL_DROP_TABLE_VOITURES = "DROP TABLE IF EXISTS " + GestionVoituresContract.Voitures.TABLE_NAME;
}