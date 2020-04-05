package com.example.sportsharing.ClasseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

/**
 * AdresseDAO est la classe qui fait la liaison entre la base de données et la classe Activité
 * @see com.example.sportsharing.Classe.Adresse
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 30/03/2020
 */
public class AdresseDAO extends DAO {

    /**
     * <br>
     * <b>Constructeur de la classe AdresseDAO</b>
     * @param context       Activity dans laquelle cette classe est appelé
     */
    public AdresseDAO(Context context) {
        super(context);
    }

    /**
     * Retourne l'identifiant d'une adresse
     * @param numero        Numero de l'adresse
     * @param nom           Nom de rue de l'adresse
     * @param codePostal    Code postal de l'adresse
     * @param ville         Ville de l'adresse
     * @return
     */
    public int getIdByAdresse(int numero, String nom, int codePostal, String ville) {
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select id from Adresse where numero=? and nom=? and codePostal=? and ville=?", new String[]{numero+"", nom, codePostal+"", ville});

        curseur.moveToFirst();
        if(curseur.getCount() > 0) {
            return curseur.getInt(0);
        } else {
            return -1;
        }
    }

    /**
     * Ajout d'une nouvelle adresse à la table Adresse
     * @param numero        Numero de l'adresse
     * @param nom           Nom de rue de l'adresse
     * @param codePostal    Code postal de l'adresse
     * @param ville         Ville de l'adresse
     */
    public void addAdresse(int numero, String nom, int codePostal, String ville) {
        ContentValues adresse = new ContentValues();

        adresse.put("numero", numero);
        adresse.put("nom", nom);
        adresse.put("codePostal", codePostal);
        adresse.put("ville", ville);

        this.getWritableDatabase().insert("Adresse", null, adresse);
    }
}
