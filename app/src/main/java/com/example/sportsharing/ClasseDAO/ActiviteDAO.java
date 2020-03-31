package com.example.sportsharing.ClasseDAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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
        ContentValues contentValues = new ContentValues();
        contentValues.put("jour", activite.getJour());
        contentValues.put("heureDebut", activite.getHeureDebut());
        contentValues.put("heureFin", activite.getHeureFin());
        contentValues.put("description", activite.getDescription());
        contentValues.put("nbMaxPersonnes", activite.nbMaxPersonnes);
        contentValues.put("estTermine", activite.estTermine?1:0);
        contentValues.put("idCreateur", activite.createur.getLogin());
        contentValues.put("libelleSport", activite.sport.getLibelle().toString());
        contentValues.put("niveauSport", activite.niveauSport.toString());

        int id = getIdByAdresse(activite.lieu.getNumeroRue(), activite.lieu.getNomRue(), activite.lieu.getCodePostal(), activite.lieu.getVille());
        if(id != -1) {
            contentValues.put("idAdresse", id);
        } else {
            addAdresse(activite.lieu.getNumeroRue(), activite.lieu.getNomRue(), activite.lieu.getCodePostal(), activite.lieu.getVille());
            id = getIdByAdresse(activite.lieu.getNumeroRue(), activite.lieu.getNomRue(), activite.lieu.getCodePostal(), activite.lieu.getVille());
            contentValues.put("idAdresse", id);
        }

        Log.d("Test", this.getWritableDatabase().insert("Activite", null, contentValues)+"");
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
        EnumUtil.NiveauSport niveau;
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
            niveau = EnumUtil.NiveauSport.valueOf(curseur.getString(9));
            lieu = getAdresseById(curseur.getInt(10));
            sport = getSportByLibelle(EnumUtil.NameSport.valueOf(curseur.getString(8)));

            Activite activite = new Activite(id, jour, heureDebut, heureFin, description, nbMaxPersonne, niveau, createur, lieu, sport);
            activite.setEstTermine(estTermine);

            listActivite.add(activite);
            curseur.moveToNext();
        }

        return listActivite;
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
        Sportif sportif = new Sportif();
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

    private Adresse getAdresseById(int id) {
        Cursor curseur;
        Adresse adresse = null;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Adresse where id=?", new String[]{id+""});

        curseur.moveToFirst();
        if(curseur.getCount() > 0) {
            int numero = curseur.getInt(1);
            String nom = curseur.getString(2);
            int codePostal = curseur.getInt(3);
            String ville = curseur.getString(4);

            adresse = new Adresse(numero, nom, codePostal, ville);
        }
        return adresse;
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
