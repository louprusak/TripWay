package fr.iut.projet.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.iut.projet.R;
import fr.iut.projet.model.Carnet;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView;
    MonClickListener monClickListener;

    public ViewHolder(@NonNull View itemView, MonClickListener monClickListener) {
        super(itemView);
       textView=itemView.findViewById(R.id.textView2);
       this.monClickListener=monClickListener;
       itemView.setOnClickListener(this);

    }


    public TextView getTextView() {return textView; }

    @Override
    public void onClick(View v) {
        monClickListener.onMonclik(getAdapterPosition());
    }

    public interface MonClickListener{
        void onMonclik(int position);
    }
}
