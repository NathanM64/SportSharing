package com.example.sportsharing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            boolean isAProblem;//Passe a true si un problème est survenu

            ArrayList<TextInputEditText> champTraiter = new ArrayList<>(); //Si vide à la fin des tests alors tous les champs ont validé les tests
            TextInputEditText[] tabAVerifier = {login, password, confirmPassword, name, firstName, mail, birthday};//Récupérer toutes les infos dans un tableau
            TextInputEditText[] tabChampQuiNeDoiventPasAvoirDespace = {login, password, confirmPassword, firstName, mail, birthday, postalCode, phoneNumber};
            TextInputEditText[] tabChampQuiNePossedePasDeChiffre = {name, firstName, city};

            //Traitement des champs qui doivent obligatoirement être renseignés
            for (TextInputEditText inputEditText : tabAVerifier) {
                if (!champTraiter.contains(inputEditText)) {
                    isAProblem = afficheErreur(inputEditText, isCompleted(inputEditText), "champ obligatoirement renseigné");
                    if (isAProblem) {
                        champTraiter.add(inputEditText);
                    }
                }
            }

            //Traitement des champs qui ne doivent pas contenir d'espace et supression de ceci
            for (TextInputEditText textInputEditText : tabChampQuiNeDoiventPasAvoirDespace) {
                if (!champTraiter.contains(textInputEditText)) {
                    isAProblem = afficheErreur(textInputEditText, replaceSpaces(textInputEditText), "ce champs ne peut pas contenir d'espace");
                    if (isAProblem) {
                        champTraiter.add(textInputEditText);
                    }
                }
            }

            //Traitement de l'email
            if(!champTraiter.contains(mail)) {
                isAProblem = afficheErreur( mail , emailPatternIsCorect(mail) , "l'adresse email n'a pas été correctement saisit" );
                if(isAProblem) {
                    champTraiter.add(mail);
                }
            }

            //Traitement de l'email
            if(!champTraiter.contains(birthday)) {
                isAProblem = afficheErreur( birthday , birthdayPatternIsCorect(birthday) , "la date de naissance n'a pas été correctement saisit.\nElle doit être de la forme (jj/mm/aaaa)" );
                if(isAProblem) {
                    champTraiter.add(birthday);
                }
            }

            //Traitement des champs qui ne doivent pas contenir de chiffres
            for (TextInputEditText textInputEditText : tabChampQuiNePossedePasDeChiffre) {
                if (!champTraiter.contains(textInputEditText)) {
                    isAProblem = afficheErreur(textInputEditText, containNumber(textInputEditText), "ce champs ne peut pas contenir de chiffre");
                    if (isAProblem) {
                        champTraiter.add(textInputEditText);
                    }
                }
            }

            //Traitement du code postal si non vide
            if(!champTraiter.contains(postalCode) && postalCode.getText().toString().length() != 0) {
                isAProblem = afficheErreur( postalCode , postalCodePatternIsCorrect(postalCode) , "le code postal n'a pas été correctement saisit" );
                if(isAProblem) {
                    champTraiter.add(postalCode);
                }
            }

            //Traitement du numéro de tel si non vide
            if(!champTraiter.contains(phoneNumber) && phoneNumber.getText().toString().length() != 0) {
                isAProblem = afficheErreur( phoneNumber , phoneNumberPatternIsCorrect(phoneNumber) , "le numéro de téléphone n'a pas été correctement saisit" );
                if(isAProblem) {
                    champTraiter.add(phoneNumber);
                }
            }

            //Chargement maquette Inscription 2 si aucun problème n'a été rencontré
            if(champTraiter.size() == 0) {

                //Save les informations dans le profil de DossierVariableClasse

                demarre = new Intent(getApplicationContext(), Inscription2Activity.class);
                startActivity(demarre);
            }
        }
    };

    public boolean isCompleted(TextInputEditText text) {
        return (text.getText().toString().length() == 0);
    }

    public boolean replaceSpaces(TextInputEditText mot){
        //On récupère la valeur du champs de mot
        String champText = mot.getText().toString();

        //Nouvelle valeur du champs après la supression des espaces
        //String nouvelleValeurChamp = champText.replaceAll("\\s*", "");
        String nouvelleValeurChamp = champText.trim();
        mot.setText(nouvelleValeurChamp);

        //Si la valeur a été modifié alors return true et affiche l'erreur

        return (!champText.equals(nouvelleValeurChamp));
    }

    public boolean containNumber(TextInputEditText text) {
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(text.getText().toString());

        return (m.find());
    }

    public boolean emailPatternIsCorect(TextInputEditText email){

        Pattern p = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,4}$");
        Matcher m = p.matcher(email.getText().toString());

        return !(m.find());

    }

    public boolean birthdayPatternIsCorect(TextInputEditText email){

        Pattern p = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}$");
        Matcher m = p.matcher(email.getText().toString());

        return !(m.find());

    }

    public boolean postalCodePatternIsCorrect(TextInputEditText postalCode) {
        Pattern p = Pattern.compile("[0-9]{5,5}$");
        Matcher m = p.matcher(postalCode.getText().toString());

        return !(m.find());
    }

    public boolean phoneNumberPatternIsCorrect(TextInputEditText postalCode) {
        Pattern p = Pattern.compile("(0|\\+33)[0-9]{9,9}$");
        Matcher m = p.matcher(postalCode.getText().toString());

        return !(m.find());
    }

    private boolean afficheErreur(View view, boolean aErreur, String message) {

        if (view == null) {
            return false;
        }

        TextInputEditText t = (TextInputEditText) view;
        if (aErreur) {
            t.setTextColor(getResources().getColor(R.color.textError));
            t.setError(message);
            return true;
        } else {
            t.setTextColor(getResources().getColor(R.color.textNormal));
            return false;
        }
    }
}
