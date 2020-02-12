package com.example.sportsharing.Classe;

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
}
