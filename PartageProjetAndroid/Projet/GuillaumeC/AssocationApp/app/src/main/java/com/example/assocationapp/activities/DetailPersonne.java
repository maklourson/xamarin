package com.example.assocationapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.assocationapp.MainActivity;
import com.example.assocationapp.R;
import com.example.assocationapp.daos.PersonneDao;
import com.example.assocationapp.models.Personne;

public class DetailPersonne extends AppCompatActivity {
    private Personne personne;
    private PersonneDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personne_detail);

        dao = new PersonneDao(this);
    }

    @Override
    protected void onStart(){
        super.onStart();

        int identifier = (int)getIntent().getLongExtra("IDENTIFIER", -1);

        dao = ((MainActivity)getApplication()).getPersonneDao();

        personne = dao.getById(identifier);

        ((TextView) findViewById(R.id.nom)).setText(personne.getNom());
        ((TextView) findViewById(R.id.prenom)).setText(personne.getPrenom());
        ((TextView) findViewById(R.id.email)).setText(personne.getEmail());
    }
}
