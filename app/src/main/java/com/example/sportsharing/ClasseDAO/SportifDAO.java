package com.example.sportsharing.ClasseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sportsharing.Classe.Sportif;

import java.util.ArrayList;

/**
 * SportifDAO est la classe qui fait la liaison entre la base de données et la classe Sportif
 * @see Sportif
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 30/03/2020
 */
public class SportifDAO extends DAO {

    /**
     * <br>
     * <b>Constructeur de la classe SportifDAO</b>
     * @param context       Activity dans laquelle cette classe est appelé
     */
    public SportifDAO(Context context) {
        super(context);
    }

    /**
     * Ajoute un sportif à la base de données
     * @see Sportif
     * @param sportif
     */
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
        valeurs.put("resteConnecte", sportif.isResteConnecte());

        //Ajout à la table
        db.insert("Sportif", null, valeurs);
    }

    /**
     * Retire l'option de rester connecté à un sportif
     * @param login     Login du sportif concerné
     */
    public void enleveResteConnectASportifAvecResteConnecte(String login) {
        ContentValues values = new ContentValues();
        values.put("resteConnecte", 0);

        this.getWritableDatabase().update("Sportif", values, "login=?", new String[]{login});
    }

    /**
     * Définit l'option de rester connecté à un sportif
     * @param login     Login du sportif concerné
     */
    public void setResteConnectASportif(String login) {
        ContentValues values = new ContentValues();
        values.put("resteConnecte", 1);

        this.getWritableDatabase().update("Sportif", values, "login=?", new String[]{login});
    }

    /**
     * Retourne un sportif
     * @param login     Login du sportif concerné
     * @return
     */
    public Sportif getSportif(String login) {
        Sportif sportif = null;
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sportif where login=?;", new String[]{login});
        sportif = curseurToSportif(curseur);

        return sportif;
    }

    /**
     * Retourne le sportif qui a l'option de rester connecté
     * @return
     */
    public Sportif getSportifResteConnecte() {
        Sportif sportif = null;
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sportif where resteConnecte=?;", new String[]{1+""});
        sportif = curseurToSportif(curseur);

        return sportif;
    }

    /**
     * Retourne un sportif contenu dans un curseur
     * @see Cursor
     * @param curseur
     * @return
     */
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

    /**
     * Retourne la liste de tous les sportifs de la base
     * @return
     */
    public ArrayList<Sportif> getAllSportifs() {
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sportif;", new String[] {});
        return curseurToSportifArrayList(curseur);
    }

    /**
     * Retourne la liste de tous les sportif contenu dans un curseur
     * @see Cursor
     * @param curseur
     * @return
     */
    private ArrayList<Sportif> curseurToSportifArrayList(Cursor curseur) {
        ArrayList<Sportif> listSportif = new ArrayList<>();
        String login, motDePasse, confirmMotDePasse, nom, prenom, adresseMail, dateNaissance, ville, numeroTelephone, description;
        int codePostal, resteConnecte;

        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
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

            curseur.moveToNext();
        }

        return listSportif;
    }
}
