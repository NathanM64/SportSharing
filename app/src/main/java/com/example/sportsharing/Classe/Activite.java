package com.example.sportsharing.Classe;

import java.sql.Date;
import java.sql.Time;

/**
 * Activite est la classe représentant une activite sportive créé par un organisateur
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 24/02/2020
 */
public class Activite {

    //VARIABLES
    /**
     * Identifiant de l'activité
     */
    protected int id;
    /**
     * Date du jour de réalisation de l'activité
     */
    protected Date jour;
    /**
     * Heure de début de l'activité
     */
    protected Time heureDebut;
    /**
     * Heure de fin théorique de l'activité
     */
    protected Time heureFin;
    /**
     * Description de l'activité
     */
    protected String description = "";
    /**
     * Nombre de participants maximum à l'activité
     */
    public int nbMaxPersonnes;
    /**
     * (True) si l'activité est terminé, (False) sinon
     */
    public boolean estTermine;

    /**
     * Organisateur de l'activité
     * @see Activite
     */
    public Organisateur createur;
    /**
     * Lieu de l'activité
     * @see Adresse
     */
    public Adresse lieu;
    /**
     * Sport de l'activité
     * @see Sport
     */
    public Sport sport;

    //CONSTRUCTEURS

    /**
     * <br>
     * <b>Constructeur de l'activité</b>
     * @param id                Identifiant de l'activité
     * @param jour              Jour de participation à l'activité
     * @param heureDebut        Heure de début de l'activité
     * @param heureFin          Heure de fin de l'activité
     * @param description       Description de l'activité
     * @param nbMaxPersonnes    Nombre maximum de participants
     * @param createur          Créateur de l'activité
     * @param lieu              Lieu de l'activité
     * @param sport             Sport concerné par l'activité
     */
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

    /**
     *
     * @return Retourne le jour de participation
     */
    public Date getJour() {
        return jour;
    }

    /**
     *
     * @return Retourne l'heure de début de l'activité
     */
    public Time getHeureDebut() {
        return heureDebut;
    }

    /**
     *
     * @return Retourne l'heure de fin de l'activité
     */
    public Time getHeureFin() {
        return heureFin;
    }

    /**
     *
     * @return Retourne la description de l'activité
     */
    public String getDescription() {
        return description;
    }

    //METHODES SETTER

    /**
     *
     * @param jour              Définit le jour de participation
     */
    public void setJour(Date jour) {
        this.jour = jour;
    }

    /**
     *
     * @param heureDebut        Définit l'heure de début de l'activité
     */
    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    /**
     *
     * @param heureFin          Définit l'heure de fin de l'activité
     */
    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    /**
     *
     * @param description       Définit la description de l'activité
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @param nbMaxPersonnes    Définit le nombre maximum de participants à l'activité
     */
    public void setNbMaxPersonnes(int nbMaxPersonnes) {
        this.nbMaxPersonnes = nbMaxPersonnes;
    }

    /**
     *
     * @param estTermine        Définit si l'activité est terminé (True) ou non(False)
     */
    public void setEstTermine(boolean estTermine) {
        this.estTermine = estTermine;
    }

    /**
     *
     * @param lieu              Définit le lieu de l'activité
     */
    public void setLieu(Adresse lieu) {
        this.lieu = lieu;
    }

    /**
     *
     * @return Retourne le détails de l'activité
     */
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
