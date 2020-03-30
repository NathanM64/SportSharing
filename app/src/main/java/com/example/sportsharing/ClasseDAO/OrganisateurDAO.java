package com.example.sportsharing.ClasseDAO;

import android.content.Context;
import android.database.Cursor;

import com.example.sportsharing.Classe.Organisateur;
import com.example.sportsharing.Classe.Sportif;

public class OrganisateurDAO extends DAO {

    public OrganisateurDAO(Context context) {
        super(context);
    }

    public Organisateur getOrganisateurByLogin(String login) {
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sportif where login=?", new String[]{login});
        return curseurToOrganisateur(curseur);
    }

    private Organisateur curseurToOrganisateur(Cursor curseur) {
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

        return new Organisateur(sportif);
    }
}
