package com.example.assocationapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.assocationapp.R;

public class PersonneViewHolder extends RecyclerView.ViewHolder {
    private OnRecyclerItemClickListener listener;

    private TextView id;
    private TextView nom;
    private TextView prenom;
    private TextView email;

    public PersonneViewHolder(View itemView){
        super(itemView);
        this.id = itemView.findViewById(R.id.id);
        this.nom = itemView.findViewById(R.id.nom);
        this.prenom = itemView.findViewById(R.id.prenom);
        this.email = itemView.findViewById(R.id.email);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.click(PersonneViewHolder.this.getAdapterPosition());
            }
        });
    }

    public void setListener(OnRecyclerItemClickListener listener){
        this.listener = listener;
    }

    public TextView getId(){
        return id;
    }

    public TextView getNom(){
        return nom;
    }

    public TextView getPrenom(){
        return prenom;
    }

    public  TextView getEmail(){
        return email;
    }

    public interface OnRecyclerItemClickListener {
        void click(int position);
    }
}
