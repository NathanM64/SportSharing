package com.example.sportsharing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class RechercheActiviteActivity extends AppCompatActivity {

    //VARIABLES maquette
    private BottomNavigationView navBar;
    private Spinner spinnerSport, spinnerLevel;
    private AutoCompleteTextView autoCETlieu;
    private TextView displayDay, displayTime;
    private ImageView imageDay, imageTime;
    private Button buttonCancel, buttonConfirm;
    private TableLayout tableSuggestion;

    //VARIABLES Date et Time
    private int jour, mois, annee, heure, minute;


    //VARIABLES autres
    Intent demarre;
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
        autoCETlieu = findViewById(R.id.autoCETlieu);
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
    }

    //Fonction OnClick
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
