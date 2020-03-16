package com.example.sportsharing;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;

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

import java.util.Calendar;

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_AUTOCOMPLETE) {

            CarmenFeature selectedCarmenFeature = PlaceAutocomplete.getPlace(data);

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

            Log.d("positionInfo", String.format("Selected location information:\\n%1$s", carmenFeature.toJson()));

            int start = carmenFeature.toJson().lastIndexOf("place_name") + 13;
            int fin = carmenFeature.toJson().lastIndexOf("place_type") - 3;

            lieu.setText(carmenFeature.toJson().substring(start, fin));
        }
    }

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
    }

    //Fonction OnClick
    View.OnFocusChangeListener openPlacePicker = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if(b) {
                lieu.clearFocus();

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

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            displayDay.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
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

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            displayTime.setText(hourOfDay + ":" + minute);
                        }
                    }, heure, minute, true);
            timePickerDialog.show();
        }
    };

}
