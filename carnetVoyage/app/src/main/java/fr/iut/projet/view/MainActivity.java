package fr.iut.projet.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.iut.projet.R;
import fr.iut.projet.view.CreateActivity;
import fr.iut.projet.view.fragments.AjouterCarnet;
import fr.iut.projet.view.fragments.Home;

public class MainActivity extends AppCompatActivity {

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView menu = findViewById(R.id.bottom_navigation);

        menu.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    clicBoutonNavHome();
                    break;
                case R.id.nav_carnets:
                    clicBoutonNavCarnets();
                    break;
                case R.id.nav_add:
                    clicBoutonNavAdd();
                    break;
                case R.id.nav_map:
                    clicBoutonNavMap();
                    break;
            }
            return true;
        });

    }

    private void clicBoutonNavMap() {
    }

    private void clicBoutonNavCarnets() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.afficheurfragment,new AjouterCarnet(), null)
                .commit();
    }

    private void clicBoutonNavHome() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.afficheurfragment,new Home(), null)
                .commit();
    }

    private void clicBoutonNavAdd(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.afficheurfragment,new AjouterCarnet(), null)
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