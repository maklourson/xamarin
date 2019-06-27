package com.example.eni_parking;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.eni_parking.bo.Agency;
import com.example.eni_parking.bo.Manager;
import com.example.eni_parking.dao.AgencyDao;
import com.example.eni_parking.dao.CarDao;
import com.example.eni_parking.dao.CustomerDao;
import com.example.eni_parking.dao.ManagerDao;
import com.example.eni_parking.dao.RentalDao;

public class CustomApplication extends Application {
    private AppDatabase database;
    private AgencyDao agencyDao;
    private CarDao carDao;
    private CustomerDao customerDao;
    private RentalDao rentalDao;
    private ManagerDao managerDao;

    @Override
    public void onCreate() {
        super.onCreate();

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "androvoiture").build();

        agencyDao = database.agencyDao();
        carDao = database.carDao();
        customerDao = database.customerDao();
        rentalDao = database.rentalDao();
        managerDao = database.managerDao();
    }

    public AgencyDao getAgencyDao() {
        return agencyDao;
    }

    public CarDao getCarDao() {
        return carDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public RentalDao getRentalDao() {
        return rentalDao;
    }

    public ManagerDao getManagerDao() {
        return managerDao;
    }
}
