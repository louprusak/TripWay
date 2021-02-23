package fr.iut.projet.view;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.iut.projet.R;

public class CarnetActivity extends AppCompatActivity {

    //constantes requestCode des Intent
    private static final int RETOUR_GALERIE = 1;
    private static final int RETOUR_CAMERA = 2;

    private ImageView imgPhoto;
    private Button btnPhoto;
    private Button btnCamera;
    private String photoPath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carnet_activity);
        initActivity();
    }

    /**
     * méthode appelée pendant le onCreate, qui récupère les éléments graphiques et initialise les évenements.
     */
    private void initActivity() {
        //récupération des objets graphiques
        imgPhoto=(ImageView)findViewById(R.id.imgPhoto);
        btnPhoto=(Button)findViewById(R.id.btnGalerie);
        btnCamera=(Button)findViewById(R.id.btnCamera);

        //initialisation du clic sur le bouton galerie
        createOnClicGalerie();
        createOnClicCamera();
    }




    /**
     * Evenement au clic sur le bouton galerie
     */
    public void createOnClicGalerie(){
        btnPhoto.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent galerieIntent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galerieIntent,RETOUR_GALERIE);
            }
        });
    }

    /**
     * méthode qui se déclenche après startActivityForResult
     * @param requestCode
     * @param resultCode
     * @param data : c'est l'Intent qui fait passer les données
     */
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //INTENT DE LA GALERIE
        if(requestCode==RETOUR_GALERIE && resultCode==RESULT_OK) {

            //Accès à l'image depuis l'intent
            Uri monImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            //Curseur pour accèder au chemin de l'image
            Cursor curseur = this.getContentResolver().query(monImage, filePathColumn, null, null, null);

            //Position sur la première ligne (normalement il y en a qu'une car une seule image)
            curseur.moveToFirst();

            //Récupération du chemin précis de l'image
            int columnIndex = curseur.getColumnIndex(filePathColumn[0]);
            String imgPath = curseur.getString(columnIndex);
            curseur.close();

            //Récupération de l'image
            Bitmap image = BitmapFactory.decodeFile(imgPath);

            //Affichage de l'image
            imgPhoto.setImageBitmap(image);
        }
        //INTENT DE LA CAMERA
        if(requestCode==RETOUR_CAMERA && resultCode==RESULT_OK){
            Bitmap image = BitmapFactory.decodeFile(photoPath);
            imgPhoto.setImageBitmap(image);

        }

    }


    public void clicBoutonTexte(View sender){
        //Intent monIntent=new Intent(this, .class);
        //startActivity(monIntent);
        //TO DO : Créer un nouvelle activité qui s'ouvrira ici qui contiendra une textEdit (à récupérer pour afficher ici)
    }



    private void createOnClicCamera(){
        btnCamera.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                prendrePhoto();

            }
        });
    }

    /**
     * est appelée au onClick sur le bouton Camera
     * méthode qui accède à l'appareil photo et mémorise dans un fichier temporaire
     */
    private void prendrePhoto(){
        Intent cameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // test est ce que le téléphone a bien une caméra
        if(cameraIntent.resolveActivity(getPackageManager()) != null) {
            //créer un nom de fichier unique
            String time = new SimpleDateFormat("yyyyMMdd_HHmmdd").format(new Date());
            File photoDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            try {
                File photoFile = File.createTempFile("photo"+time,".jpg",photoDir);
                //enregistrer le chemin complet
                photoPath = photoFile.getAbsolutePath();
                // créer l'URI
                Uri photoUri = FileProvider.getUriForFile(CarnetActivity.this,CarnetActivity.this.getApplicationContext().getPackageName()+".provider",photoFile);
                //transfert URI vers l'intent pour enregistrement dans fichier temporaire
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
                startActivityForResult(cameraIntent,RETOUR_CAMERA);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
