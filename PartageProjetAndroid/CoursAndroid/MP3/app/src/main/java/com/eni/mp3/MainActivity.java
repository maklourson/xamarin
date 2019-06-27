package com.eni.mp3;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    String TAG = "ActivityMain";
    MediaPlayer mediaPlayerEnCours;
    ArrayList<Song> arrayListSong;
    private ListView listView;
    private TextView textViewIntituleSong;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayListSong = new ArrayList<>();

        listView = findViewById(R.id.listView);
        textViewIntituleSong = findViewById(R.id.textView);
        progressBar = findViewById(R.id.progressBar);


        if(isStoragePermissionGranted(this)) {
            listAllSongs();
            addSongsToList();
        }
    }

    private void addSongsToList() {
        SongArrayAdapter songArrayAdapter = new SongArrayAdapter(this,arrayListSong);

        songArrayAdapter.setOnItemClickListener(new SongArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song s) {
                playSong(s);
            }
        });

        listView.setAdapter(songArrayAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v("AUTORISATION","Permission: "+permissions[0]+ "was "+grantResults[0]);
            listAllSongs();
            addSongsToList();

        }
    }

    public  boolean isStoragePermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(
                        activity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    private void listAllSongs(){
        ContentResolver cr = this.getContentResolver();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + "!= 0";
        String sortOrder = MediaStore.Audio.Media.TITLE + " ASC";
        Cursor cur = cr.query(uri, null, selection, null, sortOrder);
        int count = 0;

        if (cur != null) {
            count = cur.getCount();

            if (count > 0) {
                while (cur.moveToNext()) {
                    String path = cur.getString(
                            cur.getColumnIndex(MediaStore.Audio.Media.DATA));
                    String artiste = cur.getString(
                            cur.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    String titre = cur.getString(
                            cur.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    Log.i("mediaFile", path);
                    arrayListSong.add(new Song(artiste, titre, path));
                }
            }
        }
        cur.close();
    }
    public void playSong(final Song song){
        if (mediaPlayerEnCours == null)
            mediaPlayerEnCours = new MediaPlayer();
        else{
            mediaPlayerEnCours.stop();
            mediaPlayerEnCours.release();
            mediaPlayerEnCours = new MediaPlayer();
        }
        try {
            mediaPlayerEnCours.setDataSource(song.getPath());
            mediaPlayerEnCours.prepare();
            mediaPlayerEnCours.start();
            textViewIntituleSong.setText(song.getAuteur().concat(" - ").concat(song.getTitre()));

            progressBar.setProgress(0);
            final int duration = mediaPlayerEnCours.getDuration();
            progressBar.setMax(duration);

            final Timer mTimer = new Timer();

            mediaPlayerEnCours.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(final MediaPlayer mediaPlayer) {
                    mTimer.schedule(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    if (!( progressBar.getProgress() >= duration)
                                            && mediaPlayer.isPlaying()) {
                                        progressBar.setProgress(
                                                mediaPlayer.getCurrentPosition());
                                    }
                                }
                            });
                        };
                    }, 1000,1000);
                }
            });

            mediaPlayerEnCours.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    int indexEnCours = arrayListSong.indexOf(song);
                    Song nextSong = null;
                    if(arrayListSong.size() == indexEnCours +1){
                        nextSong = arrayListSong.get(0);
                    }else{
                        nextSong = arrayListSong.get(indexEnCours+1);
                    }

                    playSong(nextSong);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
