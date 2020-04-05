package com.example.sportsharing.Classe;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
     * Ville du lieu d'utilisation de l'application<br>
     * Par défault à ""
     */
    protected String ville = "";
    /**
     * Code postal de la ville<br>
     * Par défault à 00000
     */
    protected int codePostal = 00000;
    /**
     *  Numéro de téléphone du sportif<br>
     *  Par défault à ""
     */
    protected String numeroTelephone = "";
    /**
     *  Présentation du sportif<br>
     *  Par défault à ""
     */
    protected String description = "";
    /**
     * Permet de savoir si ce profil doit être charger sans saisie du login et motDePasse (true)<br>
     * Sinon (false)
     */
    protected boolean resteConnecte;

    //Autre variables
    /**
     * Map listant les sports décrits par le sportif : <br>
     * Clé : Element de la classe Sport <br>
     * Valeur : Element appartenant à l'énumération NiveauSport présent dans EnumUtil
     */
    public Map<Sport, EnumUtil.NiveauSport> mesSports;

    /**
     * <br>
     * <b>Constructeur par défault de la classe sportif</b>
     */
    public Sportif()
    {
        this.login = "";
        this.motDePasse = "";
        this.confirmMotDePasse = "";
        this.nom = "";
        this.prenom = "";
        this.adresseMail = "";
        this.dateNaissance = "";
        this.ville = "";
        this.codePostal = 0;
        this.numeroTelephone = "";
        this.description = "";
        this.resteConnecte = true;

        mesSports = new HashMap<>();
    }

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
        this.resteConnecte = false;

        mesSports = new HashMap<>();
    }

    /**
     * <br>
     * <b>Constructeur de la classe sportif pour charger le profil d'un autre sportif sélectionné</b>
     * @param nom               Nom du sportif
     * @param prenom            Prénom du sportif
     * @param adresseMail       Adresse mail du sportif
     * @param dateNaissance     Date de naissance du sportif
     * @param ville             Ville du lieu d'utilisation de l'application (Facultatif)
     * @param codePostal        Code postal de la ville (Facultatif)
     * @param numeroTelephone   Numéro de téléphone du sportif (Facultatif)
     * @param description       Présentation du sportif (Facultatif)
     */
    public Sportif(String nom, String prenom,
                   String adresseMail, String dateNaissance, String ville, int codePostal, String numeroTelephone, String description)
    {
        this.login = "";
        this.motDePasse = "";
        this.confirmMotDePasse = "";
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
        this.dateNaissance = dateNaissance;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numeroTelephone = numeroTelephone;
        this.description = description;
        this.resteConnecte = false;

        mesSports = new HashMap<>();
    }

    //METHODES GETTER

    /**
     * Retourne le login du sportif
     * @return
     */
    public String getLogin() { return login; }

    /**
     * Retourne le mot de passe du sportif
     * @return
     */
    public String getMotDePasse() { return motDePasse; }

    /**
     * Retourne le mot de passe du sportif
     * @return
     */
    public String getConfirmMotDePasse() { return confirmMotDePasse; }

    /**
     * Retourne le nom du sportif
     * @return
     */
    public String getNom() { return nom; }

    /**
     * Retourne le prenom du sportif
     * @return
     */
    public String getPrenom() { return prenom; }

    /**
     * Retourne l'adresse mail du sportif
     * @return
     */
    public String getAdresseMail() { return adresseMail; }

    /**
     * Retourne la date de naissance du sportif
     * @return
     */
    public String getDateNaissance() { return dateNaissance; }

    /**
     * Retourne la ville du sportif
     * @return
     */
    public String getVille() { return ville; }

    /**
     * Retourne le code postal du sportif
     * @return
     */
    public int getCodePostal() { return codePostal; }

    /**
     * Retourne le numero de téléphone du sportif
     * @return
     */
    public String getNumeroTelephone() { return numeroTelephone; }

    /**
     * Retourne la description du sportif
     * @return
     */
    public String getDescription() { return description; }

    /**
     * Retourne si le sportif doit se charger seul lors de l'ouverture de l'application
     * @return Conversion du boolean en int (1 = TRUE ou 0 = FALSE)
     */
    public int isResteConnecte() { return (resteConnecte?1:0); }

    //METHODES SETTER

    /**
     * Définit le login du sportif
     * @param login
     */
    public void setLogin(String login) { this.login = login; }

    /**
     * Définit le mot de passe du sportif
     * @param motDePasse
     */
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    /**
     * Définit le mot de passe du sportif
     * @param confirmMotDePasse
     */
    public void setConfirmMotDePasse(String confirmMotDePasse) { this.confirmMotDePasse = confirmMotDePasse; }

    /**
     * Définit le nom du sportif
     * @param nom
     */
    public void setNom(String nom) { this.nom = nom; }

    /**
     * Définit le prénom du sportif
     * @param prenom
     */
    public void setPrenom(String prenom) { this.prenom = prenom; }

    /**
     * Définit l'adresse mail du sportif
     * @param adresseMail
     */
    public void setAdresseMail(String adresseMail) { this.adresseMail = adresseMail; }

    /**
     * Définit la date de naissance du sportif
     * @param dateNaissance
     */
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }

    /**
     * Définit la ville du sportif
     * @param ville
     */
    public void setVille(String ville) { this.ville = ville; }

    /**
     * Définit le code postal du sportif
     * @param codePostal
     */
    public void setCodePostal(int codePostal) { this.codePostal = codePostal; }

    /**
     * Définit le numéro de téléphone
     * @param numeroTelephone
     */
    public void setNumeroTelephone(String numeroTelephone) { this.numeroTelephone = numeroTelephone; }

    /**
     * Définit la description du sportif
     * @param description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Retourne si le sportif doit rester connecté
     * @param resteConnecte     (TRUE) reste connecté sinon (FALSE)
     */
    public void setResteConnecte(boolean resteConnecte) { this.resteConnecte = resteConnecte; }

    //AUTRES METHODES
    /**
     * Ajoute un sport à la liste du sportif si celui n'est pas déjà présent dans la liste
     * @param s     <a href="{@docRoot}/com/example/sportsharing/Classe/Sport.html">Sport</a> servant de clé pour la recherche dans mesSports
     * @param n     <a href="{@docRoot}/com/example/sportsharing/Classe/EnumUtil.NiveauSport.html">NiveauSport</a> étant la valeur lié à la clé s
     */
    public void addSport(Sport s, EnumUtil.NiveauSport n)
    {
        //Element de la map
        Set keys = mesSports.keySet();
        Iterator it = keys.iterator();
        boolean isAdd = true;
        //Tant que il y a un element suivant et que l'element n'est pas deja present (presence par isAdd à false)
        while (it.hasNext() && isAdd) {
            Sport key = (Sport) it.next();
            if(key.libelle.equals(s.libelle))
                isAdd = false;
        }

        //Suppression seulement si l'element n'est pas present (isAdd a true)
        if(isAdd)
            mesSports.put(s, n);
    }

    /**
     * Supprime un sport de la liste du sportif seulement si celui-ci est présent
     * @param s     <a href="{@docRoot}/com/example/sportsharing/Classe/Sport.html">Sport</a> servant de clé pour la recherche dans mesSports
     */
    public void suppSport(Sport s)
    {
        //Element de la map
        Set keys = mesSports.keySet();
        Iterator it = keys.iterator();
        Sport aSupprimer = null;

        //Tant que il y a un element suivant et que l'element a supprimer n'est pas trouvé
        while (it.hasNext() && aSupprimer == null) {
            Sport key = (Sport) it.next();
            if(key.libelle.equals(s.libelle))
                aSupprimer = key;
        }
        //Suppression seulement si l'element est trouve
        if (aSupprimer != null)
            mesSports.remove(aSupprimer);
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
