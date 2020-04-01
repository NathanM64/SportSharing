package com.example.sportsharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sportsharing.Classe.DossierVariableClasse;
import com.example.sportsharing.ClasseDAO.SportifDAO;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilParametreActivity extends AppCompatActivity {

    //VARIABLES Maquette
    private TextView deconnexion;

    //VARIABLES Autres



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_parametre);

        //Initialisation des variables de la maquette
        deconnexion = findViewById(R.id.deconnexion);

        //Application Listeners à element
        deconnexion.setOnClickListener(deconnexionProfil);
    }

    View.OnClickListener deconnexionProfil = view -> {
        SportifDAO sportifDAO = new SportifDAO(ProfilParametreActivity.this);

        //Deconnecte le profil à l'attribut resteConnecte
        sportifDAO.enleveResteConnectASportifAvecResteConnecte(DossierVariableClasse.getInstance().utilisateur.getLogin());

        Intent demarre = new Intent(getApplicationContext(), ConnexionActivity.class);
        startActivity(demarre);
        finish();
    };
}
