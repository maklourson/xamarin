package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.adapters.VoitureAdapter;
import com.example.myapplication.bo.Voiture;
import com.example.myapplication.dao.VoituresDAO;

import java.util.ArrayList;

public class ListeVoituresActivity extends AppCompatActivity
{
    private static final String TAG = "ACOS";
    //Objet représentant le recyclerView
    private RecyclerView mRecyclerView;
    //Objet représentant l"adapter remplissant le recyclerView
    private RecyclerView.Adapter mAdapter;
    //Objet permettant de structurer l'ensemble des sous vues contenues dans le RecyclerView.
    private RecyclerView.LayoutManager mLayoutManager;
    //Liste bouchon
    private ArrayList<Voiture> voitures = new ArrayList<>();


    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Charge le menu décrit du fichier action_bar_liste.xml
        getMenuInflater().inflate(R.menu.action_bar_liste,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.i("XXX","onOptionsItemSelected : " + item.getItemId());
        switch (item.getItemId())
        {
            case R.id.item_delete:
                supprimerTout();
                break;
            case R.id.item_ajout :
                Intent intentAjout = new Intent(this,AjoutEditActivity.class);
                startActivity(intentAjout);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Définition de l'action du clic sur un item.
     */
    private View.OnClickListener monClickListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            int position = Integer.parseInt(view.getTag().toString());
            Log.i(TAG,"POSITION : " + view.getTag());
            changementLouee(voitures.get(position));
            Intent intent = new Intent(ListeVoituresActivity.this,ListeVoituresActivity.class);
            intent.putExtra("voiture",voitures.get(position));
            startActivity(intent);
        }
    };

    /**
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_voitures);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        chargementDonnees();
        chargementRecycler();
    }

    /**
     * Permet de charger le recycler view.
     */
    private void chargementRecycler()
    {
        //Lie le recyclerView aux fonctionnalité offerte par le linear layout
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //Liaison permettant de structurer l'ensemble des sous vues contenues dans le RecyclerView.
        mAdapter = new VoitureAdapter(voitures,monClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Permet de charger le bouchon.
     */
    private void chargementDonnees()
    {
        VoituresDAO dao = new VoituresDAO(this);
        SharedPreferences spIntra = getSharedPreferences("configuration",MODE_PRIVATE);
        voitures = dao.get(false);
    }
    private void changementLouee(Voiture voiture){
        VoituresDAO dao = new VoituresDAO(this);
        if(voiture.getvLocation()){
            voiture.setvLocation(false);
        }
        else{
            voiture.setvLocation(true);
        }
        dao.update(voiture);
    }
    protected void supprimerTout() {
        VoituresDAO dao = new VoituresDAO(this);
        for (int i = 0; i < voitures.size(); i++) {
            dao.delete(voitures.get(i));
        }
        voitures = dao.get(false);
        Intent intent = new Intent(ListeVoituresActivity.this,ListeVoituresActivity.class);
        startActivity(intent);
    }
}
