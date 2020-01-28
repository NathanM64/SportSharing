package com.example.sportsharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;

public class Inscription1Activity extends AppCompatActivity {

    //VARIABLES maquette
    TextInputEditText login, password, confirmPassword, name, firstName, mail, birthday, city, postalCode, phoneNumber;
    Switch condition, notification;
    Button cancel, next;

    //VARIABLES
    Intent demarre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_1);

        //Initialisation des variables
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        name = findViewById(R.id.name);
        firstName = findViewById(R.id.firstName);
        mail = findViewById(R.id.mail);
        birthday = findViewById(R.id.birthday);
        city = findViewById(R.id.city);
        postalCode = findViewById(R.id.postalCode);
        phoneNumber = findViewById(R.id.phoneNumber);

        condition = findViewById(R.id.switchCondition);
        notification = findViewById(R.id.switchNotification);

        cancel = findViewById(R.id.buttonCancel);
        next = findViewById(R.id.buttonNext);

        //Attribution onClick à variables
        cancel.setOnClickListener(annulerInscription);
        next.setOnClickListener(suiteInscription);
    }

    //Fonction OnClick
    View.OnClickListener annulerInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Retour maquette Connexion
            demarre = new Intent(getApplicationContext(), ConnexionActivity.class);
            startActivity(demarre);
        }
    };

    View.OnClickListener suiteInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Récupérer toutes les infos
            //Vérifier la bonne saisie des informations
            // Passer les anciennes et les nouvelles infos à la prochaine maquette


            //Chargement maquette Inscription 2
            demarre = new Intent(getApplicationContext(), Inscription2Activity.class);
            startActivity(demarre);
        }
    };
}
