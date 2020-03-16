package com.example.sportsharing;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mapbox.mapboxsdk.plugins.places.picker.PlacePicker;
import com.mapbox.mapboxsdk.plugins.places.picker.model.PlacePickerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RechercheActiviteActivity extends AppCompatActivity {

    //VARIABLES maquette
    private BottomNavigationView navBar;
    private Spinner spinnerSport, spinnerLevel;
    private EditText lieu;
    private TextView displayDay, displayTime;
    private ImageView imageDay, imageTime;
    private Button buttonCancel, buttonConfirm;
    private TableLayout tableSuggestion;

    //VARIABLES Date et Time
    private int jour, mois, annee, heure, minute;


    //VARIABLES autres
    Intent demarre;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private static final int REQUEST_CODE_PLACEPICKER = 2;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarSearch;
    private Context contextActivity = RechercheActiviteActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recherche_activite);

        //Initialisation des variables
        navBar = findViewById(R.id.bottomNavigationView);
        spinnerSport = findViewById(R.id.spinnerSport);
        spinnerLevel = findViewById(R.id.spinnerLevel);
        lieu = findViewById(R.id.lieu);
        displayDay = findViewById(R.id.displayDay);
        displayTime = findViewById(R.id.displayTime);
        imageDay = findViewById(R.id.imageDay);
        imageTime = findViewById(R.id.imageTime);
        buttonCancel = findViewById(R.id.buttonCancel);
        buttonConfirm = findViewById(R.id.buttonConfirm);
        tableSuggestion = findViewById(R.id.tableSuggestion);


        //Changer icone navBar selectionnee
        navBar.getMenu().findItem(ITEM_NAV_BAR_SELECTED).setChecked(true);

        //Listener
        BottomNavigationViewListener.typeNavigation(contextActivity, navBar);
        imageDay.setOnClickListener(openCalendar);
        imageTime.setOnClickListener(openTimer);
        lieu.setOnFocusChangeListener(openPlacePicker);

        //Init Date
        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        String dateCurrent = currentDate.format(new Date());
        annee = Integer.parseInt(dateCurrent.substring(6));
        mois = Integer.parseInt(dateCurrent.substring(3,5));
        jour = Integer.parseInt(dateCurrent.substring(0,2));

        //Init time
        @SuppressLint("SimpleDateFormat") SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        String timeCurrent = currentTime.format(new Date());
        heure = Integer.parseInt(timeCurrent.substring(0,2));
        minute = Integer.parseInt(timeCurrent.substring(3));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_AUTOCOMPLETE) {

            CarmenFeature selectedCarmenFeature = PlaceAutocomplete.getPlace(data);

            //Start le PlacePicker
            startActivityForResult(
                    new PlacePicker.IntentBuilder()
                            .accessToken(getString(R.string.access_token_api))
                            .placeOptions(PlacePickerOptions.builder()
                                    .statingCameraPosition(new CameraPosition.Builder()
                                            .target(new LatLng(((Point) selectedCarmenFeature.geometry()).latitude(), ((Point) selectedCarmenFeature.geometry()).longitude())).zoom(14).build())
                                    .build())
                            .build(this), REQUEST_CODE_PLACEPICKER);
        }

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_PLACEPICKER) {
            CarmenFeature carmenFeature = PlacePicker.getPlace(data);

            //Récupération des index de départ et de fin qui représentent l'adresse choisi
            int start = carmenFeature.toJson().lastIndexOf("place_name") + 13;
            int fin = carmenFeature.toJson().lastIndexOf("place_type") - 3;

            lieu.setText(carmenFeature.toJson().substring(start, fin));
        }
    }

    //Fonction OnClick
    View.OnFocusChangeListener openPlacePicker = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if(b) {
                lieu.clearFocus(); //Enleve le focus de l'edit text

                //Start le PlaceAutoComplete
                Intent intent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken(getString(R.string.access_token_api))
                        .placeOptions(PlaceOptions.builder()
                                .backgroundColor(Color.parseColor("#EEEEEE"))
                                .limit(10)
                                .build(PlaceOptions.MODE_CARDS))
                        .build(RechercheActiviteActivity.this);
                startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
            }

        }
    };
    View.OnClickListener openCalendar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Calendar c = Calendar.getInstance();
            annee = c.get(Calendar.YEAR);
            mois = c.get(Calendar.MONTH);
            jour = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(RechercheActiviteActivity.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                            String dateCurrent = currentDate.format(new Date());
                            int yearCurrent = Integer.parseInt(dateCurrent.substring(6));
                            int monthOfYearCurrent = Integer.parseInt(dateCurrent.substring(3,5));
                            int dayOfMonthCurrent = Integer.parseInt(dateCurrent.substring(0,2));

                            boolean error = false;
                            if(year <= yearCurrent) {
                                if(monthOfYear <= monthOfYearCurrent){
                                    if(dayOfMonth < dayOfMonthCurrent) {
                                        Toast.makeText(contextActivity, "Veuillez choisir une date correcte", Toast.LENGTH_LONG).show();
                                        displayDay.setText("");
                                        error = true;
                                    }
                                }
                            }

                            if(!error)
                                displayDay.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, annee, mois, jour);
            datePickerDialog.show();
        }
    };

    View.OnClickListener openTimer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Calendar c = Calendar.getInstance();
            heure = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(RechercheActiviteActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minuteOfHour) {
                            //Date
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                            String dateCurrent = currentDate.format(new Date());
                            int yearCurrent = Integer.parseInt(dateCurrent.substring(6));
                            int monthOfYearCurrent = Integer.parseInt(dateCurrent.substring(3,5));
                            int dayOfMonthCurrent = Integer.parseInt(dateCurrent.substring(0,2));

                            //Heure
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
                            String timeCurrent = currentTime.format(new Date());
                            int hourCurrent = Integer.parseInt(timeCurrent.substring(0,2));
                            int minuteCurrent = Integer.parseInt(timeCurrent.substring(3));

                            boolean error = false;
                            if(annee <= yearCurrent) {
                                if(mois <= monthOfYearCurrent){
                                    if(jour <= dayOfMonthCurrent) {
                                        if(hourOfDay <= hourCurrent) {
                                            if(minuteOfHour <= minuteCurrent) {
                                                Toast.makeText(contextActivity, "Veuillez choisir une heure correcte", Toast.LENGTH_LONG).show();
                                                displayTime.setText("");
                                                error = true;
                                            }
                                        }
                                    }
                                }
                            }

                            if(!error)
                                displayTime.setText(hourOfDay + ":" + minuteOfHour);
                        }
                    }, heure, minute, true);
            timePickerDialog.show();
        }
    };

}
