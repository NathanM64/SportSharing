package com.example.sportsharing.Classe;

import java.util.Vector;

/**
 * Adresse est la classe représentant un lieu de l'activité
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 24/02/2020
 */
public class Adresse {

    //VARIABLES
    private int numeroRue;
    private String nomRue;
    private int codePostal;
    private String ville;

    /**
     *
     * @see Activite
     */
    public Vector<Activite> possede;

    //CONSTRUCTEURS

    /**
     * <br>
     * <b>Constructeur d'une adresse</b>
     * @param numeroRue     Numero de la rue de l'adresse du lieu
     * @param nomRue        Nom de la rue de l'adresse du lieu
     * @param codePostal    Code postal de l'adresse du lieu
     * @param ville         Ville de l'adresse du lieu
     */
    public Adresse(int numeroRue, String nomRue, int codePostal, String ville) {
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.possede = new Vector();
    }

    //METHODES GETTER

    /**
     *
     * @return  Retourne le numero de rue
     */
    public int getNumeroRue() {
        return numeroRue;
    }

    /**
     *
     * @return  Retourne le nom de la rue
     */
    public String getNomRue() {
        return nomRue;
    }

    /**
     *
     * @return  Retourne le code postal
     */
    public int getCodePostal() {
        return codePostal;
    }

    /**
     *
     * @return  Retourne la ville
     */
    public String getVille() {
        return ville;
    }

    /**
     *
     * @return  Retourne la liste des activités qui posséde ce lieu
     */
    public Vector<Activite> getPossede() {
        return possede;
    }

    //AUTRES METHODES

    /**
     * Ajoute une activité au lieu
     * @param a     <a href="{@docRoot}/com/example/sportsharing/Classe/Activite.html">Activite</a>
     */
    public void addActivite(Activite a)
    {
        if(!possede.contains(a))
        {
            possede.addElement(a);
        }
    }

    /**
     * Supprime une activité au lieu
     * @param a     <a href="{@docRoot}/com/example/sportsharing/Classe/Activite.html">Activite</a>
     */
    public void  supActivite(Activite a)
    {
        if(possede.contains(a))
        {
            possede.remove(a);
        }
    }

    /**
     *
     * @return Retourne le détails de l'adresse
     */
    @Override
    public String toString() {
        return "Adresse{" +
                "numeroRue=" + numeroRue +
                ", nomRue='" + nomRue + '\'' +
                ", codePostal=" + codePostal +
                ", ville='" + ville + '\'' +
                ", possede=" + possede +
                '}';
    }
}
