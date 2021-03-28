package fr.iut.projet.model;

import java.io.Serializable;
import java.util.ArrayList;

public class GestionnaireCarnet implements Serializable {

    public ArrayList<Carnet> lesCarnets;

    public GestionnaireCarnet() {
        this.lesCarnets=new ArrayList<>();
    }

    public void addCarnet(Carnet c){
        lesCarnets.add(c);
    }

    public ArrayList<Carnet> getLesCarnets() {
        return lesCarnets;
    }

    public String toString(){
        return "\nTous les carnets : "+lesCarnets;
    }

}
