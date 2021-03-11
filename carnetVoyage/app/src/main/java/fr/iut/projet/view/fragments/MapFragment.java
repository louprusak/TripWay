package fr.iut.projet.view.fragments;

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

import fr.iut.projet.R;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    public MapFragment() {
        super(R.layout.map);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*monActivite = ((MainActivity)getContext());
        assert monActivite != null;*/

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapfragment);
        //assert mapFragment != null;
        mapFragment.getMapAsync((OnMapReadyCallback) this);

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

        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Voyage a Sydney")
                .snippet("Pays : Australie - Lieu : Sydney - Date : 2001")); //utiliser id pour retrouver le carnet

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void loadMarkers(GoogleMap map){
        //recuperer la liste des carnets de la persistance
        //faire un foreach sur les éléments
        //faire un truc du genre :
        LatLng marker = new LatLng(-33.852, 151.211);
        map.addMarker(new MarkerOptions()
                .position(marker)
                .title("Voyage a Sydney")
                .snippet("Pays : Australie - Lieu : Sydney - Date : 2001")); //utiliser id pour retrouver le carnet
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
