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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sportsharing.Classe.Activite;
import com.example.sportsharing.Classe.Adresse;
import com.example.sportsharing.Classe.Ressource;
import com.example.sportsharing.Classe.EnumUtil;
import com.example.sportsharing.Classe.Sport;
import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;
import com.mapbox.mapboxsdk.plugins.places.picker.PlacePicker;
import com.mapbox.mapboxsdk.plugins.places.picker.model.PlacePickerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreerActiviteActivity extends AppCompatActivity {

    //VARIABLES maquette
    private BottomNavigationView navBar;
    private Spinner spinnerSport, spinnerLevel;
    private EditText lieu, editTextDescription;
    private TextView displayDay, displayTimeBegin, displayTimeEnd, nbrPlayer;
    private ImageView imageDay, imageTimeBegin, imageTimeEnd;
    private FloatingActionButton addPlayer, supPlayer;
    private Button cancel, confirm;

    //VARIABLES autres
    Intent demarre;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarActivity;
    private Context contextActivity = CreerActiviteActivity.this;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private static final int REQUEST_CODE_PLACEPICKER = 2;
    private Ressource global;

    public int nbPlayer;
    int annee, mois, jour, heure, minute;
    boolean isBegin;

    //VARIABLES lieu
    int numero, codePostal;
    String nom, ville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_activite);

        //Récupération de DossierGlobalClasse
        global = Ressource.getInstance();

        //Initialisation des variables
        navBar = findViewById(R.id.bottomNavigationView);
        spinnerSport = findViewById(R.id.spinnerSport);
        spinnerLevel = findViewById(R.id.spinnerLevel);
        lieu = findViewById(R.id.lieu);
        editTextDescription = findViewById(R.id.editTextDescription);
        displayDay = findViewById(R.id.displayDay);
        displayTimeBegin = findViewById(R.id.displayTimeBegin);
        displayTimeEnd = findViewById(R.id.displayTimeEnd);
        nbrPlayer = findViewById(R.id.nbrPlayer);
        imageDay = findViewById(R.id.imageDay);
        imageTimeBegin = findViewById(R.id.imageTimeBegin);
        imageTimeEnd = findViewById(R.id.imageTimeEnd);
        addPlayer = findViewById(R.id.addPlayer);
        supPlayer = findViewById(R.id.supPlayer);
        cancel = findViewById(R.id.buttonCancel);
        confirm = findViewById(R.id.buttonConfirm);

        //Changer icone navBar selectionnee
        navBar.getMenu().findItem(ITEM_NAV_BAR_SELECTED).setChecked(true);

        //Liaison clic boutton avec l'action
        lieu.setOnFocusChangeListener(openPlacePicker);
        imageDay.setOnClickListener(openCalendar);
        imageTimeBegin.setOnClickListener(openTimer);
        imageTimeEnd.setOnClickListener(openTimer);
        addPlayer.setOnClickListener(modifPlayer);
        supPlayer.setOnClickListener(modifPlayer);
        cancel.setOnClickListener(cancelListener);
        confirm.setOnClickListener(confirmListener);
        BottomNavigationViewListener.typeNavigation(contextActivity, navBar);

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

            //Récupération de l'adresse
            try {
                JSONObject object = new JSONObject(carmenFeature.toJson());
                nom = object.getString("text");
                numero = Integer.parseInt(object.getString("address"));
                codePostal = Integer.parseInt(object.getJSONArray("context").getJSONObject(0).getString("text"));
                ville = object.getJSONArray("context").getJSONObject(1).getString("text");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            lieu.setText(numero + " " + nom + ", "+codePostal+" " +ville);
        }
    }

    //Méthodes onClick
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
                        .build(CreerActiviteActivity.this);
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
            DatePickerDialog datePickerDialog = new DatePickerDialog(CreerActiviteActivity.this,
                    (view1, year, monthOfYear, dayOfMonth) -> {

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

                        if(!error) {
                            displayDay.setText((dayOfMonth<10?"0":"") + dayOfMonth + "/" + (monthOfYear+1<10?"0":"") + (monthOfYear + 1) + "/" + year);
                            annee = year;
                            mois = monthOfYear+1;
                            jour = dayOfMonth;
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

            isBegin = false;
            if(view.getId() == R.id.imageTimeBegin  )
                    isBegin = true;

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(CreerActiviteActivity.this,
                    (view1, hourOfDay, minuteOfHour) -> {
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
                        String messageError = "";
                        //Si l'année est inférieur à l'année courante (Erreur)
                        if(annee < yearCurrent) {
                            error = true;
                            messageError = "L'année choisi doit être supérieure ou égale à l'année courante";
                        } else {

                            //Si le mois est inférieur au mois courant (Erreur)
                            if(mois < monthOfYearCurrent){
                                error = true;
                                messageError = "Le mois choisi doit être supérieur ou égale au mois courant";
                            } else {

                                //Si le jour est inférieur au jour courant (Erreur)
                                if(jour < dayOfMonthCurrent) {
                                    error = true;
                                    messageError = "Le jour choisi doit être supérieur ou égale au jour courant";
                                }
                                else {

                                    //Si le jour est égal au jour courant
                                    if(jour == dayOfMonthCurrent) {

                                        //Si l'heure est inférieur à l'heure courante (Erreur)
                                        if(hourOfDay < hourCurrent) {
                                            error = true;
                                            messageError = "Pour ce jour, l'heure choisi doit être supérieure ou égale à l'heure courante";
                                        }
                                        //Ou si les minutes sont inférieur aux minutes courantes (Erreur)
                                        if(minuteOfHour < minuteCurrent && hourOfDay == hourCurrent) {
                                            error = true;
                                            messageError = "Pour ce jour, les minutes choisi doivent être supérieures ou égale aux minutes courantes";
                                        }
                                    }
                                }
                            }
                        }

                        if(!error) {
                            //Vérification de la cohérence entre l'heure de fin et l'heure de début seulement si pas d'erreur
                            String time;
                            switch (view.getId()) {
                                case R.id.imageTimeBegin:
                                    time = displayTimeEnd.getText().toString();
                                    if(!time.equals("")) {
                                        int hFin = Integer.parseInt(time.split(":")[0]);
                                        int mFin = Integer.parseInt(time.split(":")[1]);
                                        if(hFin < hourOfDay) {
                                            error = true;
                                            messageError = "L'heure de début ne peut être supérieur à l'heure de fin";
                                        }
                                        if(mFin < minuteOfHour && hFin == hourOfDay) {
                                            error = true;
                                            messageError = "Les minutes de début pour cette heure ne peuvent être supérieures aux minutes de l'heure de fin";
                                        }
                                    }
                                    break;

                                case R.id.imageTimeEnd:
                                    time = displayTimeBegin.getText().toString();
                                    if(!time.equals("")) {
                                        int hDebut = Integer.parseInt(time.split(":")[0]);
                                        int mDebut = Integer.parseInt(time.split(":")[1]);
                                        if(hDebut > hourOfDay) {
                                            error = true;
                                            messageError = "L'heure de fin ne peut être inférieur à l'heure de début";
                                        }
                                        if(mDebut > minuteOfHour && hDebut == hourOfDay) {
                                            error = true;
                                            messageError = "Les minutes de fin pour cette heure ne peuvent être inférieures aux minutes de l'heure de début";
                                        }
                                    }
                                    break;
                            }
                        }

                        if(!error) {
                            if(isBegin) {
                                displayTimeBegin.setText(hourOfDay + ":" + (minuteOfHour<10?"0":"") + minuteOfHour);
                            } else {
                                displayTimeEnd.setText(hourOfDay + ":" + (minuteOfHour<10?"0":"") + minuteOfHour);
                            }
                        } else {
                            Toast.makeText(contextActivity, messageError, Toast.LENGTH_LONG).show();
                            if(isBegin) {
                                displayTimeBegin.setText("");
                            } else {
                                displayTimeEnd.setText("");
                            }
                        }

                    }, heure, minute, true);
            timePickerDialog.show();
        }
    };
    View.OnClickListener modifPlayer = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.addPlayer:
                    nbPlayer++;
                    break;

                case R.id.supPlayer:
                    if(nbPlayer > 0) {
                        nbPlayer--;
                    }
                    break;
            }

            nbrPlayer.setText(nbPlayer+"");
        }
    };
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

            if(!lieu.getText().toString().equals("") &&
                    !displayDay.getText().toString().equals("") &&
                    !displayTimeBegin.getText().toString().equals("") &&
                    !displayTimeEnd.getText().toString().equals("") && nbPlayer != 0) {

                global.activiteCurrent = new Activite(-1,
                        displayDay.getText().toString(),
                        displayTimeBegin.getText().toString(),
                        displayTimeEnd.getText().toString(),
                        editTextDescription.getText().toString(),
                        nbPlayer,
                        EnumUtil.NiveauSport.valueOf(((String) spinnerLevel.getSelectedItem()).replace(" ","_")),
                        global.createur,
                        new Adresse(numero, nom, codePostal, ville),
                        new Sport(EnumUtil.NameSport.valueOf((String) spinnerSport.getSelectedItem())));

                demarre = new Intent(getApplicationContext(), AffichageActivitePostCreationActivity.class);
                startActivity(demarre);
            } else {
                Toast.makeText(CreerActiviteActivity.this, "Vous n'avez pas rempli tous les champs", Toast.LENGTH_LONG).show();
            }
        }
    };
}
