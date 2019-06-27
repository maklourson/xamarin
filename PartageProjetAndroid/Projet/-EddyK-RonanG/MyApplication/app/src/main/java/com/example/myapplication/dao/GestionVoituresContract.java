package com.example.myapplication.dao;

import android.provider.BaseColumns;


public class GestionVoituresContract
{
    // Structure de la table VOITURES
    public abstract static class Voitures implements BaseColumns
    {
        // BaseColumns déclare déjà la colonne "_id"
        public static final String TABLE_NAME 		= "voitures";
        public static final String COL_NAME_NOM 	= "nom";
        public static final String COL_NAME_PRIX 	= "prix";
        public static final String COL_NAME_LOUEE	= "louee";
        public static final String COL_NAME_PLAQUE	= "plaque";


        public static final int VALUE_LOUEE_TRUE = 1;
        public static final int VALUE_LOUEE_FALSE = 0;
    }
}
