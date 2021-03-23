package fr.iut.projet.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.iut.projet.R;

public class Adaptateur extends RecyclerView.Adapter<ViewHolder> {

    private List<CarnetItem> listeCarnet;

    public Adaptateur(List<CarnetItem> carnets){
        this.listeCarnet = carnets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout =  (LinearLayout)LayoutInflater.from(parent.getContext()).inflate(R.layout.carnet_item, parent,false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarnetItem courant = listeCarnet.get(position);
        holder.getTextView().setText(courant.getText());
    }


    @Override
    public int getItemCount() {
        return listeCarnet.size();
    }
}
