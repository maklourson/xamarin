package com.example.eni_parking.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eni_parking.bo.Agency;
import com.example.eni_parking.bo.Customer;

import java.util.List;

@Dao
public interface AgencyDao {
    @Insert(onConflict= OnConflictStrategy.REPLACE)
    public void insertAgency(Agency agency);

    @Update
    public void updateAgency(Agency agency);

    @Delete
    public void deleteAgency(Agency agency);

    @Query("SELECT * FROM agency")
    public Agency[] loadAllAgencies();

    @Query("SELECT * FROM agency WHERE id = :id")
    public List<Agency> findAgencyWithId(Integer id);
}
