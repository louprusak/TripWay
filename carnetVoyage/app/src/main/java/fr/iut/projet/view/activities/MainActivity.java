package fr.iut.projet.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

import fr.iut.projet.R;
import fr.iut.projet.data.Stub;
import fr.iut.projet.model.GestionnaireCarnet;
import fr.iut.projet.view.Serialize.Serialize;
import fr.iut.projet.view.activities.CreateActivity;
import fr.iut.projet.view.fragments.CreateFragment;
import fr.iut.projet.view.fragments.HomeFragment;
import fr.iut.projet.view.fragments.LogsFragment;
import fr.iut.projet.view.fragments.MapFragment;
import fr.iut.projet.view.interfaces.IGestionnaireCarnet;

public class MainActivity extends AppCompatActivity implements IGestionnaireCarnet {

    private GestionnaireCarnet manager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Récupération des données
        if(savedInstanceState != null){
            manager = (GestionnaireCarnet) savedInstanceState.getSerializable("manager");
        }
        else{
            manager = Serialize.deSerialize(getApplicationContext(),manager);
        }
        if(manager == null){
            manager = new Stub().load();
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView menu = findViewById(R.id.bottom_navigation);

        //navigation
        menu.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    buttonClicOnNavHome();
                    break;
                case R.id.nav_logs:
                    buttonClicOnNavLogs();
                    break;
                case R.id.nav_create:
                    buttonClicOnNavCreate();
                    break;
                case R.id.nav_map:
                    buttonClicOnNavMap();
                    break;
            }
            return true;
        });
         menu.setSelectedItemId(R.id.nav_home);


    }

    private void buttonClicOnNavHome() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.afficheurfragment,new HomeFragment(), null)
                .commit();
    }

    private void buttonClicOnNavMap() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.afficheurfragment,new MapFragment(), null)
                .commit();
    }

    private void buttonClicOnNavLogs() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.afficheurfragment,new LogsFragment(), null)
                .commit();
    }

    private void buttonClicOnNavCreate(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.afficheurfragment,new CreateFragment(), null)
                .commit();
    }

    @Override
    public GestionnaireCarnet getGestionnaireCarnet() {
        return manager;
    }

    @Override
    protected void onDestroy() {
        Serialize.serialize(getApplicationContext(),manager);
        super.onDestroy();
    }
}