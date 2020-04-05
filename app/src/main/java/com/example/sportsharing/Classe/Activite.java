package com.example.sportsharing.Classe;

/**
 * Activite est la classe représentant une activite sportive créé par un organisateur
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 2.0
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
    protected String jour;
    /**
     * Heure de début de l'activité<br>
     * Model : jj/MM/yyyy
     */
    protected String heureDebut;
    /**
     * Heure de fin théorique de l'activité<br>
     * Model : hh:mm
     */
    protected String heureFin;
    /**
     * Description de l'activité<br>
     * Model : hh:mm
     */
    protected String description = "";
    /**
     * Nombre de participants maximum à l'activité
     */
    public int nbMaxPersonnes;
    /**
     * Niveau minimum conseillé pour pratiquer l'activité
     * @see EnumUtil.NiveauSport
     */
    public EnumUtil.NiveauSport niveauSport;
    /**
     * (True) si l'activité est terminé<br>
     * (False) sinon
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
     * @param niveau            Niveau conseillé pour pratiquer l'activité
     * @param createur          Créateur de l'activité
     * @param lieu              Lieu de l'activité
     * @param sport             Sport concerné par l'activité
     */
    public Activite(int id, String jour, String heureDebut, String heureFin, String description, int nbMaxPersonnes, EnumUtil.NiveauSport niveau, Organisateur createur, Adresse lieu, Sport sport) {
        this.id = id;
        this.jour = jour;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.description = description;
        this.nbMaxPersonnes = nbMaxPersonnes;
        this.niveauSport = niveau;
        this.estTermine = false;
        this.createur = createur;
        this.lieu = lieu;
        this.sport = sport;

        //Ajout de l'activité à l'adresse
        lieu.addActivite(this);

        //Ajout de l'activité à l'organisateur
        createur.addActivite(this);
    }

    //METHODES GETTER & SETTER

    /**
     * Retourne l'identifiant de l'activité
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Définit l'identifiant pour une activité
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retourne le jour d'une activité
     * @return
     */
    public String getJour() {
        return jour;
    }

    /**
     * Définit le jour d'une activité
     * @param jour
     */
    public void setJour(String jour) {
        this.jour = jour;
    }

    /**
     * Retourne l'heure de début d'une activité
     * @return
     */
    public String getHeureDebut() {
        return heureDebut;
    }

    /**
     * Définit l'heure de début d'une activité
     * @param heureDebut
     */
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    /**
     * Retourne l'heure de fin d'une activité
     * @return
     */
    public String getHeureFin() {
        return heureFin;
    }

    /**
     * Définit l'heure de fin d'une activité
     * @param heureFin
     */
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    /**
     * Retourne le description d'une activité
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit le description d'une activité
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne le nombre maximum de personnes pourvant pratiquer l'activité
     * @return
     */
    public int getNbMaxPersonnes() {
        return nbMaxPersonnes;
    }

    /**
     * Définit le nombre maximum de personnes pourvant pratiquer l'activité
     * @param nbMaxPersonnes
     */
    public void setNbMaxPersonnes(int nbMaxPersonnes) {
        this.nbMaxPersonnes = nbMaxPersonnes;
    }

    /**
     * Retourne le niveau conseillé pour pratiquer l'activité
     * @return
     */
    public EnumUtil.NiveauSport getNiveauSport() {
        return niveauSport;
    }

    /**
     * Définit le niveau conseillé pour pratiquer l'activité
     * @param niveauSport
     */
    public void setNiveauSport(EnumUtil.NiveauSport niveauSport) {
        this.niveauSport = niveauSport;
    }

    /**
     * Retourne si l'activité est terminé
     * @return (TRUE) l'activité est terminé sinon (FALSE)
     */
    public boolean isEstTermine() {
        return estTermine;
    }

    /**
     * Définit si l'activité est terminé
     * @param estTermine    (TRUE) l'activité est terminé ou non (FALSE)
     */
    public void setEstTermine(boolean estTermine) {
        this.estTermine = estTermine;
    }

    /**
     * Retourne le créateur de l'activité
     * @see Organisateur
     * @return
     */
    public Organisateur getCreateur() {
        return createur;
    }

    /**
     * Définit le créateur de l'activité
     * @see Organisateur
     * @param createur
     */
    public void setCreateur(Organisateur createur) {
        this.createur = createur;
    }

    /**
     * Retourne le lieu de l'activité
     * @see Adresse
     * @return
     */
    public Adresse getLieu() {
        return lieu;
    }

    /**
     * Définit le lieu de l'activité
     * @see Adresse
     * @param lieu
     */
    public void setLieu(Adresse lieu) {
        this.lieu = lieu;
    }

    /**
     * Retourne le sport de l'activité
     * @see Sport
     * @return
     */
    public Sport getSport() {
        return sport;
    }

    /**
     * Définit le sport de l'activité
     * @see Sport
     * @param sport
     */
    public void setSport(Sport sport) {
        this.sport = sport;
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
