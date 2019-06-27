package com.example.assocationapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assocationapp.R;
import com.example.assocationapp.models.Personne;
import com.example.assocationapp.viewholders.PersonneViewHolder;

import java.util.List;

public class PersonnesAdapter extends RecyclerView.Adapter<PersonneViewHolder> {

    private List<Personne> items;
    private PersonneViewHolder.OnRecyclerItemClickListener listener;

    public PersonnesAdapter(List<Personne> items){
        this.items = items;
    }

    public void setListener(PersonneViewHolder.OnRecyclerItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public PersonneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.personne_item, viewGroup, false);

        PersonneViewHolder ph = new PersonneViewHolder(v);
        ph.setListener(listener);

        return ph;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonneViewHolder personneViewHolder, int i) {
        personneViewHolder.getId().setText(String.valueOf(this.items.get(i).getId()));
        personneViewHolder.getNom().setText(this.items.get(i).getNom());
        personneViewHolder.getPrenom().setText(this.items.get(i).getPrenom());
        personneViewHolder.getEmail().setText(this.items.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
