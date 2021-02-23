package fr.iut.projet.modele;

public class Photo {
    private String chemin;

    public Photo(String chemin) {
        this.chemin = chemin;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }
}
