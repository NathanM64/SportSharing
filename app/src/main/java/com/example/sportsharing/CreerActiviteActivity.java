package com.example.sportsharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreerActiviteActivity extends AppCompatActivity {

    //VARIABLES maquette
    private BottomNavigationView navBar;

    //VARIABLES autres
    Intent demarre;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarActivity;
    private Context contextActivity = CreerActiviteActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_activite);

        navBar = findViewById(R.id.bottomNavigationView);

        //Changer icone navBar selectionnee
        navBar.getMenu().findItem(ITEM_NAV_BAR_SELECTED).setChecked(true);

        BottomNavigationViewListener.typeNavigation(contextActivity, navBar);
    }

    //Méthodes onClick


}
