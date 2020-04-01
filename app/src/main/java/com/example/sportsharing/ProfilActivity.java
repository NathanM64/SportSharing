package com.example.sportsharing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sportsharing.Classe.DossierVariableClasse;
import com.example.sportsharing.Classe.Sport;
import com.example.sportsharing.Utils.BottomNavigationViewListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;

public class ProfilActivity extends AppCompatActivity {

    //VARIABLES maquette
    private BottomNavigationView navBar;
    private TextView pseudoUser, textDateProfil, textVilleProfil, textDescProfil;
    private LinearLayout listeSport;
    private ImageView parameter;

    //VARIABLES autres
    Intent demarre;
    DossierVariableClasse global;
    private String typeProfil;
    private static final int ITEM_NAV_BAR_SELECTED = R.id.navBarUser;
    private Context contextActivity = ProfilActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);

        //Get extras
        typeProfil = this.getIntent().getStringExtra("type");

        //Init variables maquettes
        navBar = findViewById(R.id.bottomNavigationView);
        pseudoUser = findViewById(R.id.pseudoUser);
        textDateProfil = findViewById(R.id.textDateProfil);
        textVilleProfil = findViewById(R.id.textVilleProfil);
        textDescProfil = findViewById(R.id.textDescProfil);
        listeSport = findViewById(R.id.listeSport);
        parameter = findViewById(R.id.parameter);

        //Init variable autres
        global = DossierVariableClasse.getInstance();

        //Changer icone navBar selectionnee
        navBar.getMenu().findItem(ITEM_NAV_BAR_SELECTED).setChecked(true);

        //Init bottom navigation bar
        BottomNavigationViewListener.typeNavigation(contextActivity, navBar);

        //Attribution
        parameter.setOnClickListener(setParameters);

        //Init profil
        initProfil();
    }

    private void initProfil() {
        if(typeProfil.equals("user")) {
            //Nom
            pseudoUser.setText(global.utilisateur.getNom() + " " + global.utilisateur.getPrenom());

            //Age
            SimpleDateFormat currentTime = new SimpleDateFormat("dd/MM/yyyy"); // jour (0 à 2), mois (3 à 5), annee (à partir de 6)
            textDateProfil.setText(calculAge(currentTime.format(new Date()), global.utilisateur.getDateNaissance()) + " ans");

            //Ville
            textVilleProfil.setText(global.utilisateur.getCodePostal()+" "+global.utilisateur.getVille());

            //Description
            textDescProfil.setText(global.utilisateur.getDescription());

            LayoutInflater inflate = getLayoutInflater();

            //Element de la map
            Set keys = global.utilisateur.mesSports.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                //Récupération du sport
                Sport key = (Sport) it.next();

                //Création du linearLayout selon le model
                LinearLayout sport = (LinearLayout) inflate.inflate(R.layout.model_linearlayout_profil, null);

                //Init du linearLayout puis ajout
                ((TextView) sport.getChildAt(1)).setText(key.getLibelle()+"\n"+global.utilisateur.mesSports.get(key));
                listeSport.addView(sport);
            }
        }
    }

    private String calculAge(String format, String dateNaissance) {

        int age;

        age = Integer.valueOf(format.substring(6)) - Integer.valueOf(dateNaissance.substring(6));

        //Si le mois actuel est inférieur au mois d'anniversaire
        if(Integer.valueOf(format.substring(3,5)) < Integer.valueOf(dateNaissance.substring(3,5))) {
            age--;
        }
        //Si le mois actuel est égal au mois d'anniversaire
        else if (Integer.valueOf(format.substring(3,5)) == Integer.valueOf(dateNaissance.substring(3,5))) {
            //Si le jour actuel est inférieur au mois d'anniversaire
            if(Integer.valueOf(format.substring(0,2)) < Integer.valueOf(dateNaissance.substring(0,2))) {
                age--;
            }
        }

        return String.valueOf(age);
    }

    //Listeners
    View.OnClickListener setParameters = view -> {
        demarre = new Intent(getApplicationContext(), ProfilParametreActivity.class);
        startActivity(demarre);
    };
}
