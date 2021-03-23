package fr.iut.projet.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.iut.projet.R;
import fr.iut.projet.model.Carnet;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
       textView=itemView.findViewById(R.id.textView2);

    }

    public TextView getTextView() {return textView; }

}
