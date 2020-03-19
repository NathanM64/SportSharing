package com.example.sportsharing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.sportsharing.Classe.DossierVariableClasse;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;

public class Inscription1Activity extends AppCompatActivity {

    //VARIABLES maquette
    private TextInputEditText login, password, confirmPassword, name, firstName, mail, birthday, city, postalCode, phoneNumber;
    private Switch condition, notification;
    private Button cancel, next;

    //VARIABLES
    private Intent demarre;
    private DossierVariableClasse global;

    private String MESSAGE_ERROR_EMPTY_FIELD;
    private String MESSAGE_ERROR_NO_SPACE;
    private String MESSAGE_ERROR_MAIL;
    private String MESSAGE_ERROR_BIRTHDAY;
    private String MESSAGE_ERROR_BIRTHDAY_MODEL;
    private String MESSAGE_ERROR_WITH_NUMBER;
    private String MESSAGE_ERROR_POSTAL_CODE;
    private String MESSAGE_ERROR_PHONE_NUMBER;
    private String MESSAGE_ERROR_SIMILAR_PASSWORD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_1);

        //Initialisation des messages d'erreurs
        MESSAGE_ERROR_EMPTY_FIELD = getString(R.string.error_empty_field);
        MESSAGE_ERROR_NO_SPACE = getString(R.string.error_no_space);
        MESSAGE_ERROR_MAIL = getString(R.string.error_mail);
        MESSAGE_ERROR_BIRTHDAY = getString(R.string.error_birthday);
        MESSAGE_ERROR_BIRTHDAY_MODEL = getString(R.string.error_birthday_model);
        MESSAGE_ERROR_WITH_NUMBER = getString(R.string.error_with_number);
        MESSAGE_ERROR_POSTAL_CODE = getString(R.string.error_postal_code);
        MESSAGE_ERROR_PHONE_NUMBER = getString(R.string.error_phone_number);
        MESSAGE_ERROR_SIMILAR_PASSWORD = getString(R.string.error_similar_password);

        //Instance de DossierVariableClasse
        global = DossierVariableClasse.getInstance();

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

        //Init champs de text
        InitProfil();
    }

    private void InitProfil() {
        login.setText(global.utilisateur.getLogin());
        password.setText(global.utilisateur.getMotDePasse());
        confirmPassword.setText(global.utilisateur.getConfirmMotDePasse());
        name.setText(global.utilisateur.getNom());
        firstName.setText(global.utilisateur.getPrenom());
        mail.setText(global.utilisateur.getAdresseMail());
        birthday.setText(global.utilisateur.getDateNaissance());
        city.setText(global.utilisateur.getVille());
        phoneNumber.setText(global.utilisateur.getNumeroTelephone());

        if(global.utilisateur.getCodePostal() != 0 && String.valueOf(global.utilisateur.getCodePostal()).length() == 5)
            postalCode.setText(String.valueOf(global.utilisateur.getCodePostal()));
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
            TextInputEditText[] tabChampQuiNeDoiventPasAvoirDespace = {login, password, confirmPassword, name, firstName, mail, birthday, postalCode, phoneNumber};
            TextInputEditText[] tabChampQuiNePossedePasDeChiffre = {name, firstName, city};

            //Traitement des champs qui doivent obligatoirement être renseignés
            for (TextInputEditText inputEditText : tabAVerifier) {
                if (!champTraiter.contains(inputEditText)) {
                    isAProblem = afficheErreur(inputEditText, isCompleted(inputEditText), MESSAGE_ERROR_EMPTY_FIELD);
                    if (isAProblem) {
                        champTraiter.add(inputEditText);
                    }
                }
            }

            //Traitement des champs qui ne doivent pas contenir d'espace et supression de ceci
            for (TextInputEditText textInputEditText : tabChampQuiNeDoiventPasAvoirDespace) {
                if (!champTraiter.contains(textInputEditText)) {
                    isAProblem = afficheErreur(textInputEditText, replaceSpaces(textInputEditText), MESSAGE_ERROR_NO_SPACE);
                    if (isAProblem) {
                        champTraiter.add(textInputEditText);
                    }
                }
            }

            //Traitement de l'email
            if(!champTraiter.contains(mail)) {
                isAProblem = afficheErreur( mail , emailPatternIsCorect(mail) , MESSAGE_ERROR_MAIL );
                if(isAProblem) {
                    champTraiter.add(mail);
                }
            }

            //Traitement de l'email
            if(!champTraiter.contains(birthday)) {
                isAProblem = afficheErreur( birthday , birthdayPatternIsCorect(birthday) , MESSAGE_ERROR_BIRTHDAY+"\n"+MESSAGE_ERROR_BIRTHDAY_MODEL );
                if(isAProblem) {
                    champTraiter.add(birthday);
                }
            }

            //Traitement des champs qui ne doivent pas contenir de chiffres
            for (TextInputEditText textInputEditText : tabChampQuiNePossedePasDeChiffre) {
                if (!champTraiter.contains(textInputEditText)) {
                    isAProblem = afficheErreur(textInputEditText, containNumber(textInputEditText), MESSAGE_ERROR_WITH_NUMBER);
                    if (isAProblem) {
                        champTraiter.add(textInputEditText);
                    }
                }
            }

            //Traitement du code postal si non vide
            if(!champTraiter.contains(postalCode) && postalCode.getText().toString().length() != 0) {
                isAProblem = afficheErreur( postalCode , postalCodePatternIsCorrect(postalCode) , MESSAGE_ERROR_POSTAL_CODE );
                if(isAProblem) {
                    champTraiter.add(postalCode);
                }
            }

            //Traitement du numéro de tel si non vide
            if(!champTraiter.contains(phoneNumber) && phoneNumber.getText().toString().length() != 0) {
                isAProblem = afficheErreur( phoneNumber , phoneNumberPatternIsCorrect(phoneNumber) , MESSAGE_ERROR_PHONE_NUMBER );
                if(isAProblem) {
                    champTraiter.add(phoneNumber);
                }
            }

            //Traitement de l'égalité entre motDePasse et confirmMotDePasse
            if(!champTraiter.contains(password) && !champTraiter.contains(confirmPassword)) {
                isAProblem = afficheErreur( confirmPassword , !password.getText().toString().equals(confirmPassword.getText().toString()) , MESSAGE_ERROR_SIMILAR_PASSWORD );
                if(isAProblem) {
                    champTraiter.add(confirmPassword);
                }
            }

            //Chargement maquette Inscription 2 si aucun problème n'a été rencontré
            if(champTraiter.size() == 0) {

                //Enregistrements des informations dans le profil de DossierVariableClasse
                global.utilisateur.setLogin(login.getText().toString());
                global.utilisateur.setMotDePasse(password.getText().toString());
                global.utilisateur.setConfirmMotDePasse(confirmPassword.getText().toString());
                global.utilisateur.setNom(name.getText().toString());
                global.utilisateur.setPrenom(firstName.getText().toString());
                global.utilisateur.setAdresseMail(mail.getText().toString());
                global.utilisateur.setDateNaissance(birthday.getText().toString());
                global.utilisateur.setVille(city.getText().toString());
                if(!postalCode.getText().toString().equals(""))
                    global.utilisateur.setCodePostal(Integer.valueOf(postalCode.getText().toString()));
                global.utilisateur.setNumeroTelephone(phoneNumber.getText().toString());

                //Start inscription 2
                demarre = new Intent(getApplicationContext(), Inscription2Activity.class);
                startActivity(demarre);
                finish();
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
