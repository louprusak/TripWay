package fr.iut.projet.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.iut.projet.R;
import fr.iut.projet.data.Stub;
import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;
import fr.iut.projet.view.activities.CarnetActivity;
import fr.iut.projet.view.activities.MainActivity;
import fr.iut.projet.view.interfaces.IGestionnaireCarnet;

public class CreateFragment extends Fragment {

    private MainActivity monActivite;
    private GestionnaireCarnet monGestionnaire;

    static final String lecarnet="moncarnet";
    static final String legestionnaire="gestionnaire";

    public CreateFragment() {
        super(R.layout.create);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        monActivite = (MainActivity)getActivity();
        this.monGestionnaire=getCarnets();
        init(view);
    }

    public GestionnaireCarnet getCarnets(){
        Activity acivity = getActivity();
        if(acivity instanceof IGestionnaireCarnet){
            return  ((MainActivity) acivity).getGestionnaireCarnet();
        }
        return null; // faire attention de tester le résultat null
    }

    private void init(View v){
        Button b=(Button)v.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On créer un nouveau carnet
                EditText edit_titre = (EditText) getView().findViewById(R.id.edit_titre_carnet);
                String texte_titre = edit_titre.getText().toString();

                EditText edit_date = (EditText) getView().findViewById(R.id.edit_date_carnet);
                String texte_date = edit_date.getText().toString();

                EditText edit_lieu = (EditText) getView().findViewById(R.id.edit_lieu_carnet);
                String texte_lieu = edit_lieu.getText().toString();

                EditText edit_pays = (EditText) getView().findViewById(R.id.edit_pays_carnet);
                String texte_pays = edit_pays.getText().toString();

                try {
                    EditText edit_lat = (EditText) getView().findViewById(R.id.edit_lat_carnet);
                    Double texte_lat = 0.0;
                    if (edit_lat.getText().toString().equals("")) {
                        Log.d("CO", "passé dans le if pour lat");
                        texte_lat = Double.parseDouble(edit_lat.getText().toString());
                    }

                    EditText edit_long = (EditText) getView().findViewById(R.id.edit_long_carnet);
                    Double texte_long = 0.0;
                    if (edit_long.getText().toString().equals("")) {
                        Log.d("CO", "passé dans le if pour long");
                        texte_long = Double.parseDouble(edit_long.getText().toString());
                    }


                    Carnet carnet = new Carnet(texte_titre, texte_date, texte_pays, texte_lieu, texte_lat, texte_long);

                    if(monGestionnaire==null){
                        Log.d("MANAGER","nul");
                        //On récupère les carnets du Stub pour tester
                        monGestionnaire = Stub.load();
                    }
                    monGestionnaire.addCarnet(carnet);


                    //On passe à l'activité suivante
                    Intent monIntent=new Intent(getActivity(), CarnetActivity.class);
                    monIntent.putExtra(lecarnet,carnet); //On passe le nouveau carnet
                    monIntent.putExtra(legestionnaire,monGestionnaire); //On passe le gestionnaire
                    startActivity(monIntent);

                }
                catch(Exception e){
                    Intent monIntent=new Intent(getActivity(), MainActivity.class);
                    startActivity(monIntent);
                }


            }
        } );
    }
}
