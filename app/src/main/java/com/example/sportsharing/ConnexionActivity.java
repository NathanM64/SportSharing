package com.example.sportsharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class ConnexionActivity extends AppCompatActivity {

    //VARIABLES maquette
    TextInputEditText login, password;
    Button connect;
    TextView passwordOublie, inscription;

    //VARIABLES
    Intent demarre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_application);

        //Initialisation des Variables
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        connect = findViewById(R.id.connect);
        passwordOublie = findViewById(R.id.passwordOublie);
        inscription = findViewById(R.id.inscription);

        //Attribution OnClick à variable
        connect.setOnClickListener(connexionAppli);
        inscription.setOnClickListener(creationCompte);
    }

    //Fonctions onClick
    View.OnClickListener connexionAppli = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Récupération info login et password
            System.out.println(login.getText());
            System.out.println(password.getText());

            //Ouverture maquette Accueil
            ///////////////////////////A conditionner selon BD
            demarre = new Intent(getApplicationContext(), AccueilActivity.class);
            startActivity(demarre);
        }
    };

    View.OnClickListener creationCompte = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Ouverture maquette inscription
            demarre = new Intent(getApplicationContext(), Inscription1Activity.class);
            startActivity(demarre);
        }
    };
}
