package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bo.Voiture;

import java.util.ArrayList;

public class VoitureAdapter extends RecyclerView.Adapter<VoitureAdapter.ViewHolder>
{
    ArrayList<Voiture> voitures = null;
    private View.OnClickListener monClickListener;

    public VoitureAdapter(ArrayList<Voiture> voitures,View.OnClickListener monClickListener)
    {
        this.voitures = voitures;
        this.monClickListener = monClickListener;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_cards, parent, false);

        ViewHolder vh = new ViewHolder(v,monClickListener);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.mTextViewNom.setText("Nom : "+voitures.get(position).getvNom());
        holder.mTextViewPlaque.setText("Plaque : " + voitures.get(position).getvPlaque());
        holder.mTextViewPrix.setText("Prix : "+voitures.get(position).getvPrix());
        if(voitures.get(position).getvLocation()){
            holder.mTextViewLouee.setText("Louée");
        }
        else{
            holder.mTextViewLouee.setText("Non Louée");
        }
        holder.itemView.setTag(position);
    }

    public int getItemCount() {
        return voitures.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextViewNom;
        public TextView mTextViewPrix;
        public TextView mTextViewPlaque;
        public TextView mTextViewLouee;

        public ViewHolder(View v, View.OnClickListener monClickListener)
        {
            super(v);
            mTextViewNom = v.findViewById(R.id.tv_info);
            mTextViewPrix = v.findViewById(R.id.prix_voiture);
            mTextViewPlaque = v.findViewById(R.id.plaque_voiture);
            mTextViewLouee = v.findViewById(R.id.louee);
            v.setOnClickListener(monClickListener);
        }
    }


}
