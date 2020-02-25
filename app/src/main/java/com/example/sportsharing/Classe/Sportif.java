package com.example.sportsharing.Classe;


import java.util.HashMap;
import java.util.Map;

/**
 * Sportif est la classe représentant un utilisateur de l'application
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 24/02/2020
 */

public class Sportif {

    //Profil
    private String login;
    private String motDePasse;
    private String confirmMotDePasse;

    //Détails profil
    /**
     * Nom du sportif
     */
    protected String nom;
    /**
     * Prénom du sportif
     */
    protected String prenom;
    /**
     * Adresse mail du sportif
     */
    protected String adresseMail;
    /**
     * Date de naissance du sportif
     */
    protected String dateNaissance;
    /**
     * Ville du lieu d'utilisation de l'application initialisé à ""
     */
    protected String ville = "";
    /**
     * Code postal de la ville initialisé à 00000
     */
    protected int codePostal = 00000;
    /**
     *  Numéro de téléphone du sportif initialisé à ""
     */
    protected String numeroTelephone = "";
    /**
     *  Présentation du sportif initialisé à ""
     */
    protected String description = "";

    //Autre variables
    /**
     * Map listant les sports décrits par le sportif : <br>
     * Clé : Element de la classe Sport <br>
     * Valeur : Element appartenant à l'énumération NiveauSport présent dans EnumUtil
     */
    public Map<Sport, EnumUtil.NiveauSport> mesSports;

    /**
     * <br>
     * <b>Constructeur de la classe sportif</b>
     * @param login             Identifiant pour la connection du sportif
     * @param motDePasse        Mot de passe pour la connection du sportif
     * @param confirmMotDePasse Confirmation du mot de passe pour vérifier la bonne saisie du mot de passe
     * @param nom               Nom du sportif
     * @param prenom            Prénom du sportif
     * @param adresseMail       Adresse mail du sportif
     * @param dateNaissance     Date de naissance du sportif
     * @param ville             Ville du lieu d'utilisation de l'application (Facultatif)
     * @param codePostal        Code postal de la ville (Facultatif)
     * @param numeroTelephone   Numéro de téléphone du sportif (Facultatif)
     * @param description       Présentation du sportif (Facultatif)
     */
    public Sportif(String login, String motDePasse, String confirmMotDePasse,
                    String nom, String prenom,
                    String adresseMail, String dateNaissance, String ville, int codePostal, String numeroTelephone, String description)
    {
        this.login = login;
        this.motDePasse = motDePasse;
        this.confirmMotDePasse = confirmMotDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numeroTelephone = numeroTelephone;
        this.description = description;

        mesSports = new HashMap<>();
    }

    //METHODES GETTER
    public String getLogin() { return login; }

    public String getMotDePasse() { return motDePasse; }

    public String getConfirmMotDePasse() { return confirmMotDePasse; }

    public String getNom() { return nom; }

    public String getPrenom() { return prenom; }

    public String getAdresseMail() { return adresseMail; }

    public String getDateNaissance() { return dateNaissance; }

    public String getVille() { return ville; }

    public int getCodePostal() { return codePostal; }

    public String getNumeroTelephone() { return numeroTelephone; }

    public String getDescription() { return description; }

    //METHODES SETTER
    public void setLogin(String login) {
        this.login = login;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setConfirmMotDePasse(String confirmMotDePasse) { this.confirmMotDePasse = confirmMotDePasse; }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //AUTRES METHODES
    /**
     * Ajoute un sport à la liste du sportif si celui n'est pas déjà présent dans la liste
     * @param s     <a href="{@docRoot}/com/example/sportsharing/Classe/Sport.html">Sport</a> servant de clé pour la recherche dans mesSports
     * @param n     <a href="{@docRoot}/com/example/sportsharing/Classe/EnumUtil.NiveauSport.html">NiveauSport</a> étant la valeur lié à la clé s
     */
    public void addSport(Sport s, EnumUtil.NiveauSport n)
    {
        if(!mesSports.containsKey(s))
        {
            mesSports.put(s, n);
        }
    }

    /**
     * Supprime un sport de la liste du sportif seulement si celui-ci est présent
     * @param s     <a href="{@docRoot}/com/example/sportsharing/Classe/Sport.html">Sport</a> servant de clé pour la recherche dans mesSports
     */
    public void suppSport(Sport s)
    {
        if(mesSports.containsKey(s))
        {
            mesSports.remove(s);
        }
    }

    /**
     * @return Retourne la version String de la liste des sports du sportif
     */
    public String toStringVector()
    {
        String message = "Mes Sports {";

        message += mesSports.toString();

        message += " }";
        return message;
    }

    /**
     * @return Retourne le détails d'un sportif
     */
    @Override
    public String toString() {
        return "Sportif{" +
                "login='" + login + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", confirmMotDePasse='" + confirmMotDePasse + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresseMail='" + adresseMail + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal=" + codePostal +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", description='" + description + '\'' +
                ", mesSports=" + this.toStringVector() +
                '}';
    }
}
