package fr.iut.projet.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import fr.iut.projet.R;
import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;
import fr.iut.projet.view.activities.MainActivity;
import fr.iut.projet.view.interfaces.IGestionnaireCarnet;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    public MapFragment() {
        super(R.layout.map);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
    }

    public GestionnaireCarnet getCarnets(){
        Activity acivity = getActivity();
                if(acivity instanceof IGestionnaireCarnet){
                   return  ((MainActivity) acivity).getGestionnaireCarnet();
                }
                return null; // faire attention de tester le résultat null
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //a enlever juste pour tester la fonctionnalité
                //utiliser l'ID du marker pour retrouver le carnet et afficher sa page dédiée
            }
        });


        //recuperer la liste des carnets et pour chaque faire le add marker

        //loadMarkers(googleMap);

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void loadMarkers(GoogleMap map){
        ArrayList<Carnet> carnets = getCarnets().getLesCarnets();
        if(carnets != null){
            for (Carnet carnet: carnets) {
                LatLng marker = new LatLng(carnet.getLatitude(), carnet.getLongitude());
                map.addMarker(new MarkerOptions()
                        .position(marker)
                        .title(carnet.getTitre())
                        .snippet("Pays : "+carnet.getPays() +
                                " - Lieu : "+ carnet.getLieu()+
                                "- Date : "+carnet.getDate())); //utiliser id pour retrouver le carnet
            }
        }

        //recuperer la liste des carnets de la persistance
        //faire un foreach sur les éléments
        //faire un truc du genre :


    }



    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
