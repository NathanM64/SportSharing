package com.example.sportsharing.ClasseDAO;

import android.content.Context;
import android.database.Cursor;

import com.example.sportsharing.Classe.Activite;
import com.example.sportsharing.Classe.Adresse;
import com.example.sportsharing.Classe.EnumUtil;
import com.example.sportsharing.Classe.Organisateur;
import com.example.sportsharing.Classe.Sport;
import com.example.sportsharing.Classe.Sportif;

import java.util.ArrayList;

public class ActiviteDAO extends DAO {

    public ActiviteDAO(Context context) {
        super(context);
    }

    public void addActivite(Activite activite) {

    }

    public ArrayList<Activite> getAllActiviteBySportifLogin(String login) {
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Activite where idCreateur=?", new String[]{login});
        return curseurToActiviteArrayList(curseur);
    }

    private ArrayList<Activite> curseurToActiviteArrayList(Cursor curseur) {
        ArrayList<Activite> listActivite = new ArrayList<>();
        int id, nbMaxPersonne;
        String jour, heureDebut, heureFin, description;
        boolean estTermine;
        Organisateur createur;
        Adresse lieu;
        Sport sport;

        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            curseur.moveToFirst();

            id = curseur.getInt(0);
            jour = curseur.getString(1);
            heureDebut = curseur.getString(2);
            heureFin = curseur.getString(3);
            description = curseur.getString(4);
            nbMaxPersonne = curseur.getInt(5);
            estTermine = curseur.getInt(6) != 0;
            createur = getOrganisateurByLogin(curseur.getString(7));
            lieu = getAdresseById(curseur.getInt(10));
            sport = getSportByLibelle(EnumUtil.NameSport.valueOf(curseur.getString(8)));

            Activite activite = new Activite(id, jour, heureDebut, heureFin, description, nbMaxPersonne, createur, lieu, sport);
            activite.setEstTermine(estTermine);

            listActivite.add(activite);
            curseur.moveToNext();
        }

        return listActivite;
    }

    private Adresse getAdresseById(int id) {
        Cursor curseur;
        Adresse adresse = null;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select numero, nom, codePostal, ville from Adresse where id=?", new String[]{id+""});

        curseur.moveToFirst();
        if(curseur.getCount() > 0) {
            int numero = curseur.getInt(0);
            String nom = curseur.getString(1);
            int codePostal = curseur.getInt(2);
            String ville = curseur.getString(3);

            adresse = new Adresse(numero, nom, codePostal, ville);
        }
        return adresse;
    }

    private Sport getSportByLibelle(EnumUtil.NameSport libelle) {
        Cursor curseur;
        Sport sport = null;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sport where libelle=?", new String[]{libelle.toString()});

        curseur.moveToFirst();
        if(curseur.getCount() > 0) {
            EnumUtil.NameSport l = EnumUtil.NameSport.valueOf(curseur.getString(0));
            EnumUtil.TypeSport t = EnumUtil.TypeSport.valueOf(curseur.getString(1));

            sport = new Sport(l, t);
        }
        return sport;
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
