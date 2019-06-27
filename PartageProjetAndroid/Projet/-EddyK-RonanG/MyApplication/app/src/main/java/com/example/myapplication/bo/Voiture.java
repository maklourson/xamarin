package com.example.myapplication.bo;

import android.os.Parcel;
import android.os.Parcelable;

public class Voiture implements Parcelable {

    private int id;
    private String vNom;
    private String vPlaque;
    private String vPrix;
    private Boolean vLocation;


    public Voiture() {
    }


    public Voiture(String vNom, String vPlaque, String vPrix, Boolean vLocation) {
        this.vNom = vNom;
        this.vPlaque = vPlaque;
        this.vPrix = vPrix;
        this.vLocation = vLocation;
    }
    public Voiture(Integer id, String vNom, String vPlaque, String vPrix, Boolean vLocation) {
        this.id=id;
        this.vNom = vNom;
        this.vPlaque = vPlaque;
        this.vPrix = vPrix;
        this.vLocation = vLocation;
    }

    protected Voiture(Parcel in) {
        id = in.readInt();
        vNom = in.readString();
        vPlaque = in.readString();
        vPrix = in.readString();
        byte tmpVLocation = in.readByte();
        vLocation = tmpVLocation == 0 ? null : tmpVLocation == 1;
    }

    public static final Creator<Voiture> CREATOR = new Creator<Voiture>() {
        @Override
        public Voiture createFromParcel(Parcel in) {
            return new Voiture(in);
        }

        @Override
        public Voiture[] newArray(int size) {
            return new Voiture[size];
        }
    };

    public String getvNom() {
        return vNom;
    }

    public void setvNom(String vNom) {
        this.vNom = vNom;
    }

    public String getvPlaque() {
        return vPlaque;
    }

    public void setvPlaque(String vPlaque) {
        this.vPlaque = vPlaque;
    }

    public String getvPrix() {
        return vPrix;
    }

    public void setvPrix(String vPrix) {
        this.vPrix = vPrix;
    }

    public Boolean getvLocation() {
        return vLocation;
    }

    public void setvLocation(Boolean vLocation) {
        this.vLocation = vLocation;
    }
    public Boolean isLouee(){
        return this.vLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "vNom='" + vNom + '\'' +
                ", vPlaque='" + vPlaque + '\'' +
                ", vPrix=" + vPrix +
                ", vLocation=" + vLocation +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(vNom);
        dest.writeString(vPlaque);
        dest.writeString(vPrix);
        dest.writeByte((byte) (vLocation ? 1 : 0));
    }
}
