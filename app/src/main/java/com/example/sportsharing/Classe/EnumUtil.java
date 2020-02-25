package com.example.sportsharing.Classe;

/**
 * EnumUtil est la classe déclarant les différents énumération utile pour la définition du sport
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 1.0
 * @since 24/02/2020
 */
public class EnumUtil {

    /**
     * Enumeration du nom des différents sports possible
     */
    public enum NameSport {
        Course,
        Danse,
        Gymnastique,
        HandBall,
        Musculation,
        Musique,
        Pelote,
        Rugby,
        Surf,
        Tennis,
        Velo
    }

    /**
     * Catégorie des différents sports possible
     */
    public enum TypeSport {
        Autre,
        Ballon,
        Raquette
    }

    /**
     * Niveau décrivant le niveau du <a href="{@docRoot}/com/example/sportsharing/Classe/Sportif.html">Sportif</a> associé au nom d'un sport
     */
    public enum NiveauSport {
        Debutant,
        Intermediaire,
        Non_Renseigne,
        Occasionnel,
        Professionnel
    }
}
