package com.example.sportsharing;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Inscription2Activity extends AppCompatActivity {

    //VARIABLES maquette
    Spinner sport, level;
    FloatingActionButton add, sup;
    TableLayout listeSport;
    Button previous, next;

    //VARIABLES Spinner
    ArrayAdapter<CharSequence> adapterSport;
    ArrayAdapter<CharSequence> adapterLevel;
    String nameSport;
    String nameLevel;

    //VARIABLES
    Intent demarre;
    String sportASupprimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_2);

        //Initialisation des variables
        sport = findViewById(R.id.spinnerSport);
        level = findViewById(R.id.spinnerLevel);
        add = findViewById(R.id.addSport);
        sup = findViewById(R.id.supSport);
        listeSport = findViewById(R.id.TableLayoutSport);
        previous = findViewById(R.id.buttonPrevious);
        next = findViewById(R.id.buttonNext);

        //Création adapterSport
        adapterSport = ArrayAdapter.createFromResource(this, R.array.sport_array, android.R.layout.simple_spinner_item);
        adapterSport.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Création adapterLevel
        adapterLevel = ArrayAdapter.createFromResource(this, R.array.level_array, android.R.layout.simple_spinner_item);
        adapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Attribution adapter à spinner
        sport.setAdapter(adapterSport);
        level.setAdapter(adapterLevel);


        //Attribution OnItemSelectedListener à spinner
        sport.setOnItemSelectedListener(itemSport);
        level.setOnItemSelectedListener(itemLevel);

        //Attribution OnClick à variables
        add.setOnClickListener(addSport);
        sup.setOnClickListener(supSport);
        previous.setOnClickListener(retourInscription);
        next.setOnClickListener(suiteInscription);
    }

    //Fonction OnClick
    View.OnClickListener addSport = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            /////////Faire vérification sport déjà présent/////////////

            //Création d'une nouvelle ligne selon le model
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint("InflateParams") TableRow row = (TableRow) inflater.inflate(R.layout.model_tablerow_inscription_2, null);

            //Ecriture des éléments dans la nouvelle ligne
            ScrollView sv = (ScrollView) row.getChildAt(0);
            LinearLayout ll = (LinearLayout) sv.getChildAt(0);
            LinearLayout l2 = (LinearLayout) ll.getChildAt(0);
            TextView tvNameSport = (TextView) l2.getChildAt(0);
            TextView tvNameLevel = (TextView) l2.getChildAt(1);

            //Changement du texte
            tvNameSport.setText(nameSport);
            tvNameLevel.setText(nameLevel);

            //Ajout de la méthode focus
            l2.setOnClickListener(clickText);
            //tvNameSport.setOnClickListener(clickText);
            //tvNameLevel.setOnClickListener(clickText);

            //Ajout de la ligne
            listeSport.addView(row);

        }
    };

    View.OnClickListener supSport = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean textTrouve = false;
            int indexTableRow = 0;
            for (int i = 2; i < listeSport.getChildCount(); i++)
            {
                //récupération du textView à l'index i en passant par son aborescence
                    //Récupération de l'aborescence
                LinearLayout arborescence = (LinearLayout) ((LinearLayout) ((ScrollView) ((TableRow) listeSport.getChildAt(i)).getChildAt(0)).getChildAt(0)).getChildAt(0);
                    //Récupération des textView selon l'arborescence
                TextView textNameSport = (TextView) arborescence.getChildAt(0);

                //Si le text de textAnalysee est équal à celui à supprimer
                if(((textNameSport.getText().toString()).equals(sportASupprimer)
                && !textTrouve))
                {
                    //Sauvegarde de l'index trouvé
                    indexTableRow = i;
                    textTrouve = true;
                }
            }

            //Supprimer la ligne
            if(textTrouve)
            {
                listeSport.removeViewAt(indexTableRow);
            }
        }
    };

    View.OnClickListener retourInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Récupérer les infos de l'inscription 1 et les passer à l'ancienne maquette

            //Retour maquette Inscription 1
            demarre = new Intent(getApplicationContext(), Inscription1Activity.class);
            startActivity(demarre);
        }
    };

    View.OnClickListener suiteInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Récupérer toutes les infos
            //Vérifier la bonne saisie des informations
            // Passer les anciennes et les nouvelles infos à la prochaine maquette

            //Chargement maquette Incription 3
            demarre = new Intent(getApplicationContext(), Inscription3Activity.class);
            startActivity(demarre);
        }
    };

    //pour savoir quelle ligne a été sélectionné pour la supprimer
    View.OnClickListener clickText = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 2; i < listeSport.getChildCount(); i++)
            {
                //récupération du textView à l'index i en passant par son aborescence
                    //Récupération de l'aborescence
                LinearLayout arborescence = (LinearLayout) ((LinearLayout) ((ScrollView) ((TableRow) listeSport.getChildAt(i)).getChildAt(0)).getChildAt(0)).getChildAt(0);
                    //Récupération des textView selon l'arborescence
                TextView textNameSport = (TextView) arborescence.getChildAt(0);
                TextView textNameLevel = (TextView) arborescence.getChildAt(1);

                //Récupération des textView de l'objet clické
                TextView textNameSportClick = (TextView) ((LinearLayout) view).getChildAt(0);
                TextView textNameLevelClick = (TextView) ((LinearLayout) view).getChildAt(1);

                //Si le text de textAnalysee est équal à celui sélectionné
                if((textNameSport.getText()).equals(textNameSportClick.getText())
                && (textNameLevel.getText()).equals(textNameLevelClick.getText()))
                {
                    //Sauvegarde du sport sélectionné
                    sportASupprimer = textNameSport.getText().toString();

                    //Met le background à rouge transparent
                    textNameSport.setBackgroundColor(Color.parseColor("#68FF0000"));
                    textNameLevel.setBackgroundColor(Color.parseColor("#68FF0000"));
                }
                else
                {
                    //Met le background à blanc
                    textNameSport.setBackgroundColor(Color.WHITE);
                    textNameLevel.setBackgroundColor(Color.WHITE);
                }
            }
        }
    };


    //Fonction OnItemSelectedListener
    AdapterView.OnItemSelectedListener itemSport = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            nameSport = (String) adapterView.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            nameSport = (String) adapterView.getItemAtPosition(0);
        }
    };
    AdapterView.OnItemSelectedListener itemLevel = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            nameLevel = (String) adapterView.getItemAtPosition(i);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            nameLevel = (String) adapterView.getItemAtPosition(0);
        }
    };
}
