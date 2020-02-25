package com.example.sportsharing.Classe;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class DossierVariableClasse {

    //VARIABLES
    public static DossierVariableClasse instance = null;

    //VARIABLES BD
    public Sportif utilisateur;
    public Sportif profilVisite;
    public Organisateur createur;


    private DossierVariableClasse() {
        instance = this;
        this.utilisateur = null;
        this.profilVisite = null;
        this.createur = null;
    }

    public static DossierVariableClasse getInstance() {

        if(instance == null)
        {
            instance = new DossierVariableClasse();
        }

        return instance;
    }

    public void setUtilisateur(Sportif utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setProfilVisite(Sportif profilVisite) {
        this.profilVisite = profilVisite;
    }

    public void setCreateur() {
        this.createur = new Organisateur(utilisateur);
    }

    //Création de données de test pour les vues de l'application
    public void creationDonneeTest() {

        ArrayList<Activite> listeActivite = new ArrayList<>();
        //Création des activités
        Activite activite1 = new Activite(1,new Date(2020, 2, 25),new Time(18,30,0),new Time(20,0,0),"La premiere activite",10,createur,new Adresse(2,"La Rue",33430,"BAZAS"),new Sport(EnumUtil.NameSport.Musculation, EnumUtil.TypeSport.Autre));
        Activite activite2 = new Activite(2,new Date(2020, 2, 30),new Time(18,30,0),new Time(21,0,0),"La deuxieme activite",20,createur,new Adresse(3,"La Vraie",64600,"ANGLET"),new Sport(EnumUtil.NameSport.Rugby, EnumUtil.TypeSport.Ballon));
        Activite activite3 = new Activite(3,new Date(2020, 3, 5),new Time(18,30,0),new Time(22,0,0),"La troisieme activite",20,createur,new Adresse(4,"rue de la paix",33000,"BORDEAUX"),new Sport(EnumUtil.NameSport.Course, EnumUtil.TypeSport.Autre));
        Activite activite4 = new Activite(4,new Date(2020, 3, 12),new Time(18,30,0),new Time(23,0,0),"La quatrieme activite",20,createur,new Adresse(5,"rue de la soif",64400,"St GOIN"),new Sport(EnumUtil.NameSport.Pelote, EnumUtil.TypeSport.Raquette));
        Activite activite5 = new Activite(5,new Date(2020, 3, 22),new Time(20,30,0),new Time(21,0,0),"La cinquieme activite",20,createur,new Adresse(6,"pas la rue",64100,"BAYONNE"),new Sport(EnumUtil.NameSport.Tennis, EnumUtil.TypeSport.Raquette));
        Activite activite6 = new Activite(6,new Date(2020, 4, 5),new Time(21,30,0),new Time(23,0,0),"La sixieme activite",20,createur,new Adresse(7,"la bonne place",64000,"PAU"),new Sport(EnumUtil.NameSport.Danse, EnumUtil.TypeSport.Autre));
        Activite activite7 = new Activite(7,new Date(2020, 4, 23),new Time(22,30,0),new Time(0,30,0),"La septieme activite",20,createur,new Adresse(12,"place lamothe",64600,"ANGLET"),new Sport(EnumUtil.NameSport.Gymnastique, EnumUtil.TypeSport.Autre));
        //Ajout des activités à la liste
        listeActivite.add(activite1);
        listeActivite.add(activite2);
        listeActivite.add(activite3);
        listeActivite.add(activite4);
        listeActivite.add(activite5);
        listeActivite.add(activite6);
        listeActivite.add(activite7);

        //Création du profil
        Sportif michelTelo = new Sportif("michelLeTelo","password1","password1","Michel","Telo","michel.telo@gmail.com","12/12/12","Marseille",13000,"0695364608","Je suis un gitan");

    }

}
