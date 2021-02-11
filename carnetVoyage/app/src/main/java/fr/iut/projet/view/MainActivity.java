package fr.iut.projet.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import fr.iut.projet.R;
import fr.iut.projet.view.CreateActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void clicBoutonCreate(View sender){
        Intent monIntent=new Intent(this, CreateActivity.class);
        startActivity(monIntent);
    }
}