package com.example.sportsharing;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sportsharing.Classe.Activite;
import com.example.sportsharing.Classe.DossierVariableClasse;
import com.example.sportsharing.ClasseDAO.ActiviteDAO;
import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class AccueilActivity extends AppCompatActivity {

    //VARIABLES Maquette générale
    private ConstraintLayout carte, activite;
    private BottomNavigationView navBar;
    private TabLayout tab;

    //VARIABLES Maquette (Carte - classique)
    private Button recherche, creation;

    //VARIABLES Mes Activités
    private TableLayout TableLayoutRegisteredActivity, TableLayoutCompletedActivity, TableLayoutMyActivites;

    //VARIABLES MapBox
    private MapView mapView;
    private MapboxMap mapboxMap;

    //VARIABLES autres
    Intent demarre;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarHome;
    private Context contextActivity = AccueilActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(contextActivity, getString(R.string.access_token_api));
        setContentView(R.layout.accueil);

        //Demande de permission
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.requestPermissions(permissions, 2);
        }

        //Définition des items
        //Général
        carte = findViewById(R.id.ConstraintLayoutAccueilCarte);
        activite = findViewById(R.id.ConstraintLayoutAccueilActivite);
        navBar = findViewById(R.id.bottomNavigationView);
        tab = findViewById(R.id.tab);
        mapView = findViewById(R.id.mapView);

        //Carte - classique
        recherche = findViewById(R.id.buttonSearch);
        creation = findViewById(R.id.buttonCreateActivity);

        //Mes Activités
        TableLayoutRegisteredActivity = findViewById(R.id.TableLayoutRegisteredActivity);
        TableLayoutCompletedActivity = findViewById(R.id.TableLayoutCompletedActivity);
        TableLayoutMyActivites = findViewById(R.id.TableLayoutMyActivites);

        //Changer icone navBar selectionnee
        navBar.getMenu().findItem(ITEM_NAV_BAR_SELECTED).setChecked(true);

        //Init map
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new MapAccueil());

        //OnClick
        tab.addOnTabSelectedListener(tabOnSelected);
        BottomNavigationViewListener.typeNavigation(contextActivity, navBar);
        creation.setOnClickListener(createActivityListener);
        recherche.setOnClickListener(searchActivityListener);

        initTabMesActivites();
    }

    public void initTabActiviteInscrite(){

        ActiviteDAO activiteDAO= new ActiviteDAO(this);

        ArrayList<Activite> activites = activiteDAO.getAllActiviteInscriteBySportifLogin(DossierVariableClasse.getInstance().utilisateur.getLogin());

        if(!activites.isEmpty()) {
            LayoutInflater inflate = getLayoutInflater();

            TableRow mesActivites = (TableRow) inflate.inflate(R.layout.model_tablerow_accueil, null);


            for (Activite a : activites) {
                ((TextView) ((LinearLayout) mesActivites.getChildAt(1)).getChildAt(0)).setText(a.lieu.getVille());
                ((TextView) ((LinearLayout) mesActivites.getChildAt(1)).getChildAt(1)).setText(a.getJour());
            }
            TableLayoutMyActivites.addView(mesActivites);
        }

    }
    public void initTabActiviteTermine(){

        ActiviteDAO activiteDAO= new ActiviteDAO(this);

        ArrayList<Activite> activites = activiteDAO.getAllActiviteTermineBySportifLogin(DossierVariableClasse.getInstance().utilisateur.getLogin());
        if(!activites.isEmpty()) {
            LayoutInflater inflate = getLayoutInflater();

            TableRow mesActivites = (TableRow) inflate.inflate(R.layout.model_tablerow_accueil, null);


            for (Activite a : activites) {
                ((TextView) ((LinearLayout) mesActivites.getChildAt(1)).getChildAt(0)).setText(a.lieu.getVille());
                ((TextView) ((LinearLayout) mesActivites.getChildAt(1)).getChildAt(1)).setText(a.getJour());
            }
            TableLayoutMyActivites.addView(mesActivites);
        }

    }
    public void initTabMesActivites(){

        ActiviteDAO activiteDAO= new ActiviteDAO(this);

        ArrayList<Activite> activites = activiteDAO.getAllActiviteBySportifLogin(DossierVariableClasse.getInstance().utilisateur.getLogin());
        if(!activites.isEmpty()) {
            LayoutInflater inflate = getLayoutInflater();

            TableRow mesActivites = (TableRow) inflate.inflate(R.layout.model_tablerow_accueil, null);


            for (Activite a : activites) {
                ((TextView) ((LinearLayout) mesActivites.getChildAt(1)).getChildAt(0)).setText(a.lieu.getVille());
                ((TextView) ((LinearLayout) mesActivites.getChildAt(1)).getChildAt(1)).setText(a.getJour());
            }
            TableLayoutMyActivites.addView(mesActivites);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 2) {
            if (permissions.length != 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED ||
                    grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                if(mapboxMap != null) {
                    mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                        @Override
                        public void onStyleLoaded(@NonNull Style style) {
                            startLocationUser(style);
                        }
                    });
                }
            } else {

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
    View.OnClickListener createActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            demarre = new Intent(getApplicationContext(), CreerActiviteActivity.class);
            startActivity(demarre);
            finish();
        }
    };

    View.OnClickListener searchActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            demarre = new Intent(getApplicationContext(), RechercheActiviteActivity.class);
            startActivity(demarre);
            finish();
        }
    };

    //////////////////////////////////////////////////////////////////////////////
    //  Définition des méthodes pour initialiser la map Google                  //
    //////////////////////////////////////////////////////////////////////////////


    private class MapAccueil implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull MapboxMap mapboxMap) {
            AccueilActivity.this.mapboxMap = mapboxMap;

            mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    startLocationUser(style);
                }
            });
        }
    }

    private void startLocationUser(@NonNull Style loadedMapStyle) {
        if(ContextCompat.checkSelfPermission(AccueilActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(AccueilActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            /* Activate with options */
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this, loadedMapStyle).build());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

            //Zoom
            Location posUser = locationComponent.getLastKnownLocation();
            mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(posUser.getLatitude(), posUser.getLongitude()), 14));
        }
    }
}