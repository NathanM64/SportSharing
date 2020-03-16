package com.example.sportsharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sportsharing.Classe.DossierVariableClasse;
import com.example.sportsharing.Classe.Sportif;
import com.example.sportsharing.ClasseDAO.SportifDAO;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ConnexionActivity extends AppCompatActivity {

    //VARIABLES maquette
    TextInputEditText login, password;
    Button connect;
    TextView passwordOublie, inscription;

    //VARIABLES
    Intent demarre;
    DossierVariableClasse global;
    SportifDAO sportifDAO;
    ArrayList<Sportif> utilisateurs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_application);

        //Instance de DossierVariableClasse
        global = DossierVariableClasse.getInstance();

        //Instance sportifDAO
        sportifDAO = new SportifDAO(this);
        utilisateurs = sportifDAO.getAllSportifs();

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
            String loginText = login.getText().toString();
            String motDePasseText = password.getText().toString();

            //Ouverture maquette Accueil
            ///////////////////////////A conditionner selon BD
            demarre = new Intent(getApplicationContext(), AccueilActivity.class);
            startActivity(demarre);
            finish();
        }
    };

    View.OnClickListener creationCompte = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Création du nouveau profil
            global.utilisateur = new Sportif();

            //Ouverture maquette inscription
            demarre = new Intent(getApplicationContext(), Inscription1Activity.class);
            startActivity(demarre);
            finish();
        }
    };
}
