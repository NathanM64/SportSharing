package com.example.sportsharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sportsharing.Classe.DossierVariableClasse;
import com.example.sportsharing.ClasseDAO.SportifDAO;
import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilParametreActivity extends AppCompatActivity {

    //VARIABLES Maquette
    private BottomNavigationView navBar;
    private FloatingActionButton back;
    private TextView pseudoUser, deconnexion;

    //VARIABLES Autres
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_parametre);

        //Initialisation des variables de la maquette
        back = findViewById(R.id.back);
        pseudoUser = findViewById(R.id.pseudoUser);
        deconnexion = findViewById(R.id.deconnexion);
        navBar = findViewById(R.id.bottomNavigationView);

        //Init pseudo
        pseudoUser.setText(DossierVariableClasse.getInstance().utilisateur.getNom() +" "+ DossierVariableClasse.getInstance().utilisateur.getPrenom());

        //Changer icone navBar selectionnee
        navBar.getMenu().findItem(ITEM_NAV_BAR_SELECTED).setChecked(true);

        //Init bottom navigation bar
        BottomNavigationViewListener.typeNavigation(this, navBar);

        //Application Listeners à element
        back.setOnClickListener(returnProfil);
        deconnexion.setOnClickListener(deconnexionProfil);
    }

    //Listeners
    View.OnClickListener returnProfil = view -> finish();
    View.OnClickListener deconnexionProfil = view -> {
        SportifDAO sportifDAO = new SportifDAO(ProfilParametreActivity.this);

        //Deconnecte le profil à l'attribut resteConnecte
        sportifDAO.enleveResteConnectASportifAvecResteConnecte(DossierVariableClasse.getInstance().utilisateur.getLogin());

        Intent demarre = new Intent(getApplicationContext(), ConnexionActivity.class);
        startActivity(demarre);
        finish();
    };
}
