package fr.iut.projet.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import fr.iut.projet.R;

public class AddTextActivity extends AppCompatActivity {

    //constantes requestCode des Intent
    private static final int RETOUR_VALIDER_TEXTE = 3;

    static final String donnee="madonnee";

    //objet graphique
    private Button btnValider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_text_activity);
        initActivity();

    }

    //METHODE INIT APPELEE A LA CREATION DE L'ACTIVITE

    private void initActivity() {
        //récupération des objets graphiques
        btnValider = (Button)findViewById(R.id.btn_valider_texte);

        //initialisation des clics sur les boutons
        createOnClicValider();

    }

    //SAUVEGARDE LEGERE

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //On sauvegarde le texte de l'EditTexte dans le Bundle
        EditText texte = (EditText) findViewById(R.id.texte_a_ajouter);
        outState.putString("TEXTE_X", texte.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        //On récupère le texte qui est dans le Bundle
        super.onRestoreInstanceState(savedInstanceState);
        ((EditText)findViewById(R.id.texte_a_ajouter)).setText(String.valueOf(savedInstanceState.getString("TEXTE_X")));
    }

    //GESTION DU CLIC SUR LE BOUTON VALIDER

    private void createOnClicValider(){
        btnValider.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicValider();
            }
        });
    }

    private void clicValider(){
        EditText texte = (EditText) findViewById(R.id.texte_a_ajouter);
        Intent monIntent=new Intent(AddTextActivity.this, CarnetActivity.class);
        monIntent.putExtra(donnee,texte.getText().toString());
        startActivity(monIntent);
    }



}
