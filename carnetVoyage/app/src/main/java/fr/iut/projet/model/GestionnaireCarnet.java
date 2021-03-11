package fr.iut.projet.model;

import java.util.ArrayList;

public class GestionnaireCarnet {
    private ArrayList<Carnet> lesCarnets;

    //+méthode load et save ?
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
