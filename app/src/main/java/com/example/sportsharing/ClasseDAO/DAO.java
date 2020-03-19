package com.example.sportsharing.ClasseDAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper {

    public DAO(Context context) {
        super(context, ParamDAO.base, null, ParamDAO.version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //SportifDAO
            //Reste Connecte permet de charger le profil dont la valeur est 1
        db.execSQL("create table Sportif (" +
                "login VARCHAR(25) PRIMARY KEY," +
                "motDePasse VARCHAR(25) NOT NULL," +
                "confirmMotDePasse VARCHAR(25) NOT NULL," +
                "nom VARCHAR(25) NOT NULL," +
                "prenom VARCHAR(25) NOT NULL," +
                "adresseMail VARCHAR(250) NOT NULL," +
                "dateNaissance VARCHAR(12) NOT NULL," +
                "ville VARCHAR(60) NOT NULL," +
                "codePostal MEDIUMINT(5) NOT NULL," +
                "numeroTelephone VARCHAR(12) NOT NULL," +
                "description TEXT NOT NULL," +
                "resteConnecte INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
