package com.example.sportsharing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sportsharing.Classe.DossierVariableClasse;
import com.example.sportsharing.Classe.EnumUtil;
import com.example.sportsharing.Classe.Sport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;

public class Inscription2Activity extends AppCompatActivity {

    //VARIABLES maquette
    Spinner sport, level;
    FloatingActionButton add, sup;
    Button previous, next;
    LinearLayout listeSportAjoute;

    //VARIABLES pour stocker les valeurs
    ArrayAdapter<String> adapterSport;
    List<String> listeSport;
    String nameSport;
    String nameLevel;

    //VARIABLES
    Intent demarre;
    DossierVariableClasse global;
    String sportASupprimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_2);

        //Instance de DossierVariableClasse
        global = DossierVariableClasse.getInstance();

        //Initialisation des variables
        sport = findViewById(R.id.spinnerSport);
        level = findViewById(R.id.spinnerLevel);
        add = findViewById(R.id.addSport);
        sup = findViewById(R.id.supSport);
        listeSportAjoute = findViewById(R.id.listeSportAjoute);
        previous = findViewById(R.id.buttonPrevious);
        next = findViewById(R.id.buttonNext);

        //Adapter Sport
        listeSport = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.sport_array)));
        adapterSport = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listeSport);
        adapterSport.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Application adapter à spinner
        sport.setAdapter(adapterSport);

        //Attribution OnItemSelectedListener à spinner
        sport.setOnItemSelectedListener(itemSport);
        level.setOnItemSelectedListener(itemLevel);

        //Attribution OnClick à variables
        add.setOnClickListener(addSport);
        sup.setOnClickListener(supSport);
        previous.setOnClickListener(retourInscription);
        next.setOnClickListener(suiteInscription);

        //InitProfil
        InitProfil();
    }

    private void InitProfil() {
        //Init sport
        Set keys = global.utilisateur.mesSports.keySet();
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            Sport key = (Sport) it.next();

            String sport = key.libelle.toString();
            String level = global.utilisateur.mesSports.get(key).toString().replace("_", " ");

            ajoutLigne(sport, level);
        }
    }

    private void ajoutLigne(String s, String l) {
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout row = (LinearLayout) inflater.inflate(R.layout.model_tablerow_inscription_2, null);

        //Ecriture des éléments dans la nouvelle ligne
        TextView tvNameSport = (TextView) row.getChildAt(0);
        TextView tvNameLevel = (TextView) row.getChildAt(1);

        //Changement du texte
        tvNameSport.setText(s);
        tvNameLevel.setText(l);

        //Ajout de la méthode focus
        row.setOnClickListener(clickText);

        //Ajout de la ligne
        listeSportAjoute.addView(row);

        //MAJ de la liste des sports
        try {
            //Suppression de l'élément dans la liste
            listeSport.remove(s);
            adapterSport.notifyDataSetChanged();

            //Modification du sport sélectionné
            sport.setSelection(0);
            nameSport = listeSport.get(0);
        }catch (Exception e) {
            e.printStackTrace();
            Log.d("listeSport", "Erreur sup sport");
        }
    }

    //Fonction OnClick
    View.OnClickListener addSport = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Création d'une nouvelle ligne selon le model
            ajoutLigne(nameSport, nameLevel);
        }
    };

    View.OnClickListener supSport = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean textTrouve = false;
            int indexTableRow = 0;
            for (int i = 0; i < listeSportAjoute.getChildCount(); i++)
            {
                //récupération du textView à l'index i en passant par son aborescence
                    //Récupération de l'aborescence
                LinearLayout arborescence = (LinearLayout) listeSportAjoute.getChildAt(i);
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
                listeSportAjoute.removeViewAt(indexTableRow);
            }

            //Supprimer dans les sports du joueurs si déjà présent
            Sport s = null;
            for (EnumUtil.NameSport sport : EnumUtil.NameSport.values()) {
                if((sportASupprimer).equals(sport.toString())) {
                    s = new Sport(sport);
                    Log.d("listeSport", "Sport crée");
                }
            }
            if(s != null)
                global.utilisateur.suppSport(s);

            //MAJ de la liste des sports
            try {
                //Ajout de l'élément dans la liste
                listeSport.add(0, sportASupprimer);
                adapterSport.notifyDataSetChanged();

                //Modification du sport sélectionné
                sport.setSelection(0);
                nameSport = listeSport.get(0);
            }catch (Exception e) {
                e.printStackTrace();
                Log.d("listeSport", "Erreur add sport");
            }
        }
    };

    View.OnClickListener retourInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Retour maquette Inscription 1
            demarre = new Intent(getApplicationContext(), Inscription1Activity.class);
            startActivity(demarre);
            finish();
        }
    };

    View.OnClickListener suiteInscription = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Ajout des sports à l'utilisateur
            for (int i = 0; i < listeSportAjoute.getChildCount(); i++)
            {
                //Récupération de l'aborescence
                LinearLayout arborescence = (LinearLayout) listeSportAjoute.getChildAt(i);
                //Récupération des textView selon l'arborescence
                TextView textNameSport = (TextView) arborescence.getChildAt(0);
                TextView textNameLevel = (TextView) arborescence.getChildAt(1);

                Sport s = null;
                for (EnumUtil.NameSport sport : EnumUtil.NameSport.values()) {
                    if((textNameSport.getText().toString()).equals(sport.toString())) {
                        s = new Sport(sport);
                    }
                }
                for (EnumUtil.NiveauSport niveau : EnumUtil.NiveauSport.values()) {
                    if((textNameLevel.getText().toString()).equals(niveau.toString().replace("_", " "))) {
                        global.utilisateur.addSport(s, niveau);
                    }
                }
            }

            Log.d("listeSport", global.utilisateur.mesSports.toString());

            //Chargement maquette Incription 3
            demarre = new Intent(getApplicationContext(), Inscription3Activity.class);
            startActivity(demarre);
            finish();
        }
    };

    //pour savoir quelle ligne a été sélectionné pour la supprimer
    View.OnClickListener clickText = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < listeSportAjoute.getChildCount(); i++)
            {
                //récupération du textView à l'index i en passant par son aborescence
                    //Récupération de l'aborescence
                LinearLayout arborescence = (LinearLayout) listeSportAjoute.getChildAt(i);
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
                    Log.d("listeSport", "Sport a supprimer selected : " +sportASupprimer);

                    //Met le background à rouge transparent
                    arborescence.setBackgroundColor(Color.parseColor("#68FF0000")); //Rouge clair
                }
                else
                {
                    //Met le background à blanc
                    arborescence.setBackgroundColor(Color.parseColor("#00FFFFFF")); //Texture blanche transparente
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
