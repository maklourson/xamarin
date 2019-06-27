package com.example.eni_parking.bo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "manager")
public class Manager {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="firstname")
    private String firstname;

    @ColumnInfo(name="lastname")
    private String lastname;

    @ColumnInfo(name="phone")
    private String phone;

    @ColumnInfo(name="mail")
    private String mail;

    @ColumnInfo(name="password")
    private String password;

    @ForeignKey(
            entity = Agency.class,
            parentColumns = "id",
            childColumns = "agencyID"
    )
    private int agencyID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String tel) {
        this.phone = tel;
    }

    public int getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(int agencyID) {
        this.agencyID = agencyID;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", agencyID=" + agencyID +
                '}';
    }
}
