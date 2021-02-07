package fr.iut.projet.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut.projet.R;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_activity);
    }

    public void clicBoutonValider(View sender){
        Intent monIntent=new Intent(this, CarnetActivity.class);
        startActivity(monIntent);
    }
}