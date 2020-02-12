package com.example.sportsharing.Classe;

import java.util.Vector;

public class Organisateur extends Sportif {

    public Vector<Activite> listActivite;

    public Organisateur(Sportif s){
        super(s.getLogin(), s.getMotDePasse(), s.getConfirmMotDePasse(), s.getNom(),
                s.getPrenom(), s.getAdresseMail(), s.getDateNaissance(), s.getVille(),
                s.getCodePostal(), s.getNumeroTelephone(), s.getDescription());

        listActivite = new Vector();
    }

    public void addActivite(Activite a)
    {
        if(!listActivite.contains(a))
        {
            listActivite.addElement(a);
        }
    }

    public void  supActivite(Activite a)
    {
        if(listActivite.contains(a))
        {
            listActivite.remove(a);
        }
    }
}
