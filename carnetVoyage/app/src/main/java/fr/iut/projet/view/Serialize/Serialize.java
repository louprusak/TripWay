package fr.iut.projet.view.Serialize;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;

public abstract class Serialize {

    public static final String filename = "tripway_data";

    public static void serialize (Context context, GestionnaireCarnet objet){
        try {
            Log.d("SERIALIZE","Passage dans la méthode serialize");
/*
            File fichier = new File(context.getFilesDir(),filename);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeObject(objet.getLesCarnets());
            oos.flush();
            oos.close();*/

            File fichier = new File(context.getFilesDir(), filename);

            FileOutputStream fos = context.openFileOutput(filename, context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(objet);
            oos.flush();
            oos.close();
            fos.close();



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static GestionnaireCarnet deSerialize (Context context, GestionnaireCarnet objet){
        try {
            Log.d("SERIALIZE","Passage dans la méthode deserialize");
            /*
            File fichier = new File(context.getFilesDir(),filename);
            if(!fichier.exists()){
                return null;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
            GestionnaireCarnet doc = new GestionnaireCarnet();
            doc.setLesCarnets((ArrayList<Carnet>) ois.readObject());
            ois.close();
            return doc;*/
            FileInputStream fis = context.openFileInput(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            GestionnaireCarnet doc;
            doc = (GestionnaireCarnet) ois.readObject();
            ois.close();
            fis.close();
            return doc;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
