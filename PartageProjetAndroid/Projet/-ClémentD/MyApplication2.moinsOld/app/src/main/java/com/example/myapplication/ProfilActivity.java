package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplication.BO.Song;

import java.util.ArrayList;
import java.util.List;

public class ProfilActivity extends AppCompatActivity {

    private ArrayList<Song> songs;
    private Spinner handSpinner;
    private String Level;
    private int nbCharCaptcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_parameters);

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Level 1");
        spinnerArray.add("Level 2");
        spinnerArray.add("Level 3");
        spinnerArray.add("Level 4");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.levelspinner);
        sItems.setAdapter(adapter);


    }

    public void DisplayClick (View view){
        Intent intent = new Intent(this, AllSongsActivity.class);
        startActivity(intent);
    }

    public void ValidateProfilClick(View view)
    {
        handSpinner = findViewById(R.id.levelspinner);
        Level = handSpinner.getSelectedItem().toString();
        nbCharCaptcha = Integer.parseInt(Level.substring(Level.length() -1));
        if(nbCharCaptcha == 1)
            nbCharCaptcha = 6;
        else if (nbCharCaptcha == 2)
            nbCharCaptcha = 8;
        else if (nbCharCaptcha == 3)
            nbCharCaptcha = 10;
        else if (nbCharCaptcha == 4)
            nbCharCaptcha = 12;

        Intent intentFromPlaylist = getIntent();
        songs = intentFromPlaylist.getParcelableArrayListExtra("songListFromPlaylist");
        if(songs != null)
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("captchaNbChar", nbCharCaptcha);
            intent.putExtra("songListFromProfile", songs);
            startActivity(intent);
        }
    }
}
