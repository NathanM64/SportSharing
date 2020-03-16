package com.example.sportsharing.ClasseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sportsharing.Classe.Sportif;

import java.util.ArrayList;

public class SportifDAO extends DAO {
    public SportifDAO(Context context) {
        super(context);
    }

    public void addSportif(Sportif sportif) {
        SQLiteDatabase db = this.getWritableDatabase();

        //Création des valeurs
        ContentValues valeurs = new ContentValues();
        valeurs.put("login", sportif.getLogin());
        valeurs.put("motDePasse", sportif.getMotDePasse());
        valeurs.put("confirmMotDePasse", sportif.getConfirmMotDePasse());
        valeurs.put("nom", sportif.getNom());
        valeurs.put("prenom", sportif.getPrenom());
        valeurs.put("adresseMail", sportif.getAdresseMail());
        valeurs.put("dateNaissance", sportif.getDateNaissance());
        valeurs.put("ville", sportif.getVille());
        valeurs.put("codePostal", sportif.getCodePostal());
        valeurs.put("numeroTelephone", sportif.getNumeroTelephone());
        valeurs.put("description", sportif.getDescription());
        valeurs.put("resteConnecte", 1);

        //Ajout à la table
        db.insert("Sportif", null, valeurs);
    }

    public Sportif getSportif(String login) {
        Sportif sportif = null;
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sportif where login=?;", new String[]{login});
        sportif = curseurToSportif(curseur);

        return sportif;
    }

    private Sportif curseurToSportif(Cursor curseur) {
        Sportif sportif = null;
        String login, motDePasse, confirmMotDePasse, nom, prenom, adresseMail, dateNaissance, ville, numeroTelephone, description;
        int codePostal, resteConnecte;

        if(curseur.getCount() > 0) {
            curseur.moveToFirst();
            login = curseur.getString(0);
            motDePasse = curseur.getString(1);
            confirmMotDePasse = curseur.getString(2);
            nom = curseur.getString(3);
            prenom = curseur.getString(4);
            adresseMail = curseur.getString(5);
            dateNaissance = curseur.getString(6);
            ville = curseur.getString(7);
            codePostal = curseur.getInt(8);
            numeroTelephone = curseur.getString(9);
            description = curseur.getString(10);
            resteConnecte = curseur.getInt(11);

            sportif = new Sportif(login, motDePasse, confirmMotDePasse, nom, prenom, adresseMail, dateNaissance, ville, codePostal, numeroTelephone, description);
            if(resteConnecte == 1)
                sportif.setResteConnecte(true);
        }

        return sportif;
    }

    public ArrayList<Sportif> getAllSportifs() {
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sportif;", new String[] {});
        return curseurToSportifArrayList(curseur);
    }

    private ArrayList<Sportif> curseurToSportifArrayList(Cursor curseur) {
        ArrayList<Sportif> listSportif = new ArrayList<>();
        String login, motDePasse, confirmMotDePasse, nom, prenom, adresseMail, dateNaissance, ville, numeroTelephone, description;
        int codePostal, resteConnecte;

        if(curseur.getCount() > 0) {
            curseur.moveToFirst();
            login = curseur.getString(0);
            motDePasse = curseur.getString(1);
            confirmMotDePasse = curseur.getString(2);
            nom = curseur.getString(3);
            prenom = curseur.getString(4);
            adresseMail = curseur.getString(5);
            dateNaissance = curseur.getString(6);
            ville = curseur.getString(7);
            codePostal = curseur.getInt(8);
            numeroTelephone = curseur.getString(9);
            description = curseur.getString(10);
            resteConnecte = curseur.getInt(11);

            Sportif sportif = new Sportif(login, motDePasse, confirmMotDePasse, nom, prenom, adresseMail, dateNaissance, ville, codePostal, numeroTelephone, description);
            if(resteConnecte == 1)
                sportif.setResteConnecte(true);
            listSportif.add(sportif);
        }

        return listSportif;
    }
}
