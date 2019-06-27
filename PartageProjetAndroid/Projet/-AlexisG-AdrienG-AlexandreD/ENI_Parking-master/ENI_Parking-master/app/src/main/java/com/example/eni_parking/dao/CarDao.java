package com.example.eni_parking.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eni_parking.bo.Car;

import java.util.List;


@Dao
public interface CarDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    public void insertCar(Car car);

    @Update
    public void updateCar(Car car);

    @Delete
    public void deleteCar(Car car);

    @Query("SELECT * FROM Cars")
    public Car[] loadAllCar();

    @Query("SELECT * FROM cars WHERE id = :id")
    public Car findCarWithId(Integer id);

    @Query("SELECT * FROM cars WHERE carType_id = :idCarType")
    public List<Car> findCarByCarType(Integer idCarType);

    @Query("SELECT * FROM cars WHERE carType_id = :idCarType AND agency_id = :agency_id")
    public List<Car> findCarByCarTypeAndAgency(Integer idCarType, Integer agency_id);

    @Query("SELECT * FROM cars WHERE agency_id = :agency_id")
    public Car[] findCarByAgency(Integer agency_id);
}
