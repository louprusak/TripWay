package fr.iut.projet.data;

import fr.iut.projet.model.Carnet;
import fr.iut.projet.model.GestionnaireCarnet;

public class Stub {
    private GestionnaireCarnet laListe=new GestionnaireCarnet();
    private Carnet c1=new Carnet("Voyage à Londres","Juillet 2020","Angeleterre",0,0);
    private Carnet c2=new Carnet("Voyage à Paris","Août 2020","France",38.609556, -1.139637);
    private Carnet c3=new Carnet("Voyage à Tokyo","Juin 2020","Japon",43.2568193,-2.9225534);

    public void testAffichageListe(){
        laListe.addCarnet(c1);
        laListe.addCarnet(c2);
        laListe.addCarnet(c3);
    }

    public GestionnaireCarnet load(){
        //testAffichageListe();
        //return laListe;
        return null;
    }


}
