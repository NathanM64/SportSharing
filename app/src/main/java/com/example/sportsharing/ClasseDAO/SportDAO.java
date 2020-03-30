package com.example.sportsharing.ClasseDAO;

import android.content.Context;
import android.database.Cursor;

import com.example.sportsharing.Classe.EnumUtil;
import com.example.sportsharing.Classe.Sport;

import java.util.ArrayList;

public class SportDAO extends DAO {

    public SportDAO(Context context) {
        super(context);
    }

    public ArrayList<Sport> getAllSports() {
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sport", new String[]{});
        return curseurToSports(curseur);
    }

    public Sport getSport(EnumUtil.NameSport libelle) {
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sport where libelle=?", new String[]{libelle.toString()});
        return curseurToSport(curseur);
    }

    private ArrayList<Sport> curseurToSports(Cursor curseur) {
        ArrayList<Sport> listSports = new ArrayList<>();
        EnumUtil.NameSport libelle;
        EnumUtil.TypeSport type;

        curseur.moveToFirst();
        while(!curseur.isAfterLast()) {
            libelle = EnumUtil.NameSport.valueOf(curseur.getString(0));
            type = EnumUtil.TypeSport.valueOf(curseur.getString(1));

            listSports.add(new Sport(libelle, type));
            curseur.moveToNext();
        }

        return listSports;
    }

    private Sport curseurToSport(Cursor curseur) {
        Sport sport = null;
        EnumUtil.NameSport libelle;
        EnumUtil.TypeSport type;

        curseur.moveToFirst();
        if(curseur.getCount() > 0) {
            libelle = EnumUtil.NameSport.valueOf(curseur.getString(0));
            type = EnumUtil.TypeSport.valueOf(curseur.getString(1));

            sport = new Sport(libelle, type);
        }

        return sport;
    }
}
