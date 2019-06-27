package com.eni.mp3;

/**
 * Created by quentin for MP3 on 02/01/2018.
 */

public class Song {
    private String auteur;
    private String titre;
    private String path;

    public Song(String auteur, String titre, String path) {
        this.auteur = auteur;
        this.titre = titre;
        this.path = path;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
