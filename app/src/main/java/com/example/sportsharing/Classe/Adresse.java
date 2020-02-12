package com.example.sportsharing.Classe;

import java.util.Vector;

public class Adresse {

    //VARIABLES
    private int numeroRue;
    private String nomRue;
    private int codePostal;
    private String ville;

    /**
     *
     * @element-type Activite
     */
    public Vector<Activite> possede;

    //CONSTRUCTEURS

    public Adresse(int numeroRue, String nomRue, int codePostal, String ville) {
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.possede = new Vector();
    }

    //METHODES GETTER
    public int getNumeroRue() {
        return numeroRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getVille() {
        return ville;
    }

    public Vector<Activite> getPossede() {
        return possede;
    }

    //AUTRES METHODES

    public void addActivite(Activite a)
    {
        if(!possede.contains(a))
        {
            possede.addElement(a);
        }
    }

    public void  supActivite(Activite a)
    {
        if(possede.contains(a))
        {
            possede.remove(a);
        }
    }

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
