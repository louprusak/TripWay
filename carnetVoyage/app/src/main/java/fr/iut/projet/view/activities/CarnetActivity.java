package fr.iut.projet.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.iut.projet.R;
import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;

public class CarnetActivity extends AppCompatActivity {

    //constantes requestCode des Intent
    private static final int RETOUR_GALERIE = 1;
    private static final int RETOUR_CAMERA = 2;
    private static final int RETOUR_TEXTE = 3;

    //objets graphiques
    private ImageView imgPhoto;
    private TextView maTexteView;
    private TextView maTitreView;
    private Button btnPhoto;
    private Button btnCamera;
    private Button btnTexte;
    private String photoPath = null;
    private Bitmap image;
    private LinearLayout monLayout;

    //Le carnet en cours
    private Carnet carnet;
    private Carnet carnetEnCours;
    private GestionnaireCarnet gestionnaire;

    //position des éléments
    int positionTextView=100;
    int positionImageView=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carnet_activity);
        initActivity();
        Intent intentRecup=getIntent();
        if(intentRecup!=null){

            //On récupère le carnet en cours (titre, date et lieu qui sont rentrés  juste avant)
            if((intentRecup.hasExtra("moncarnet"))) {
                carnet = (Carnet) intentRecup.getSerializableExtra("moncarnet");
                //Affichage des données du carnet
                maTitreView.setText(carnet.toString());
            }
            //On récupère le gestionnaire de carnet
            if((intentRecup.hasExtra("gestionnaire"))) {
                gestionnaire = (GestionnaireCarnet) intentRecup.getSerializableExtra("gestionnaire");
                ArrayList<Carnet> laListe = gestionnaire.getLesCarnets();
                //On récupère le carnet en cours de création
                for(int i=0; i<laListe.size();i++){
                    if(laListe.get(i).getTitre().equals(carnet.getTitre())){
                        carnetEnCours=laListe.get(i);
                    }
                }
            }

        }
    }

    //SAUVEGARDE LEGERE - sauvegarde des données quand on tourne l'écran

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //On sauvegarde la photo de l'ImageView dans le Bundle
        outState.putString("KEY_PHOTO",photoPath);
        //On sauvegarde le Gestionnaire dans le Bundle
        outState.putSerializable("KEY_GESTIONNAIRE",gestionnaire);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        //On récupère le texte qui est dans le Bundle
        super.onRestoreInstanceState(savedInstanceState);
        String path= savedInstanceState.getString("KEY_PHOTO");
        image = BitmapFactory.decodeFile(photoPath);
        imgPhoto.setImageBitmap(image);
    }



    /**
     * méthode appelée pendant le onCreate, qui récupère les éléments graphiques et initialise les évenements.
     */
    private void initActivity() {
        //récupération des objets graphiques
        imgPhoto=(ImageView)findViewById(R.id.imgPhoto);
        btnPhoto=(Button)findViewById(R.id.btnGalerie);
        btnCamera=(Button)findViewById(R.id.btnCamera);
        btnTexte=(Button)findViewById(R.id.btnTexte);
        maTexteView=(TextView)findViewById(R.id.texte_anecdote);
        maTitreView=(TextView)findViewById(R.id.titre_view);
        monLayout=(LinearLayout) findViewById(R.id.layout_content);

        //initialisation des clics sur les boutons
        createOnClicGalerie();
        createOnClicCamera();
        createOnClicTexte();
    }


    //RECUPERER UNE PHOTO DEPUIS LA GALERIE

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
    @SuppressLint("ResourceType")
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        //INTENT DE LA GALERIE
        //POUR QUE CA MARCHE IL FAUT AUTORISER LES FICHIERS DANS APPLICATIONS>TRIPWAY
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
            carnetEnCours.addPhoto(imgPath);
            image = BitmapFactory.decodeFile(imgPath);

            //Affichage de l'image
            //Création, redimensionnement et positionnement de l'imageView
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(image);
            //positionImageView-=510;
            //imageView.setTranslationY(positionImageView);
            //imageView.setTranslationX(500);
            //int height= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, imgPhoto.getContext().getResources().getDisplayMetrics());
            //int width= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, imgPhoto.getContext().getResources().getDisplayMetrics());
            monLayout.addView(imageView);

        }
        //INTENT DE LA CAMERA
        if(requestCode==RETOUR_CAMERA && resultCode==RESULT_OK){
            carnetEnCours.addPhoto(photoPath);
            image = BitmapFactory.decodeFile(photoPath);
            //Création , dimensionnement et positionnement de l'imageView
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageBitmap(image);
            //positionImageView-=510;
            //imageView.setTranslationY(positionImageView);
            //imageView.setTranslationX(500);
            //int height= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, imgPhoto.getContext().getResources().getDisplayMetrics());
            //int width= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, imgPhoto.getContext().getResources().getDisplayMetrics());
            monLayout.addView(imageView);

        }
        //INTENT DU TEXTE (result_canceled car on retourne à l'activité où il y a le carnet, pas une nouvelle)
        if(requestCode==RETOUR_TEXTE && resultCode==RESULT_CANCELED){
            String texte = data.getStringExtra(AddTextActivity.KEY_DONNEE);
            if(texte!=null) {
                //ajout du texte dans le carnet
                carnetEnCours.addTexte(texte);
                //création et positionnement de la textView associée à ce texte
                //positionTextView -= 200;
                TextView textView = new TextView(getApplicationContext());
                textView.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                //textView.setBackgroundColor(R.color.black);
                textView.setTypeface(null, Typeface.BOLD);
                textView.setTextColor(Color.BLACK);
                textView.setText(texte);
                //textView.setTranslationY(positionTextView);
                //textView.setTranslationX(50);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                //.setBackgroundResource(R.drawable.bordure_textview);
                monLayout.addView(textView);
            }

        }

        Log.d("CARNET",carnetEnCours.getListePhotos().toString());
        Log.d("CARNET",carnetEnCours.getListeTextes().toString());

    }


    //AJOUTER DU TEXTE AU CARNET

    private void createOnClicTexte(){
        btnTexte.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicTexte();
            }
        });
    }

    private void clicTexte(){
        Intent monIntent=new Intent(CarnetActivity.this, AddTextActivity.class);
        startActivityForResult(monIntent,RETOUR_TEXTE);
    }

    //RECUPERER UNE PHOTO DEPUIS L'APPAREIL PHOTO

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
