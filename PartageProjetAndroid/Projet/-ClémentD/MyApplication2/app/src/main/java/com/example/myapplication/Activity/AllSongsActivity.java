package com.example.myapplication.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.Adapter.SongAdapter;
import com.example.myapplication.BO.Song;
import com.example.myapplication.R;

import java.util.ArrayList;

public class AllSongsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    ArrayList<Song> arrayListSong;
    ArrayList<Song> selectedSongs;
    private static final String TAG = "AllSongsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_songs);

        arrayListSong = new ArrayList<Song>();
        recyclerView = findViewById(R.id.recycler);
        selectedSongs = new ArrayList<Song>();
        if(isStoragePermissionGranted(this)) {
            listAllSongs();
            addSongsToList();
        }
    }

    public void ValiderPlaylistClick (View view) {
        Intent intent = new Intent(this, ProfilActivity.class);
        intent.putExtra("songListFromPlaylist", selectedSongs);
        startActivity(intent);
    }

    private void addSongsToList() {
        SongAdapter songArrayAdapter = new SongAdapter(arrayListSong, new SongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song s) {
                Toast.makeText(AllSongsActivity.this, s.getTitre() + " -Added", Toast.LENGTH_SHORT).show();
                selectedSongs.add(s);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setAdapter(songArrayAdapter);
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
    private void listAllSongs()
    {
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
                    arrayListSong.add(new Song(artiste, titre, path));
                }
            }
        }
        cur.close();
    }

    public boolean isStoragePermissionGranted (Activity activity){
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(
                        activity,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }
}
