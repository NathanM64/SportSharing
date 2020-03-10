package com.example.sportsharing;

import com.example.sportsharing.Utils.BottomNavigationViewListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccueilActivity extends AppCompatActivity implements OnMapReadyCallback {

    //VARIABLES Maquette générale
    private ConstraintLayout carte, activite;
    private BottomNavigationView navBar;
    private TabLayout tab;

    //VARIABLES Maquette (Carte - classique)
    private Button recherche, creation;

    //VARIABLES GoogleMap
    private GoogleMap mapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    //VARIABLES autres
    Intent demarre;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarHome;
    private Context contextActivity = AccueilActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        //Initialisation de la carte GoogleMap
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        //Définition des items
            //Général
        carte = findViewById(R.id.ConstraintLayoutAccueilCarte);
        activite = findViewById(R.id.ConstraintLayoutAccueilActivite);
        navBar = findViewById(R.id.bottomNavigationView);
        tab = findViewById(R.id.tab);

            //Carte - classique
        recherche = findViewById(R.id.buttonSearch);
        creation = findViewById(R.id.buttonCreateActivity);

            //Mes Activités


        //Changer icone navBar selectionnee
        navBar.getMenu().findItem(ITEM_NAV_BAR_SELECTED).setChecked(true);

        //OnClick
        tab.addOnTabSelectedListener(tabOnSelected);
        BottomNavigationViewListener.typeNavigation(contextActivity, navBar);
        creation.setOnClickListener(createActivityListner);
        recherche.setOnClickListener(searchActivityListener);
    }

    //Fonction OnClick
    TabLayout.OnTabSelectedListener tabOnSelected = new TabLayout.OnTabSelectedListener() {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    carte.setVisibility(View.VISIBLE);
                    activite.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    carte.setVisibility(View.INVISIBLE);
                    activite.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }
        @Override
        public void onTabUnselected(TabLayout.Tab tab) {}
        @Override
        public void onTabReselected(TabLayout.Tab tab) {}
    };

    //Liaison clic boutton avec l'action
    View.OnClickListener createActivityListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            demarre = new Intent(getApplicationContext(), CreerActiviteActivity.class);
            startActivity(demarre);
        }
    };

    View.OnClickListener searchActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            demarre = new Intent(getApplicationContext(), RechercheActiviteActivity.class);
            startActivity(demarre);
        }
    };

    //////////////////////////////////////////////////////////////////////////////
    //  Définition des méthodes pour initialiser la map Google                  //
    //////////////////////////////////////////////////////////////////////////////

    @Override
    public void onMapReady(GoogleMap map) {

        mapView = map;

        Geocoder geo = new Geocoder(AccueilActivity.this);
        List<Address> list = new ArrayList<>();

        try {
            list = geo.getFromLocationName("Paris", 1);
        } catch (IOException e) {
            System.out.println("Pas de ville trouvé");
        }

        if(list.size() > 0)
        {
            LatLng position = new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());

            mapView.addMarker(new MarkerOptions().position(position));
            mapView.moveCamera(CameraUpdateFactory.newLatLng(position));
            mapView.animateCamera(CameraUpdateFactory.zoomTo(11), 400, null);
        }
    }
}