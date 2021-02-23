package fr.iut.projet.modele;

import java.util.List;

public class Carnet {

    //ATTRIBUTS

    private List<Photo> listePhotos;
    private List<Texte> listeTextes;

    //CONSTRUCTEURS

    public Carnet(List<Photo> listePhotos, List<Texte> listeTextes) {
        this.listePhotos = listePhotos;
        this.listeTextes = listeTextes;
    }


    //GETTERS ET SETTERS

    public void addPhoto(Photo p){
       listePhotos.add(p);
    }

    public void addTexte(Texte t){
        listeTextes.add(t);
    }

    public List<Photo> getListePhotos() {
        return listePhotos;
    }


    public List<Texte> getListeTextes() {
        return listeTextes;
    }



}
