package com.example.myapplication.BO;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    private String auteur;
    private String titre;
    private String path;

    public Song(String auteur, String titre, String path) {
        this.auteur = auteur;
        this.titre = titre;
        this.path = path;
    }

    protected Song(Parcel in) {
        auteur = in.readString();
        titre = in.readString();
        path = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(auteur);
        dest.writeString(titre);
        dest.writeString(path);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

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
