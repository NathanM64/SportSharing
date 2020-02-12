package com.example.sportsharing.Classe;

import android.util.ArrayMap;
import android.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class Sportif {

    //Identifiant
    private String login;
    private String motDePasse;
    private String confirmMotDePasse;

    //Détails profil
    protected String nom;
    protected String prenom;
    protected String adresseMail;
    protected String dateNaissance;
    protected String ville = "";
    protected int codePostal = 00000;
    protected String numeroTelephone = "";
    protected String description = "";

    //Autres variables
    //public Vector<Pair<Sport, EnumUtil.NiveauSport>> mesSports;
    public Map<Sport, EnumUtil.NiveauSport> mesSports;

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
    public String getLogin() {
        return login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getConfirmMotDePasse() {
        return confirmMotDePasse;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getVille() {
        return ville;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getDescription() {
        return description;
    }

    //METHODES SETTER

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setConfirmMotDePasse(String confirmMotDePasse) {
        this.confirmMotDePasse = confirmMotDePasse;
    }

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
    public void addSport(Sport s, EnumUtil.NiveauSport n)
    {
        if(!mesSports.containsKey(s))
        {
            mesSports.put(s, n);
        }
    }

    public void suppSport(Sport s)
    {
        if(mesSports.containsKey(s))
        {
            mesSports.remove(s);
        }
    }

    public String toStringVector()
    {
        String message = "Mes Sports {";

        message += mesSports.toString();

        message += " }";
        return message;
    }


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
