package com.example.sportsharing.ClasseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class AdresseDAO extends DAO {

    public AdresseDAO(Context context) {
        super(context);
    }

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

    public void addAdresse(int numero, String nom, int codePostal, String ville) {
        ContentValues adresse = new ContentValues();

        adresse.put("numero", numero);
        adresse.put("nom", nom);
        adresse.put("codePostal", codePostal);
        adresse.put("ville", ville);

        this.getWritableDatabase().insert("Adresse", null, adresse);
    }
}
