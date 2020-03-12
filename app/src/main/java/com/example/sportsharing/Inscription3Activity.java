package com.example.sportsharing;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sportsharing.Classe.DossierVariableClasse;

import androidx.appcompat.app.AppCompatActivity;

public class Inscription3Activity extends AppCompatActivity {

    //VARIABLES maquette
    EditText presentation;
    TextView nbrCaracterePresentation;
    Button previous, confirm;

    //VARIABLES
    Intent demarre;
    private DossierVariableClasse global;
    int caractereSaisie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_3);
        
        //Instance de DossierVariableClasse
        global = DossierVariableClasse.getInstance();

        //Initialisation des variables
        presentation = findViewById(R.id.editTextPresentation);
        nbrCaracterePresentation = findViewById(R.id.textNbrCaractereTextSePresenter);
        previous = findViewById(R.id.buttonPrevious);
        confirm = findViewById(R.id.buttonConfirm);

        //Mise en place d'un TextChangedListener sur presentation pour savoir dés que l'utilisateur entre un caractère ou le supprime
        presentation.addTextChangedListener(watcherPresentation);

        //Attribution OnClick à variables
        previous.setOnClickListener(retourInscription);
        confirm.setOnClickListener(confirmInscription);

        //InitProfil
        InitProfil();
    }

    private void InitProfil() {
        presentation.setText(global.utilisateur.getDescription());
    }

    //Fonction OnClick
    View.OnClickListener retourInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Retour maquette Inscription 2
            demarre = new Intent(getApplicationContext(), Inscription2Activity.class);
            startActivity(demarre);
            finish();
        }
    };

    View.OnClickListener confirmInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Sauvegarder toutes les informations dans la bd
            global.utilisateur.setDescription(presentation.getText().toString());

            //Chargement maquette Connection
            demarre = new Intent(getApplicationContext(), AccueilActivity.class);
            startActivity(demarre);
            finish();
        }
    };

    //Fonction TextWatcher
    TextWatcher watcherPresentation = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            /*
                i1 : nombre de caractères avant modification
                i2 : nombre de caractères après modification
             */

            if(caractereSaisie <= 400 && caractereSaisie >= 0)
            {
                //Si des caractères ont été saisis
                if(i1 < i2)
                {
                    caractereSaisie += i2-i1;
                }
                //Si des caractères ont été supprimés
                else
                {
                    caractereSaisie -= i1-i2;
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String message = caractereSaisie +"/400";
            nbrCaracterePresentation.setText(message);
        }
    };
}
