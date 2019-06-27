package com.eni.mp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by quentin for MP3 on 02/01/2018.
 */

public class SongArrayAdapter extends ArrayAdapter<Song> {
        private final Context context;
        private final ArrayList<Song> values;
        private OnItemClickListener onItemClickListener;

    public SongArrayAdapter(Context context, ArrayList<Song> values) {
            super(context, -1, values);
            this.context = context;
            this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_list_view, parent, false);

        final Song song = values.get(position);

        // Find layout views
        TextView textViewAuteur = (TextView) rowView.findViewById(R.id.textViewAuteur);
        TextView textViewTitre = (TextView) rowView.findViewById(R.id.textViewTitre);


        //set text on views
        textViewAuteur.setText(song.getAuteur());
        textViewTitre.setText(song.getTitre());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(song);
            }
        });

        return rowView;
    }

    public interface OnItemClickListener {
        void onItemClick(Song s);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
