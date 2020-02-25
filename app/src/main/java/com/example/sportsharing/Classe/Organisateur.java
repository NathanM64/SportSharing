package com.example.sportsharing.Classe;

import java.util.Vector;

/**
 * Organisateur est la classe dérivé de <a href="{@docRoot}/com/example/sportsharing/Classe/Sportif.html">Sportif</a> et représente l'utilisateur qui a crée une <a href="{@docRoot}/com/example/sportsharing/Classe/Activite.html">Activité</a>
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 24/02/2020
 */
public class Organisateur extends Sportif {

    /**
     * Liste des activités créés par l'organisateur
     * @see Activite
     */
    public Vector<Activite> listActivite;

    /**
     * <br>
     * <b>Constructeur de l'organisateur</b>
     * @param s     <a href="{@docRoot}/com/example/sportsharing/Classe/Sportif.html">Sportif</a> (utilisateur) de l'application
     */
    public Organisateur(Sportif s){
        super(s.getLogin(), s.getMotDePasse(), s.getConfirmMotDePasse(), s.getNom(),
                s.getPrenom(), s.getAdresseMail(), s.getDateNaissance(), s.getVille(),
                s.getCodePostal(), s.getNumeroTelephone(), s.getDescription());

        listActivite = new Vector();
    }

    /**
     * Ajoute une activite à la liste de l'organisateur
     * @param a     <a href="{@docRoot}/com/example/sportsharing/Classe/Activite.html">Activité</a>
     */
    public void addActivite(Activite a)
    {
        if(!listActivite.contains(a))
        {
            listActivite.addElement(a);
        }
    }

    /**
     * Supprime une activite de la liste de l'organisateur
     * @param a     <a href="{@docRoot}/com/example/sportsharing/Classe/Activite.html">Activité</a>
     */
    public void  supActivite(Activite a)
    {
        if(listActivite.contains(a))
        {
            listActivite.remove(a);
        }
    }
}
