package fr.iut.projet.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.iut.projet.R;
import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;
import fr.iut.projet.view.adapter.Adaptateur;
import fr.iut.projet.view.adapter.CarnetItem;

public class LogsFragment extends Fragment {
    //private GestionnaireCarnet lesCarnets = Stub.load();

    ArrayList<CarnetItem> listeCarnets = new ArrayList<>();

    private Carnet c1=new Carnet("Voyage à Londres","Juillet 2020","Angeleterre",0,0);
    private Carnet c2=new Carnet("Voyage à Paris","Août 2020","France",38.609556, -1.139637);
    private Carnet c3=new Carnet("Voyage à Tokyo","Juin 2020","Japon",43.2568193,-2.9225534);

    public void creationListe(){
        listeCarnets.add(new CarnetItem(c1.getTitre()));
        listeCarnets.add(new CarnetItem(c2.getTitre()));
        listeCarnets.add(new CarnetItem(c3.getTitre()));
    }



    public LogsFragment() {
        super(R.layout.logs);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        creationListe();
        init(view);
    }


    private void init(View v) {

        RecyclerView laListView = v.findViewById(R.id.liste_view);
        laListView.setHasFixedSize(true);
        laListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        laListView.setAdapter(new Adaptateur(listeCarnets));
    }
}
