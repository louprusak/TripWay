package fr.iut.projet.data;

import java.util.ArrayList;

import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;

public abstract class Stub {

    private static ArrayList<Carnet> createCarnets(){
        ArrayList<Carnet> liste = new ArrayList<>();
        Carnet c1=new Carnet("Le métro londonnien","Juillet 2020","Angleterre","Londres",51.507334,-0.127744);
        c1.addPhoto("@drawable/londres1.jpeg");
        c1.addPhoto("@drawable/londres2.jpeg");
        c1.addPhoto("@drawable/londres3.jpeg");
        Carnet c2=new Carnet("Tour Eiffel","Août 2020","France","Paris",48.858646, 2.293909);
        c2.addPhoto("@drawable/paris1.jpeg");
        c2.addPhoto("@drawable/paris2.jpeg");
        c2.addPhoto("@drawable/paris3.jpeg");
        Carnet c3=new Carnet("Voyage au Japon","Juin 2020","Japon","Tokyo",35.686741705119104,139.77110136477555);
        c3.addPhoto("@drawable/tokyo1.jpeg");
        c3.addPhoto("@drawable/tokyo2.jpeg");
        Carnet c4=new Carnet("test","Juillet 2020","Angleterre","Londres",0,0);
        Carnet c5=new Carnet("test","Juillet 2020","Angleterre","Londres",1,-1);
        Carnet c6=new Carnet("test","Juillet 2020","Angleterre","Londres",2,-2);
        Carnet c7=new Carnet("test","Juillet 2020","Angleterre","Londres",3,-3);
        Carnet c8=new Carnet("test","Juillet 2020","Angleterre","Londres",4,-4);
        Carnet c9=new Carnet("test","Juillet 2020","Angleterre","Londres",5,-5);
        Carnet c10=new Carnet("test","Juillet 2020","Angleterre","Londres",6,-6);
        liste.add(c1);
        liste.add(c2);
        liste.add(c3);
        liste.add(c4);
        liste.add(c5);
        liste.add(c6);
        liste.add(c8);
        liste.add(c9);
        liste.add(c10);
        liste.add(c7);
        return liste;
    }


    private static GestionnaireCarnet loadCarnets(ArrayList<Carnet> liste){
        GestionnaireCarnet laListe=new GestionnaireCarnet();
        for (Carnet c : liste){
            laListe.addCarnet(c);
        }
        return laListe;
    }

    public static GestionnaireCarnet load(){
        return loadCarnets(createCarnets());
    }


}
