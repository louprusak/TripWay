package fr.iut.projet.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.iut.projet.R;
import fr.iut.projet.model.Carnet;

public class MyViewHolder extends RecyclerView.ViewHolder {

    //@BindView(R.layout.cellule) TextView textView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        // c'est ici qu'on fait toutes les opérations avec l'utilisateur
    }

    public void updateWithCarnet(Carnet carnet){
        //this.textView
    }
}
