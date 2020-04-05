package com.example.sportsharing.ClasseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper {

    public DAO(Context context) {
        super(context, ParamDAO.base, null, ParamDAO.version);
    }

    /**
     * Crée la base de données
     * La méthode n'est pas appelé si la base de données est déjà créé
     * @param db
     */
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
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
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

        //Création des sports
        ContentValues sport = new ContentValues();
        sport.put("libelle", "Course");
        sport.put("type", "Autre");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Foot");
        sport.put("type", "Ballon");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Danse");
        sport.put("type", "Autre");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Gymnastique");
        sport.put("type", "Autre");
        db.insert("Sport", null, sport);

        sport.put("libelle", "HandBall");
        sport.put("type", "Ballon");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Musculation");
        sport.put("type", "Autre");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Musique");
        sport.put("type", "Autre");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Pelote");
        sport.put("type", "Raquette");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Rugby");
        sport.put("type", "Ballon");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Surf");
        sport.put("type", "Autre");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Tennis");
        sport.put("type", "Raquette");
        db.insert("Sport", null, sport);

        sport.put("libelle", "Velo");
        sport.put("type", "Autre");
        db.insert("Sport", null, sport);

        //Création du sportif de test
        ContentValues valeurs = new ContentValues();
        valeurs.put("login", "test");
        valeurs.put("motDePasse", "test");
        valeurs.put("confirmMotDePasse", "test");
        valeurs.put("nom", "test");
        valeurs.put("prenom", "test");
        valeurs.put("adresseMail", "test@test.fr");
        valeurs.put("dateNaissance", "01/01/2020");
        valeurs.put("ville", "Anglet");
        valeurs.put("codePostal", 64600);
        valeurs.put("numeroTelephone", "0606060606");
        valeurs.put("description", "description");
        valeurs.put("resteConnecte", 1);

        //Ajout à la table
        db.insert("Sportif", null, valeurs);


        valeurs.put("login", "test2");
        valeurs.put("motDePasse", "test2");
        valeurs.put("confirmMotDePasse", "test2");
        valeurs.put("nom", "test2");
        valeurs.put("prenom", "test2");
        valeurs.put("adresseMail", "test2@test2.fr");
        valeurs.put("dateNaissance", "01/01/2020");
        valeurs.put("ville", "Anglet");
        valeurs.put("codePostal", 64600);
        valeurs.put("numeroTelephone", "0606060606");
        valeurs.put("description", "description");
        valeurs.put("resteConnecte", 0);

        //Ajout à la table
        db.insert("Sportif", null, valeurs);

        valeurs = new ContentValues();
        valeurs.put("id", "2");
        valeurs.put("numero", "8");
        valeurs.put("nom", "chemin de la Cabette");
        valeurs.put("codePostal", "64400");
        valeurs.put("ville", "Saint Goin");

        //Ajout à la table
        db.insert("Adresse", null, valeurs);


        valeurs = new ContentValues();
        valeurs.put("jour", "01/04/2020");
        valeurs.put("heureDebut", "14:00");
        valeurs.put("heureFin", "15:30");
        valeurs.put("description", "test");
        valeurs.put("nbMaxPersonnes", "1");
        valeurs.put("estTermine", 0);
        valeurs.put("idCreateur", "test2");
        valeurs.put("libelleSport", "Surf");
        valeurs.put("niveauSport", "Intermediaire");
        valeurs.put("idAdresse", 2);

        //Ajout à la table
        db.insert("Activite", null, valeurs);


        valeurs.put("jour", "01/04/2020");
        valeurs.put("heureDebut", "15:30");
        valeurs.put("heureFin", "17:00");
        valeurs.put("description", "test");
        valeurs.put("nbMaxPersonnes", "1");
        valeurs.put("estTermine", 0);
        valeurs.put("idCreateur", "test");
        valeurs.put("libelleSport", "Foot");
        valeurs.put("niveauSport", "Intermediaire");
        valeurs.put("idAdresse", 2);

        //Ajout à la table
        db.insert("Activite", null, valeurs);



    }

    /**
     * Méthode qui permet de mettre à jour la base
     * @param db
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
