package com.example.sportsharing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RechercheActiviteActivity extends AppCompatActivity {

    //VARIABLES maquette
    private BottomNavigationView navBar;

    //VARIABLES autres
    Intent demarre;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarSearch;
    private Context contextActivity = RechercheActiviteActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_activite);

        navBar = findViewById(R.id.bottomNavigationView);

        //Changer icone navBar selectionnee
        navBar.getMenu().findItem(ITEM_NAV_BAR_SELECTED).setChecked(true);

        BottomNavigationViewListener.typeNavigation(contextActivity, navBar);
    }

    //Fonction OnClick


}
