package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.bo.Voiture;

import java.util.ArrayList;
import java.util.List;


public class VoituresDAO {

    private static final String TAG = VoituresDAO.class.getName();

    private GestionVoituresHelper helper = null;
    private SQLiteDatabase db = null;

    private static final String[] ALL_COLUMN = new String[]{GestionVoituresContract.Voitures._ID,
            GestionVoituresContract.Voitures.COL_NAME_NOM,
            GestionVoituresContract.Voitures.COL_NAME_PRIX,
            GestionVoituresContract.Voitures.COL_NAME_PLAQUE,
            GestionVoituresContract.Voitures.COL_NAME_LOUEE };

    public VoituresDAO(Context contexte) {
        helper = new GestionVoituresHelper(contexte);
    }


    public void insert(Voiture voiture) {

        // Ouvrir la connexion
        db = helper.getWritableDatabase();

        // Preparer la requete
        ContentValues valeurs = new ContentValues();
        valeurs.put(GestionVoituresContract.Voitures.COL_NAME_NOM, voiture.getvNom());
        valeurs.put(GestionVoituresContract.Voitures.COL_NAME_PRIX, voiture.getvPrix());
        valeurs.put(GestionVoituresContract.Voitures.COL_NAME_PLAQUE, voiture.getvPlaque());
        valeurs.put(GestionVoituresContract.Voitures.COL_NAME_LOUEE, voiture.isLouee());

        // Passer la requete
        long rowId = db.insert(GestionVoituresContract.Voitures.TABLE_NAME, null, valeurs);

        if (rowId == -1)
        {
            Log.w(TAG, "L'insertion n'a pas eu lieu.");
        }
        Log.d(TAG, "rowID = " + rowId);

        // Fermer la connexion
        helper.close();

    }

    public void update(Voiture voiture)
    {
        Log.d(TAG, "Entrée dans update() = " + voiture.toString());
        // Ouvrir la connexion
        db = helper.getWritableDatabase();

        // Preparer la requete
        ContentValues valeurs = new ContentValues();
        valeurs.put(GestionVoituresContract.Voitures.COL_NAME_NOM, voiture.getvNom());
        valeurs.put(GestionVoituresContract.Voitures.COL_NAME_PRIX, voiture.getvPrix());
        valeurs.put(GestionVoituresContract.Voitures.COL_NAME_PLAQUE, voiture.getvPlaque());
        valeurs.put(GestionVoituresContract.Voitures.COL_NAME_LOUEE, voiture.isLouee());

        String whereClause =  GestionVoituresContract.Voitures._ID+" == ?";
        String[] whereParams = new String[]{Integer.toString(voiture.getId())};

        // Passer la requete
        int nbLignesModifiess = db.update(GestionVoituresContract.Voitures.TABLE_NAME, valeurs, whereClause, whereParams);

        Log.d(TAG, "nbLignesModifiess = " + nbLignesModifiess);

        // Fermer la connexion
        helper.close();

    }

    public void delete(Voiture voiture)
    {
        // Ouvrir la connexion
        db = helper.getWritableDatabase();

        String whereClause =  GestionVoituresContract.Voitures._ID+" == ?";
        String[] whereParams = new String[]{Integer.toString(voiture.getId())};

        // Passer la requete
        int nbLignesModifiess = db.delete(GestionVoituresContract.Voitures.TABLE_NAME, whereClause, whereParams);

        // Fermer la connexion
        helper.close();
    }

    public ArrayList<Voiture> get(boolean tri)
    {
        Log.d(TAG, "Entrée dans get(). Tri = " + tri);
        ArrayList<Voiture> resultat = new ArrayList<Voiture>();

        // Ouvrir la connexion
        db = helper.getWritableDatabase();

        String orderBy = tri?GestionVoituresContract.Voitures.COL_NAME_PRIX + " ASC":"";

        Log.d(TAG, "Dans get(). orderBy = " + orderBy);

        Cursor curseur = db.query(GestionVoituresContract.Voitures.TABLE_NAME, ALL_COLUMN, null, null, null, null, orderBy);

        while (curseur.moveToNext())
        {
            Voiture voiture =  new Voiture(curseur.getInt(0), // id
                    curseur.getString(1), // Nom
                    curseur.getString(3),// Plaque
                    curseur.getString(2), // Prix
                    curseur.getInt(4)==GestionVoituresContract.Voitures.VALUE_LOUEE_TRUE // Louee
            );
            Log.d(TAG, "Dans get(). voiture = " + voiture.toString());
            resultat.add(voiture);
        }

        curseur.close();

        // Fermer la connexion
        helper.close();
        return resultat;
    }

    public Voiture get(int id)
    {
        Voiture resultat = new Voiture();

        // Ouvrir la connexion
        db = helper.getWritableDatabase();

        String whereClause =  GestionVoituresContract.Voitures._ID+" == ?";
        String[] whereParams = new String[]{Integer.toString(id)};

        Cursor curseur = db.query(GestionVoituresContract.Voitures.TABLE_NAME, ALL_COLUMN, whereClause, whereParams, null, null, null);

        if (curseur.moveToNext())
        {
            resultat = new Voiture(curseur.getInt(0), // id
                    curseur.getString(1), // Nom
                    curseur.getString(2),// Plaque
                    curseur.getString(3), // Prix
                    curseur.getInt(4)==GestionVoituresContract.Voitures.VALUE_LOUEE_TRUE // Louee
            );
        }

        curseur.close();

        // Fermer la connexion
        helper.close();
        return resultat;
    }
}
