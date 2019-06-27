package com.example.assocationapp.models;

public class Personne {

    private int identifier = -1;
    private long id;
    private String nom;
    private String prenom;
    private String password;
    private String email;

    public Personne(){

    }

    public Personne(String nom, String prenom, String password, String email){
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
    }

    public int getIdentifier() {
        return identifier;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNom(){
        return nom;
    }

    public void setNom(String nom){
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public void setPrenom(String prenom){
        this.prenom = prenom;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
