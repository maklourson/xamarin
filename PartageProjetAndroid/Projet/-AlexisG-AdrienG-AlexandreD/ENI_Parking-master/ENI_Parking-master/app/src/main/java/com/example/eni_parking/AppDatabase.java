package com.example.eni_parking;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.eni_parking.bo.Agency;
import com.example.eni_parking.bo.Car;
import com.example.eni_parking.bo.CarType;
import com.example.eni_parking.bo.Customer;
import com.example.eni_parking.bo.Manager;
import com.example.eni_parking.bo.Rental;
import com.example.eni_parking.dao.AgencyDao;
import com.example.eni_parking.dao.CarDao;
import com.example.eni_parking.dao.CarTypeDao;
import com.example.eni_parking.dao.CustomerDao;
import com.example.eni_parking.dao.ManagerDao;
import com.example.eni_parking.dao.RentalDao;

@Database(entities = {Car.class, Agency.class, CarType.class, Customer.class, Manager.class, Rental.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract AgencyDao agencyDao();

    public abstract CarDao carDao();

    public abstract CarTypeDao carTypeDao();

    public abstract CustomerDao customerDao();

    public abstract ManagerDao managerDao();

    public abstract RentalDao rentalDao();


    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "parking-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
