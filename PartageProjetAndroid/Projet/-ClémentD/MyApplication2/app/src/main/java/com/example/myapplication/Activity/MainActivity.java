package com.example.myapplication.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.BO.Song;
import com.example.myapplication.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String ALLOWED_CHARACTERS ="0123456789AZERTYUIOPQSDFGHJKLMWXCVBN,&@#€${}|azertyuiopqsdfghjklmwxcvnb!\\/*+-;:";
    boolean IsDisable = false;
    private TextView textViewIntituleSong;
    private TextView textQuestion;
    private EditText editTextResponse;
    private Button buttonValidateResponse;
    private MediaPlayer mediaPlayerEnCours;
    private static int sizeOfCaptcha;
    private ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textQuestion = findViewById(R.id.QuestionTextView);
        editTextResponse = findViewById(R.id.EditResponse);
        buttonValidateResponse = findViewById(R.id.buttonValidResponse);
        textViewIntituleSong = findViewById(R.id.titleText);
        sizeOfCaptcha = 0;
        Intent intent = getIntent();
        songs = intent.getParcelableArrayListExtra("songListFromProfile");
        sizeOfCaptcha = intent.getIntExtra("captchaNbChar", sizeOfCaptcha);

        if(songs != null)
        {
            Random r = new Random();
            Song currentSong = songs.get(r.nextInt(songs.size()));
            textViewIntituleSong.setText(currentSong.getAuteur().concat(" - ").concat(currentSong.getTitre()));
            playSong(currentSong);
        }
    }

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfCaptcha;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

    public void ProfilClick (View view){
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }

    public void playSong(Song song) {
        if (mediaPlayerEnCours == null)
            mediaPlayerEnCours = new MediaPlayer();
        else {
            mediaPlayerEnCours.stop();
            mediaPlayerEnCours.release();
            mediaPlayerEnCours = new MediaPlayer();
        }
        try {
            mediaPlayerEnCours.setDataSource(song.getPath());
            mediaPlayerEnCours.prepare();
            mediaPlayerEnCours.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void DisableClick(View view){

        textQuestion.setText(getRandomString(8));
        textQuestion.setVisibility(View.VISIBLE);
        editTextResponse.setVisibility(View.VISIBLE);
        textViewIntituleSong.setVisibility(View.VISIBLE);
        buttonValidateResponse.setVisibility(View.VISIBLE);
        IsDisable = true;
    }

    public void SnoozeClick (View view) {
        textQuestion.setText(getRandomString(8));
        textQuestion.setVisibility(View.VISIBLE);
        textViewIntituleSong.setVisibility(View.VISIBLE);
        editTextResponse.setVisibility(View.VISIBLE);
        buttonValidateResponse.setVisibility(View.VISIBLE);
        IsDisable = false;
    }

    public void ValidResponseClick (View view) throws InterruptedException {
        Log.v(TAG,"JE SUIS LA");

        if(textQuestion.getText().equals(editTextResponse.getText().toString()))
        {
            if(IsDisable)
            {
                mediaPlayerEnCours.stop();
                editTextResponse.setText("");
                textViewIntituleSong.setVisibility(View.INVISIBLE);
                textQuestion.setVisibility(View.INVISIBLE);
                editTextResponse.setVisibility(View.INVISIBLE);
                buttonValidateResponse.setVisibility(View.INVISIBLE);
            }
            else
            {
                mediaPlayerEnCours.pause();
                editTextResponse.setText("");
                textViewIntituleSong.setVisibility(View.INVISIBLE);
                textQuestion.setVisibility(View.INVISIBLE);
                editTextResponse.setVisibility(View.INVISIBLE);
                buttonValidateResponse.setVisibility(View.INVISIBLE);
                Thread.sleep(10000);
                mediaPlayerEnCours.start();
            }
        }
    }
}