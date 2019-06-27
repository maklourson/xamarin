package com.example.myapplication.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.BO.Song;
import com.example.myapplication.R;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    ArrayList<Song> songs = null;
    private OnItemClickListener onItemClickListener;

    /**
     * Constructeur
     * @param songs Données à afficher.
     */
    public SongAdapter(ArrayList<Song> songs,OnItemClickListener monClickListener)
    {
        this.songs = songs;
        this.onItemClickListener = monClickListener;
    }

    /**
     * Décompresse le fichier my_article_view.xml et créé un ViewHolder qui le représente.
     * @param parent
     * @param viewType
     * @return Un objet représentant my_article_view.xml
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlist_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public interface OnItemClickListener {
        void onItemClick(Song s);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        holder.mTextViewAuteur.setText(songs.get(position).getAuteur());
        holder.mTextViewTitre.setText(songs.get(position).getTitre());
        //holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(songs.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    /**
     * Classe interne
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextViewAuteur;
        public TextView mTextViewTitre;

        public ViewHolder(View v)
        {
            super(v);
            mTextViewAuteur = v.findViewById(R.id.authorSong);
            mTextViewTitre = v.findViewById(R.id.titleSong);
        }
    }
}
