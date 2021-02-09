package fr.iut.projet.view;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut.projet.R;

public class CarnetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carnet_activity);
    }

    public void clicBoutonCamera(View sender){
        Intent monIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(monIntent);
    }

    public void clicBoutonGalerie(View sender){
        Intent monIntent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivity(monIntent); //On va devoir ajouter le for Result pour récupérer le résultat
    }

    public void clicBoutonTexte(View sender){
        //Intent monIntent=new Intent(this, .class);
        //startActivity(monIntent);
    }
}
