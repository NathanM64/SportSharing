package com.example.sportsharing;

import com.example.sportsharing.Classe.Activite;
import com.example.sportsharing.Classe.Adresse;
import com.example.sportsharing.Classe.DossierVariableClasse;
import com.example.sportsharing.Classe.EnumUtil;
import com.example.sportsharing.Classe.Organisateur;
import com.example.sportsharing.Classe.Sport;
import com.example.sportsharing.Classe.Sportif;

import org.junit.Test;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestClassBD {
    @Test
    public void Creation_et_liaison_variable() {

        //Récupération de l'accés aux variables
        DossierVariableClasse bd = DossierVariableClasse.getInstance();

        //Création des elements
            //CREATION D'UN SPORT
        Sport test1 = new Sport(EnumUtil.NameSport.Tennis, EnumUtil.TypeSport.Raquette);
        Sport test2 = new Sport(EnumUtil.NameSport.HandBall, EnumUtil.TypeSport.Ballon);

        Sportif utilisateur = new Sportif("coucou", "a", "a",
                "bocciarelli", "mathieu", "bocciarellimathieu@orange.fr", "01/09/2000", "Nevian",
                11200, "", "");

        Organisateur createur = new Organisateur(utilisateur);

        Adresse aActivite = new Adresse(3, "chemin du deves", 11200, "Nevian");

        Activite creation = new Activite(1, new Date(2020, 2, 2), new Time(18, 33, 00), new Time(18, 50, 0) , "", 10, createur, aActivite, test1);


        //Affichage bonne création
        System.out.println(utilisateur.toString());

        System.out.println(aActivite.toString());

        System.out.println(creation.toString());

        utilisateur.addSport(test1, EnumUtil.NiveauSport.Occasionnel);


        System.out.println(utilisateur.toString());

    }
}