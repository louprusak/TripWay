package fr.iut.projet.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.iut.projet.R;
import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView;
    private Button btnSuppression;
    MonClickListener monClickListener;

    public ViewHolder(@NonNull View itemView, MonClickListener monClickListener) {
        super(itemView);
        textView=itemView.findViewById(R.id.textView2);
        btnSuppression = itemView.findViewById(R.id.btn_sup_carnet);
       this.monClickListener=monClickListener;
       itemView.setOnClickListener(this);
        createOnClicSuppression();

    }

    //GESTION DU CLIC SUR LE BOUTON SUPPRIMER

    private void createOnClicSuppression(){
        btnSuppression.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClickListener.clicSuppression(getAdapterPosition());
            }
        });
    }



    public TextView getTextView() {return textView; }

    @Override
    public void onClick(View v) {
        monClickListener.onMonclik(getAdapterPosition());
    }

    public interface MonClickListener{
        void onMonclik(int position);
        void clicSuppression(int position);
    }

}
