package com.example.sportsharing.Utils;

import android.content.Context;
import android.content.Intent;

import com.example.sportsharing.AccueilActivity;
import com.example.sportsharing.CreerActiviteActivity;
import com.example.sportsharing.ProfilActivity;
import com.example.sportsharing.R;
import com.example.sportsharing.RechercheActiviteActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Classe permettant la navigation dans l'application
 * @author groupe 13 (Mathieu BOCCIARELLI)
 * @version 2.0
 * @since 24/02/2020
 */
public class BottomNavigationViewListener {

    /**
     * Méthode permettant la navigation entre les activités :
     * <ul>
     *     <li>
     *         Accueil
     *     </li>
     *     <li>
     *         Recherche
     *     </li>
     *     <li>
     *         Création
     *     </li>
     *     <li>
     *         Message
     *     </li>
     *     <li>
     *         Profil
     *     </li>
     * </ul>
     * @param context       Activity dans laquelle cette classe est appelé
     * @param navBar        Element xml BottomNavigationView concerné
     */
    public static void typeNavigation(final Context context, final BottomNavigationView navBar)
    {
        navBar.setOnNavigationItemSelectedListener(menuItem -> {
            //Variables
            Intent demarre;

            switch (menuItem.getItemId())
            {
                case R.id.navBarHome:
                    if(navBar.getMenu().findItem(R.id.navBarHome).isChecked()) break; //Regarde si l'item navBarHome est déjà sélectionné, si oui quitte
                    demarre = new Intent(context, AccueilActivity.class);
                    context.startActivity(demarre);
                    break;
                case R.id.navBarSearch:
                    if(navBar.getMenu().findItem(R.id.navBarSearch).isChecked()) break; //Regarde si l'item navBarSearch est déjà sélectionné, si oui quitte
                    demarre = new Intent(context, RechercheActiviteActivity.class);
                    context.startActivity(demarre);
                    break;
                case R.id.navBarActivity:
                    if(navBar.getMenu().findItem(R.id.navBarActivity).isChecked()) break; //Regarde si l'item navBarActivity est déjà sélectionné, si oui quitte
                    demarre = new Intent(context, CreerActiviteActivity.class);
                    context.startActivity(demarre);
                    break;
                case R.id.navBarMessage:
                    if(navBar.getMenu().findItem(R.id.navBarMessage).isChecked()) break; //Regarde si l'item navBarMessage est déjà sélectionné, si oui quitte
                    break;
                case R.id.navBarUser:
                    if(navBar.getMenu().findItem(R.id.navBarUser).isChecked()) break; //Regarde si l'item navBarUser est déjà sélectionné, si oui quitte
                    demarre = new Intent(context, ProfilActivity.class);
                    demarre.putExtra("type", "user");
                    context.startActivity(demarre);
                    break;

            }
            return false;
        });
    }
}
