package com.example.assocationapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.assocationapp.MainActivity;
import com.example.assocationapp.R;
import com.example.assocationapp.daos.PersonneDao;
import com.example.assocationapp.models.Personne;

public class CreatePersonActivity extends AppCompatActivity {

    private EditText id;
    private EditText nom;
    private EditText prenom;
    private EditText email;
    private EditText password;

    private Personne personne;

    private PersonneDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = ((MainActivity)getApplication()).getPersonneDao();

        personne = new Personne();

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    protected void addPerson(View view){
        personne.setNom(nom.getText().toString());
        personne.setPrenom(prenom.getText().toString());
        personne.setEmail(email.getText().toString());
        personne.setPassword(password.getText().toString());

        dao.push(personne);

        Intent intent = new Intent(this, ListPersonne.class);
        startActivity(intent);
    }
}
