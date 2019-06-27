package com.example.myapplication.BO;

import java.util.ArrayList;

public class Profile {

    private String nom;
    private ArrayList<Song> songs;
    private int nbCaptchaChar;

    public int getNbCaptchaChar() {
        return nbCaptchaChar;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public void setNbCaptchaChar(int nbCaptchaChar) {
        this.nbCaptchaChar = nbCaptchaChar;
    }
}
