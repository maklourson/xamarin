package com.example.eni_parking.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eni_parking.bo.Manager;

import java.util.List;

@Dao
public interface ManagerDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    public void insertManager(Manager manager);

    @Update
    public void updateManager(Manager manager);

    @Delete
    public void deleteManager(Manager manager);

    @Query("SELECT * FROM manager")
    public Manager[] loadAllManager();

    @Query("SELECT * FROM manager WHERE id = :id")
    public Manager findManagerWithId(Integer id);

    @Query("SELECT * FROM manager WHERE mail = :mail AND password = :password AND agencyID = :agencyID")
    public Manager findByMailAndPass(String mail, String password, int agencyID);
}
