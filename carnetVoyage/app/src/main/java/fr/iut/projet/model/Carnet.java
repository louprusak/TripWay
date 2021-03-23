package fr.iut.projet.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carnet implements Serializable {

    //ATTRIBUTS
    private String titre;
    private String date;
    private String lieu;
    private String pays;
    private ArrayList<String> listePhotos;
    private ArrayList<String> listeTextes;
    private double latitude;
    private double longitude;

    //CONSTRUCTEURS

    public Carnet(String titre, String date, String lieu, double latitude, double longitude) {
        this.listePhotos = new ArrayList<>();
        this.listeTextes = new ArrayList<>();
        this.titre=titre;
        this.date=date;
        this.lieu=lieu;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    //GETTERS ET SETTERS

    public void addPhoto(String photo){
       listePhotos.add(photo);
    }

    public void addTexte(String texte){
        listeTextes.add(texte);
    }


    public List<String> getListePhotos() {
        return listePhotos;
    }


    public List<String> getListeTextes() {
        return listeTextes;
    }

    public String toString(){

        return "Titre : "+this.titre+"\nDate :"+this.date+"\nLieu :"+this.lieu;
    }

    public String getTitre(){
        return titre;
    }



    public String getDate(){
        return date;
    }

    public String getLieu(){
        return lieu;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPays() {
        return pays;
    }
}
