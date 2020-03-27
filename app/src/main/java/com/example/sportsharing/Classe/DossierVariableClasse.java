package com.example.sportsharing.Classe;

import java.util.ArrayList;

public class DossierVariableClasse {

    //VARIABLES
    public static DossierVariableClasse instance = null;

    //PourTest
    public Sportif michelTelo = new Sportif("michelLeTelo","password1","password1","Michel","Telo","michel.telo@gmail.com","01/09/2000","Marseille",13000,"0695364608","Je suis un gitan");
    public Sportif kendJ = new Sportif("kendJ","password2","password2","Kenji","Girac","XxGendji@gmail.com","20/20/1950","Bergerac",24100,"0725564605","Je suis un gitan qui fait de la musique");
    public ArrayList<Activite> listeActivite;

    //VARIABLES BD
    public Sportif utilisateur;
    public Sportif profilVisite;
    public Organisateur createur;


    private DossierVariableClasse() {
        instance = this;
        this.utilisateur = null;
        this.profilVisite = null;
        this.createur = null;

        //Test
        michelTelo.addSport(new Sport(EnumUtil.NameSport.Tennis, EnumUtil.TypeSport.Raquette), EnumUtil.NiveauSport.Occasionnel);
        michelTelo.addSport(new Sport(EnumUtil.NameSport.Surf, EnumUtil.TypeSport.Autre), EnumUtil.NiveauSport.Non_Renseigne);
        this.createur = new Organisateur(kendJ);
        creationDonneeTest();
    }

    public static DossierVariableClasse getInstance() {

        if(instance == null)
        {
            instance = new DossierVariableClasse();
        }

        return instance;
    }

    public void setUtilisateur(Sportif utilisateur) {this.utilisateur = utilisateur;}

    public void setProfilVisite(Sportif profilVisite) {this.profilVisite = profilVisite;}

    public void setCreateur() {this.createur = new Organisateur(utilisateur);}

    //Création de données de test pour les vues de l'application
    public void creationDonneeTest() {
        //Création des activités
        listeActivite = new ArrayList<>();
        Activite activite1 = new Activite(1,"25/02/2020","18/30","20/00","La premiere activite",10, createur, new Adresse(2,"La Rue",33430,"BAZAS"), new Sport(EnumUtil.NameSport.Musculation, EnumUtil.TypeSport.Autre));
        Activite activite2 = new Activite(2,"30/02/2020","18/30","21/00","La deuxieme activite",20, createur, new Adresse(3,"La Vraie",64600,"ANGLET"), new Sport(EnumUtil.NameSport.Rugby, EnumUtil.TypeSport.Ballon));
        Activite activite3 = new Activite(3,"05/03/2020","18/30","22/00","La troisieme activite",20, createur, new Adresse(4,"rue de la paix",33000,"BORDEAUX"), new Sport(EnumUtil.NameSport.Course, EnumUtil.TypeSport.Autre));
        Activite activite4 = new Activite(4,"12/03/2020","18/30","23/00","La quatrieme activite",20, createur, new Adresse(5,"rue de la soif",64400,"St GOIN"), new Sport(EnumUtil.NameSport.Pelote, EnumUtil.TypeSport.Raquette));
        Activite activite5 = new Activite(5,"22/03/2020","18/30","21/00","La cinquieme activite",20, createur, new Adresse(6,"pas la rue",64100,"BAYONNE"), new Sport(EnumUtil.NameSport.Tennis, EnumUtil.TypeSport.Raquette));
        Activite activite6 = new Activite(6,"05/04/2020","18/30","23/00","La sixieme activite",20, createur, new Adresse(7,"la bonne place",64000,"PAU"), new Sport(EnumUtil.NameSport.Danse, EnumUtil.TypeSport.Autre));
        Activite activite7 = new Activite(7,"23/04/2020","18/30","00/30","La septieme activite",20, createur, new Adresse(12,"place lamothe",64600,"ANGLET"), new Sport(EnumUtil.NameSport.Gymnastique, EnumUtil.TypeSport.Autre));
        //Ajout des activités à la liste
        listeActivite.add(activite1);
        listeActivite.add(activite2);
        listeActivite.add(activite3);
        listeActivite.add(activite4);
        listeActivite.add(activite5);
        listeActivite.add(activite6);
        listeActivite.add(activite7);
    }

}
