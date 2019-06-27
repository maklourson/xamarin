package com.example.assocationapp;

import android.app.Application;

import com.example.assocationapp.daos.PersonneDao;

public class MainActivity extends Application {

    private PersonneDao personneDao;

    @Override
    public void onCreate() {
        super.onCreate();

        personneDao = new PersonneDao(this);
    }

    public PersonneDao getPersonneDao() {
        return personneDao;
    }
}
