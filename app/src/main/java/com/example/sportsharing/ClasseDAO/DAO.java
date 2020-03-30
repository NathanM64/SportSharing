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
                "dateNaissance VARCHAR(10) NOT NULL," +
                "ville VARCHAR(60) NOT NULL," +
                "codePostal MEDIUMINT(5) NOT NULL," +
                "numeroTelephone VARCHAR(12) NOT NULL," +
                "description TEXT NOT NULL," +
                "resteConnecte INTEGER NOT NULL);");

        //SportDAO
        db.execSQL("create table Sport (" +
                "libelle VARCHAR(25) PRIMARY KEY," +
                "type VARCHAR(25) NOT NULL);");

        //Activite
        db.execSQL("create table Activite(" +
                "id INTEGER PRIMARY KEY," +
                "jour VARCHAR(10) NOT NULL," +
                "heureDebut VARCHAR(5) NOT NULL," +
                "heureFin VARCHAR(5) NOT NULL," +
                "description TEXT NOT NULL," +
                "nbMaxPersonnes INTEGER NOT NULL," +
                "estTermine INTEGER NOT NULL DEFAULT 0," +
                "idCreateur VARCHAR(25) NOT NULL," +
                "libelleSport VARCHAR(25) NOT NULL," +
                "niveauSport VARCHAR(25) NOT NULL," +
                "idAdresse INTEGER NOT NULL," +
                "FOREIGN KEY (idCreateur) REFERENCES Sportif(login)," +
                "FOREIGN KEY(libelleSport) REFERENCES Sport(libelle)," +
                "FOREIGN KEY(idAdresse) REFERENCES Adresse(id));");

        //Adresse
        db.execSQL("create table Adresse (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "numero INTEGER," +
                "nom TEXT," +
                "codePostal MEDIUMINT(5)," +
                "ville VARCHAR(60));");

        //Liaison Inscription Sportif et Activite
        db.execSQL("create table Participer(" +
                "loginSportif VARCHAR(25)," +
                "idActivite INTEGER," +
                "jour VARCHAR(10) NOT NULL," +
                "estAccepte INTEGER DEFAULT 0," +
                "PRIMARY KEY(loginSportif, idActivite)," +
                "FOREIGN KEY(loginSportif) REFERENCES Sportif(login)," +
                "FOREIGN KEY(idActivite) REFERENCES Activite(id));");

        //Liaison Sport et Sportif
        db.execSQL("create table Choisir(" +
                "loginSportif VARCHAR(25)," +
                "libelleSport VARCHAR(25)," +
                "niveau VARCHAR(25) NOT NULL," +
                "PRIMARY KEY(loginSportif, libelleSport)," +
                "FOREIGN KEY(loginSportif) REFERENCES Sportif(login)," +
                "FOREIGN KEY(libelleSport) REFERENCES Sport(libelle));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
