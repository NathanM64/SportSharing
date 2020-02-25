package com.example.sportsharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreerActiviteActivity extends AppCompatActivity {

    //VARIABLES maquette
    private BottomNavigationView navBar;
    private Button cancel, confirm;

    //VARIABLES autres
    Intent demarre;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarActivity;
    private Context contextActivity = CreerActiviteActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_activite);

        //Initialisation des variables
        navBar = findViewById(R.id.bottomNavigationView);
        cancel = findViewById(R.id.buttonCancel);
        confirm = findViewById(R.id.buttonConfirm);

        //Changer icone navBar selectionnee
        navBar.getMenu().findItem(ITEM_NAV_BAR_SELECTED).setChecked(true);

        //Liaison clic boutton avec l'action
        cancel.setOnClickListener(cancelListener);
        confirm.setOnClickListener(confirmListener);
        BottomNavigationViewListener.typeNavigation(contextActivity, navBar);
    }

    //Méthodes onClick

    View.OnClickListener cancelListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            demarre = new Intent(getApplicationContext(), AccueilActivity.class);
            startActivity(demarre);
        }
    };
    View.OnClickListener confirmListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            demarre = new Intent(getApplicationContext(), AccueilActivity.class);
            startActivity(demarre);
        }
    };


}
