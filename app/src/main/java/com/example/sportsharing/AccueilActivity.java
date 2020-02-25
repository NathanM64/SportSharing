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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
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
    private MapView mapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    //VARIABLES autres
    Intent demarre;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarHome;
    private Context contextActivity = AccueilActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        //Initialisation de la carte google
        initGoogleMap(savedInstanceState);

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

    public void initGoogleMap(Bundle savedInstanceState)
    {
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);

        mapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        //map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("TestMarker"));
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

            map.addMarker(new MarkerOptions().position(position));
            map.moveCamera(CameraUpdateFactory.newLatLng(position));
            map.animateCamera(CameraUpdateFactory.zoomTo(11), 400, null);
        }
    }
}