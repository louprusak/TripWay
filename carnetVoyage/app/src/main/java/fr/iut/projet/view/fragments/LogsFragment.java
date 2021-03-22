package fr.iut.projet.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.iut.projet.R;
import fr.iut.projet.data.Stub;
import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;
import fr.iut.projet.view.activities.CarnetActivity;
import fr.iut.projet.view.activities.MainActivity;
import fr.iut.projet.view.adapter.MyAdapter;

public class LogsFragment extends Fragment {
    //private GestionnaireCarnet lesCarnets = Stub.load();

    private GestionnaireCarnet laListe=new GestionnaireCarnet();
    private Carnet c1=new Carnet("Voyage à Londres","Juillet 2020","Angeleterre",0,0);
    private Carnet c2=new Carnet("Voyage à Paris","Août 2020","France",38.609556, -1.139637);
    private Carnet c3=new Carnet("Voyage à Tokyo","Juin 2020","Japon",43.2568193,-2.9225534);

    public void creationListe(){
        laListe.addCarnet(c1);
        laListe.addCarnet(c2);
        laListe.addCarnet(c3);
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

        laListView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        laListView.setAdapter(new MyAdapter(laListe.lesCarnets));
    }
}
