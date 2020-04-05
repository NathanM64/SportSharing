package com.example.sportsharing.ClasseDAO;

import android.content.Context;
import android.database.Cursor;

import com.example.sportsharing.Classe.EnumUtil;
import com.example.sportsharing.Classe.Sport;

import java.util.ArrayList;

/**
 * SportDAO est la classe qui fait la liaison entre la base de données et la classe Sport
 * @see Sport
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 30/03/2020
 */
public class SportDAO extends DAO {

    /**
     * <br>
     * <b>Constructeur de la classe SportDAO</b>
     * @param context       Activity dans laquelle cette classe est appelé
     */
    public SportDAO(Context context) {
        super(context);
    }

    /**
     * Retourne la liste des sports de la base de données
     * @return
     */
    public ArrayList<Sport> getAllSports() {
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sport", new String[]{});
        return curseurToSports(curseur);
    }

    /**
     * Retourne un sport
     * @param libelle       Libellé du sport voulu
     * @see com.example.sportsharing.Classe.EnumUtil.NameSport
     * @return
     */
    public Sport getSport(EnumUtil.NameSport libelle) {
        Cursor curseur;

        //Requete
        curseur = this.getReadableDatabase().rawQuery("select * from Sport where libelle=?", new String[]{libelle.toString()});
        return curseurToSport(curseur);
    }

    /**
     * Retourne la liste des sports contenue dans un curseur
     * @see Cursor
     * @param curseur
     * @return
     */
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

    /**
     * Retourne le sport contenu dans un curseur
     * @see Cursor
     * @param curseur
     * @return
     */
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
