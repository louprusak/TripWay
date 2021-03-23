package fr.iut.projet.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fr.iut.projet.R;
import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;
import fr.iut.projet.view.activities.CarnetActivity;
import fr.iut.projet.view.activities.MainActivity;
import fr.iut.projet.view.adapter.Adaptateur;
import fr.iut.projet.view.adapter.CarnetItem;
import fr.iut.projet.view.adapter.ViewHolder;
import fr.iut.projet.view.interfaces.IGestionnaireCarnet;

public class LogsFragment extends Fragment implements ViewHolder.MonClickListener {
    //private GestionnaireCarnet lesCarnets = Stub.load();

    private MainActivity monActivite;
    private GestionnaireCarnet monGestionnaire;
    private Carnet carnetEnCours;

    static final String lecarnet="moncarnet";
    static final String legestionnaire="gestionnaire";

    ArrayList<CarnetItem> listeCarnets = new ArrayList<>();

    private Carnet c1=new Carnet("Voyage à Londres","Juillet 2020","Angeleterre",0,0);
    private Carnet c2=new Carnet("Voyage à Paris","Août 2020","France",38.609556, -1.139637);
    private Carnet c3=new Carnet("Voyage à Tokyo","Juin 2020","Japon",43.2568193,-2.9225534);





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
        laListView.setAdapter(new Adaptateur(listeCarnets, this));
    }

    public void creationListe(){
        for (Carnet c : getCarnets().getLesCarnets()){
            listeCarnets.add(new CarnetItem(c.getTitre()));
        }
    }

    @Override
    public void onMonclik(int position) {
        GestionnaireCarnet monGestionnaire= getCarnets();

        ArrayList<Carnet> laListe = monGestionnaire.getLesCarnets();
        carnetEnCours=laListe.get(position);

        //On passe à l'activité suivante
        Intent monIntent=new Intent(getActivity(), CarnetActivity.class);
        monIntent.putExtra(lecarnet,carnetEnCours); //On passe le nouveau carnet
        monIntent.putExtra(legestionnaire,monGestionnaire); //On passe le gestionnaire
        startActivity(monIntent);

    }

    public GestionnaireCarnet getCarnets(){
        Activity acivity = getActivity();
        if(acivity instanceof IGestionnaireCarnet){
            return  ((MainActivity) acivity).getGestionnaireCarnet();
        }
        return null; // faire attention de tester le résultat null
    }
}
