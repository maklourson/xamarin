package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.myapplication.bo.Voiture;
import com.example.myapplication.dao.VoituresDAO;

import java.util.List;


public class AjoutEditActivity extends AppCompatActivity {

    private final static String TAG = AjoutEditActivity.class.getName();

    private Voiture voitureModifie;
    private VoituresDAO dao = null;
    TextView prix = null;
    EditText nom =null;
    EditText plaque = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_edit);

        prix = (EditText) findViewById(R.id.edit_prix);
        nom = (EditText) findViewById(R.id.edit_nom);
        plaque = (EditText) findViewById(R.id.edit_plaque);

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        // Initialisation du DAO
        dao = new VoituresDAO(this);
        // Récupérer l'intention qui a déclenché cette activité
        Intent intention = getIntent();
        Voiture a = intention.getParcelableExtra(".VoitureSelectionne");
        if (a != null)
        {
            setDetailVoiture(a);
        }
        else
        {
            SharedPreferences spIntra = getSharedPreferences("configuration",MODE_PRIVATE);
            prix.setText("");
        }
    }

    private void validerModification() {

        Log.i("XXX","Entrée dans validerModification()");
        // Mettre à jour le modèle
        if(voitureModifie == null || voitureModifie.getId() == 0)
        {
            voitureModifie = new Voiture();
            doModifications();
            dao.insert(voitureModifie);
            this.finish();
        }
        else
        {
            Log.i("XXX","validerModification() : modification : " + voitureModifie.toString());
            doModifications();
            dao.update(voitureModifie);
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_detail_barre_action, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Actions de la barre d'action
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_save:
                validerModification();
                return true;

        }
        return true;
    }

    /**
     * Mettre à jour le modele à partir de la vue
     */
    protected void doModifications()
    {
        voitureModifie.setvNom(nom.getText().toString());
        voitureModifie.setvPlaque(plaque.getText().toString());
        voitureModifie.setvPrix(prix.getText().toString());

    }

    protected void setDetailVoiture(Voiture voiture)
    {
        voitureModifie = voiture;
        nom.setText(voiture.getvNom());
        prix.setText(voiture.getvPrix());
    }

}
