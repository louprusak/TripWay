package fr.iut.projet.view.Serialize;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fr.iut.projet.model.GestionnaireCarnet;

public abstract class Serialize {

    public static final String filename = "tripway_data";

    public static void serialize (Context context, GestionnaireCarnet objet){
        try {
            File fichier = new File(context.getFilesDir(),filename);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeObject(objet);
            //oos.flush();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static GestionnaireCarnet deSerialize (Context context, GestionnaireCarnet objet){
        try {
            File fichier = new File(context.getFilesDir(),filename);
            if(!fichier.exists()){
                serialize(context,objet);
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
            GestionnaireCarnet doc;
            doc = (GestionnaireCarnet) ois.readObject();
            ois.close();
            return doc;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
