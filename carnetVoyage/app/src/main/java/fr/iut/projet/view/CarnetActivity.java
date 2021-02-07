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
}
