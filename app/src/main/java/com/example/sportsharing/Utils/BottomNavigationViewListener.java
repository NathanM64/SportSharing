package com.example.sportsharing.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.sportsharing.AccueilActivity;
import com.example.sportsharing.CreerActiviteActivity;
import com.example.sportsharing.ProfilActivity;
import com.example.sportsharing.R;
import com.example.sportsharing.RechercheActiviteActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;

public class BottomNavigationViewListener {

    public static void typeNavigation(final Context context, final BottomNavigationView navBar)
    {
        navBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
                        context.startActivity(demarre);
                        break;

                }
                return false;
            }
        });
    }
}
