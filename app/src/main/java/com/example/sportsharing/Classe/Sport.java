package com.example.sportsharing.Classe;

public class Sport {
    //VARIABLES
    public EnumUtil.NameSport libelle;
    public EnumUtil.TypeSport type;

    public Sport(EnumUtil.NameSport libelle, EnumUtil.TypeSport type) {
        this.libelle = libelle;
        this.type = type;
    }

    public EnumUtil.NameSport getLibelle() {
        return libelle;
    }

    public EnumUtil.TypeSport getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "libelle=" + libelle +
                ", type=" + type +
                '}';
    }
}
