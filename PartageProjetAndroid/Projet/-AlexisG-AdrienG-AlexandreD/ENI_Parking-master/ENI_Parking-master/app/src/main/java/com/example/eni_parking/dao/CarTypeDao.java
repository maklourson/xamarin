package com.example.eni_parking.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eni_parking.bo.CarType;

import java.util.List;

@Dao
public interface CarTypeDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    public void insertCarType(CarType customer);

    @Update
    public void updateCarType(CarType customer);

    @Delete
    public void deleteCarType(CarType customer);

    @Query("SELECT * FROM cartypes")
    public CarType[] loadAllCarType();

    @Query("SELECT * FROM cartypes WHERE id = :id")
    public CarType findCarTypeWithId(Integer id);

    @Query("SELECT * FROM cartypes WHERE type = :type")
    public CarType findCarTypeByType(String type);
}
