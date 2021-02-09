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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.iut.projet.R;

public class CarnetActivity extends AppCompatActivity {

    private ImageView imgPhoto;
    private Button btnPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carnet_activity);
        initActivity();
    }

    private void initActivity() {
        //récupération des objets graphiques
        imgPhoto=(ImageView)findViewById(R.id.imgPhoto);
        btnPhoto=(Button)findViewById(R.id.btnGalerie);

        //initialisation clic galerie
        createOnClicGalerie();
    }

    public void clicBoutonCamera(View sender){
        Intent monIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(monIntent);
    }


    /**
     * Evenement au clic sur le bouton galerie
     */
    public void createOnClicGalerie(){
        btnPhoto.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent galerieIntent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galerieIntent,1);
            }
        });
    }

    /**
     * méthode qui se déclenche après startActivityForResult
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //Vérifie si une image est récupérée
        if(requestCode==1 && resultCode==RESULT_OK){

        //accès à l'image depuis l'intent
            Uri monImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            //Curseur pour accèder au chemin de l'image
            Cursor curseur=this.getContentResolver().query(monImage,filePathColumn,null,null,null);

            //Position sur la première ligne (normalement il y en a qu'une car une seule image)
            curseur.moveToFirst();

            //Récupérer le chemin précis de l'image
            int columnIndex = curseur.getColumnIndex(filePathColumn[0]);
            String imgPath = curseur.getString(columnIndex);
            curseur.close();

            //Récupération de l'image
            Bitmap image= BitmapFactory.decodeFile(imgPath);

            //Affichage de l'image
            imgPhoto.setImageBitmap(image);
        }else{
            Toast.makeText(this, "Aucune image sélectionnée",Toast.LENGTH_LONG).show();
        } //FAIRE UN TRY CATCH AU CAS OU IL Y A PAS LA PERMISSION D'ACCEDER AUX PHOTOS
    }


    public void clicBoutonTexte(View sender){
        //Intent monIntent=new Intent(this, .class);
        //startActivity(monIntent);
    }
}
