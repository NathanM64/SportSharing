package com.example.sportsharing.Classe;

/**
 * Classe contenant les éléments chargés tout au long de l'utilisation de l'application
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 24/02/2020
 */
public class Ressource {

    /**
     * Instance unique de la classe Ressource
     */
    //VARIABLES
    public static Ressource instance = null;

    //VARIABLES BD
    /**
     * Utilisateur connecté à l'application
     * @see Sportif
     */
    public Sportif utilisateur;
    /**
     * Dernier utilisateur dont le profil a été visité
     * @see Sportif
     */
    public Sportif profilVisite;
    /**
     * Version Organisateur de l'utilisateur
     * @see Organisateur
     */
    public Organisateur createur;
    /**
     * Dernière activité visité
     * @see Activite
     */
    public Activite activiteCurrent;

    /**
     * <br>
     * <b>Constructeur de la classe Ressource</b>
     */
    private Ressource() {
        instance = this;
        this.utilisateur = null;
        this.profilVisite = null;
        this.createur = null;
    }

    /**
     * Récupération de l'instance unique de la class Ressource.
     * Appel le constructeur de Ressource si instance est null
     * @return
     */
    public static Ressource getInstance() {

        if(instance == null)
            instance = new Ressource();

        return instance;
    }

    //METHODES SETTERS

    /**
     * Définit l'utilisateur
     * @param utilisateur
     */
    public void setUtilisateur(Sportif utilisateur) {this.utilisateur = utilisateur;}

    /**
     * Définit le dernier profil visité
     * @param profilVisite
     */
    public void setProfilVisite(Sportif profilVisite) {this.profilVisite = profilVisite;}

    /**
     * Définit le créateur selon l'utilisateur
     */
    public void setCreateur() {this.createur = new Organisateur(utilisateur);}

    /**
     * Définit la dernière activité visité
     * @param activiteCurrent
     */
    public void setActiviteCurrent(Activite activiteCurrent) {
        this.activiteCurrent = activiteCurrent;
    }

    //METHODES GETTERS

    /**
     * Retourne l'utilisateur
     * @return
     */
    public Sportif getUtilisateur() {
        return utilisateur;
    }

    /**
     * Retourne le dernier profil visité
     * @return
     */
    public Sportif getProfilVisite() {
        return profilVisite;
    }

    /**
     * Retourne le créateur
     * @return
     */
    public Organisateur getCreateur() {
        return createur;
    }

    /**
     * Retourne la dernière activité visité
     * @return
     */
    public Activite getActiviteCurrent() {
        return activiteCurrent;
    }
}
