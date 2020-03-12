package com.example.sportsharing;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AccueilActivity extends AppCompatActivity {

    //VARIABLES Maquette générale
    private ConstraintLayout carte, activite;
    private BottomNavigationView navBar;
    private TabLayout tab;

    //VARIABLES Maquette (Carte - classique)
    private Button recherche, creation;

    //VARIABLES GoogleMap
    private GoogleMap mapView;
    private FusedLocationProviderClient fusedLocationClient;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

    //VARIABLES autres
    Intent demarre;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarHome;
    private Context contextActivity = AccueilActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil);

        //Demande de permission
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(permissions, 2);
        }

        //Initialisation de la carte GoogleMap
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(new GoogleMapAccueil());

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 2) {
            if (permissions.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mapView.setMyLocationEnabled(true);

                fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    // Logic to handle location object
                                    LatLng position = new LatLng(location.getLatitude(), location.getLongitude());
                                    mapView.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 11));
                                }
                            }
                        });


            } else {
                mapView.setMyLocationEnabled(false);
            }

        }

    }

    //Fonction OnClick
    TabLayout.OnTabSelectedListener tabOnSelected = new TabLayout.OnTabSelectedListener() {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case 0:
                    carte.setVisibility(View.VISIBLE);
                    activite.setVisibility(View.GONE);
                    break;
                case 1:
                    carte.setVisibility(View.GONE);
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


    private class GoogleMapAccueil implements OnMapReadyCallback {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mapView = googleMap;

            mapView.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            mapView.clear();
        }
    }
}