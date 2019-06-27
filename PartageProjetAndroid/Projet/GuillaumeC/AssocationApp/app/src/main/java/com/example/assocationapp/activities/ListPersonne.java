package com.example.assocationapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.assocationapp.MainActivity;
import com.example.assocationapp.R;
import com.example.assocationapp.adapters.PersonnesAdapter;
import com.example.assocationapp.daos.PersonneDao;
import com.example.assocationapp.models.Personne;

import java.util.ArrayList;
import java.util.List;

public class ListPersonne extends AppCompatActivity{
    private PersonneDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personne_list);

        dao = ((MainActivity)getApplication()).getPersonneDao();

        List<Personne> listPersonne = new ArrayList<>();

        listPersonne = dao.getList();

        RecyclerView recycler = findViewById(R.id.recycler);

        recycler.setLayoutManager(new GridLayoutManager(this, 1));

        PersonnesAdapter adapter = new PersonnesAdapter(listPersonne);

        recycler.setAdapter(adapter);
    }

    protected void detailPersonne(View view){
        Personne personne;
        //personne = dao.getById();

        Intent intent = new Intent(this, DetailPersonne.class);
        startActivity(intent);
    }
}
