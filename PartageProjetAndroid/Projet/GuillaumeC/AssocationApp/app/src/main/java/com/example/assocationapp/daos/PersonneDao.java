package com.example.assocationapp.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assocationapp.models.Personne;
import com.example.assocationapp.services.DbOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PersonneDao {
    private SQLiteDatabase db;

    public PersonneDao(Context context){
        db = new DbOpenHelper(context).getWritableDatabase();
    }

    public Personne getById(long id){
        Personne personne = new Personne();

        Cursor cursor = db.query(
                "personnes",
                new String[]{"id", "nom", "prenom", "password", "email"},
                "id=?",
                new String[]{String.valueOf(id)},
                null, null, null);

        if(cursor.moveToFirst()){
            personne.setNom(cursor.getString(cursor.getColumnIndex("nom")));
            personne.setPrenom(cursor.getString(cursor.getColumnIndex("prenom")));
            personne.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            personne.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        }

        return personne;
    }

    public List<Personne> getList(){
        Cursor cursor = db.query(
                "personnes",
                new String[]{"id", "nom", "prenom", "password", "email"},
                null,
                null,
                null, null, null);

        List<Personne> personnes = new ArrayList<>();

        while(cursor.moveToNext()){
            Personne personne = new Personne();
            personne.setNom(cursor.getString(cursor.getColumnIndex("nom")));
            personne.setPrenom(cursor.getString(cursor.getColumnIndex("prenom")));
            personne.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            personne.setEmail(cursor.getString(cursor.getColumnIndex("email")));

            personnes.add(personne);
        }

        return personnes;
    }

    public int push(Personne personne) {
        if (personne.getIdentifier() != -1) {
            return this.update(personne);
        } else {
            return (int) this.insert(personne);
        }
    }

    public int update(Personne personne){
        ContentValues content = new ContentValues();
        content.put("nom", personne.getNom());
        content.put("prenom", personne.getPrenom());
        content.put("password", personne.getPassword());
        content.put("email", personne.getEmail());

        return db.update(
                "personne",
                content,
                "id=?",
                new String[] {String.valueOf(personne.getId())});
    }

    public long insert(Personne personne){
        ContentValues values = new ContentValues();
        values.put("nom", personne.getNom());
        values.put("prenom", personne.getPrenom());
        values.put("password", personne.getPassword());
        values.put("email", personne.getEmail());

        return db.insert("personnes", null, values);
    }

    public int delete (Personne personne) {
        return db.delete(
                "people",
                "id=?",
                new String[] { String.valueOf(personne.getId()) }
        );
    }
}
