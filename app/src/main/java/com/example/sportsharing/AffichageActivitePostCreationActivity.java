package com.example.sportsharing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sportsharing.Classe.DossierVariableClasse;
import com.example.sportsharing.ClasseDAO.ActiviteDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import timber.log.Timber;

public class AffichageActivitePostCreationActivity extends AppCompatActivity {

    //VARIABLES maquettes
    private TextView nameUser, sport, niveau, number, dateActivite, adresseActivite, time, description;
    private Button suite;
    private FloatingActionButton back;

    //VARIABLES autres
    DossierVariableClasse global;
    String context;

    //VARIABLES MapBox
    private MapView mapView;
    private MapboxMap mapboxMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affichage_activite_post_creation);

        //Récupération des extras
        context = this.getIntent().getStringExtra("context");

        //Récupération de DossierVariableGlobal
        global = DossierVariableClasse.getInstance();

        //Initialisation des variables de la maquette
        nameUser = findViewById(R.id.nameUser);
        sport = findViewById(R.id.sport);
        niveau = findViewById(R.id.niveau);
        number = findViewById(R.id.number);
        dateActivite = findViewById(R.id.dateActivite);
        adresseActivite = findViewById(R.id.adresseActivite);
        time = findViewById(R.id.time);
        description = findViewById(R.id.description);
        suite = findViewById(R.id.suite);
        back = findViewById(R.id.back);
        mapView = findViewById(R.id.mapView);

        //Init Activite
        InitActivite();

        //Init map
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new MapAccueil());

        //Attribution listeners bouton
        back.setOnClickListener(returnCreation);
        suite.setOnClickListener(createActivity);
    }

    private void InitActivite() {

        nameUser.setText(global.activiteCurrent.createur.getNom()+ " " + global.activiteCurrent.createur.getPrenom());
        sport.setText(global.activiteCurrent.sport.libelle.toString().replace("/", " "));
        niveau.setText(global.activiteCurrent.niveauSport.toString().replace("_", " "));
        number.setText(global.activiteCurrent.nbMaxPersonnes +"");

        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(global.activiteCurrent.getJour());
            String newDate = new SimpleDateFormat("EEEE dd MMMM yyyy").format(date);
            dateActivite.setText(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        adresseActivite.setText(global.activiteCurrent.lieu.toString());

        //Calcul du time
        int t, hHeureDebut, hHeureFin, mHeureDebut, mHeureFin; //mins et heures
        hHeureDebut = Integer.parseInt(global.activiteCurrent.getHeureDebut().split(":")[0]);
        mHeureDebut = Integer.parseInt(global.activiteCurrent.getHeureDebut().split(":")[1]);
        hHeureFin = Integer.parseInt(global.activiteCurrent.getHeureFin().split(":")[0]);
        mHeureFin = Integer.parseInt(global.activiteCurrent.getHeureFin().split(":")[1]);
        boolean isMin = false;
        if(hHeureDebut == hHeureFin) {
            t = mHeureFin - mHeureDebut;
            isMin = true;
        }
        else {
            t = hHeureFin - hHeureDebut;
        }

        time.setText(hHeureDebut +"h" + (mHeureDebut<10?"0":"") + mHeureDebut + " - Dure environ : " + t + (isMin?" min(s)":" heure(s)"));
        description.setText(global.activiteCurrent.getDescription());
    }


    //Listeners
    View.OnClickListener returnCreation = view -> AffichageActivitePostCreationActivity.this.finish();
    View.OnClickListener createActivity = view -> {
        ActiviteDAO activiteDAO = new ActiviteDAO(AffichageActivitePostCreationActivity.this);
        activiteDAO.addActivite(global.activiteCurrent);
        Intent demarre = new Intent(getApplicationContext(), AccueilActivity.class);
        startActivity(demarre);
        finish();
    };


    //////////////////////////////////////////////////////////////////////////////
    //  Définition des méthodes pour initialiser la map Google                  //
    //////////////////////////////////////////////////////////////////////////////


    private class MapAccueil implements OnMapReadyCallback {

        @Override
        public void onMapReady(@NonNull MapboxMap mapboxMap) {
            AffichageActivitePostCreationActivity.this.mapboxMap = mapboxMap;

            mapboxMap.setStyle(Style.MAPBOX_STREETS, style -> startLocationUser(style));

            //Recherche de la géo selon la saisie
            Geocoder geo = new Geocoder(AffichageActivitePostCreationActivity.this);
            List<Address> list = new ArrayList<>();

            try {
                list = geo.getFromLocationName(global.activiteCurrent.lieu.toString(), 1);
            } catch (IOException e) {
                Timber.d("Pas de ville trouvé");
            }

            if(list.size() > 0)
            {
                LatLng position = new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());
                mapboxMap.addMarker(new MarkerOptions().position(position));
                mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 13));
            }
        }
    }

    private void startLocationUser(@NonNull Style loadedMapStyle) {

        if(ContextCompat.checkSelfPermission(AffichageActivitePostCreationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(AffichageActivitePostCreationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            // Activate with options
            locationComponent.activateLocationComponent(LocationComponentActivationOptions.builder(this, loadedMapStyle).build());

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
        }
    }
}
