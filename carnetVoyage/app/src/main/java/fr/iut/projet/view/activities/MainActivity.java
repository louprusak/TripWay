package fr.iut.projet.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.iut.projet.R;
import fr.iut.projet.view.activities.CreateActivity;
import fr.iut.projet.view.fragments.CreateFragment;
import fr.iut.projet.view.fragments.HomeFragment;
import fr.iut.projet.view.fragments.LogsFragment;
import fr.iut.projet.view.fragments.MapFragment;

public class MainActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView menu = findViewById(R.id.bottom_navigation);

        buttonClicOnNavHome();

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


        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapfragment);
        mapFragment.getMapAsync((OnMapReadyCallback) this);*/
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
    protected void onPostResume() {
        super.onPostResume();

        
    }

    public void clicBoutonCreate(View sender){
        Intent monIntent=new Intent(this, CreateActivity.class);
        startActivity(monIntent);
    }
}