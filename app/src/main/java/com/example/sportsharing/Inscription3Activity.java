package com.example.sportsharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Inscription3Activity extends AppCompatActivity {

    //VARIABLES maquette
    EditText presentation;
    TextView nbrCaracterePresentation;
    Button previous, confirm;

    //VARIABLES
    Intent demarre;
    int caractereSaisie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_3);

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
    }


    //Fonction OnClick
    View.OnClickListener retourInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Récupérer les infos de l'inscription 1 et 2 et les passer à l'ancienne maquette

            //Retour maquette Inscription 2
            demarre = new Intent(getApplicationContext(), Inscription2Activity.class);
            startActivity(demarre);
        }
    };

    View.OnClickListener confirmInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Sauvegarder toutes les informations dans la bd


            //Chargement maquette Connection
            demarre = new Intent(getApplicationContext(), ConnexionActivity.class);
            startActivity(demarre);
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
