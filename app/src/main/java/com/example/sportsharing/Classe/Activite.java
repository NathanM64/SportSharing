package com.example.sportsharing.Classe;

import java.sql.Date;
import java.sql.Time;

public class Activite {

    //VARIABLES
    protected int id;
    protected Date jour;
    protected Time heureDebut;
    protected Time heureFin;
    protected String description = "";
    public int nbMaxPersonnes;
    public boolean estTermine;

    Organisateur createur;
    Adresse lieu;
    Sport sport;

    //CONSTRUCTEURS

    public Activite(int id, Date jour, Time heureDebut, Time heureFin, String description, int nbMaxPersonnes, Organisateur createur, Adresse lieu, Sport sport) {
        this.id = id;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.description = description;
        this.nbMaxPersonnes = nbMaxPersonnes;
        this.estTermine = false;
        this.createur = createur;
        this.lieu = lieu;
        this.sport = sport;

        //Ajout de l'activité à l'adresse
        lieu.addActivite(this);

        //Ajout de l'activité à l'organisateur
        createur.addActivite(this);
    }

    //METHODES GETTER
    public Date getJour() {
        return jour;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public String getDescription() {
        return description;
    }

    public int getNbMaxPersonnes() {
        return nbMaxPersonnes;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public Organisateur getCreateur() {
        return createur;
    }

    public Adresse getLieu() {
        return lieu;
    }

    //METHODES SETTER

    public void setJour(Date jour) {
        this.jour = jour;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbMaxPersonnes(int nbMaxPersonnes) {
        this.nbMaxPersonnes = nbMaxPersonnes;
    }

    public void setEstTermine(boolean estTermine) {
        this.estTermine = estTermine;
    }

    public void setLieu(Adresse lieu) {
        this.lieu = lieu;
    }

    @Override
    public String toString() {
        return "Activite{" +
                "jour=" + jour +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", description='" + description + '\'' +
                ", nbMaxPersonnes=" + nbMaxPersonnes +
                ", estTermine=" + estTermine +
                ", createur=" + createur +
                ", sport=" + sport +
                '}';
    }
}
