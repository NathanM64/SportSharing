package com.example.sportsharing.Classe;

/**
 * Sport est la classe représentant un sport
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 24/02/2020
 */
public class Sport {
    //VARIABLES
    /**
     * Nom du sport
     */
    public EnumUtil.NameSport libelle;
    /**
     * Type du sport
     */
    public EnumUtil.TypeSport type;

    /**
     *  <br>
     *  <b>Constructeur du sport avec comme type EnumUtil.TypeSport.Autre</b>
     * @param libelle   Nom du sport
     */
    public Sport(EnumUtil.NameSport libelle) {
        this.libelle = libelle;
        this.type = EnumUtil.TypeSport.Autre;
    }

    /**
     *  <br>
     *  <b>Constructeur du sport</b>
     * @param libelle   Nom du sport
     * @param type      Type du sport
     */
    public Sport(EnumUtil.NameSport libelle, EnumUtil.TypeSport type) {
        this.libelle = libelle;
        this.type = type;
    }

    /**
     *
     * @return Retourne le nom du sport
     */
    public EnumUtil.NameSport getLibelle() {
        return libelle;
    }

    /**
     *
     * @return Retourne le type du sport
     */
    public EnumUtil.TypeSport getType() {
        return type;
    }

    /**
     *
     * @return Retourne le détails du sport
     */
    @Override
    public String toString() {
        return "Sport{" +
                "libelle=" + libelle +
                ", type=" + type +
                '}';
    }
}
