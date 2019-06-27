package com.example.eni_parking.bo;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "rental")
public class Rental {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(
            entity = Customer.class,
            parentColumns = "id",
            deferred = false,
            childColumns = "customer_id"
    )
    private int customer_id;
    @ForeignKey(
            entity = Car.class,
            parentColumns = "id",
            deferred = false,
            childColumns = "car_id"
    )

    @ColumnInfo(name="car_id")
    private int car_id;

    @ColumnInfo(name="date_begin")
    private long dateBegin;

    @ColumnInfo(name="date_end")
    private long dateEnd;

    @ColumnInfo(name="picture_before")
    private String pictureBefore;

    @ColumnInfo(name="picture_after")
    private String pictureAfter;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public long getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(long dateBegin) {
        this.dateBegin = dateBegin;
    }

    public long getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(long dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getPictureBefore() {
        return pictureBefore;
    }

    public void setPictureBefore(String pictureBefore) {
        this.pictureBefore = pictureBefore;
    }

    public String getPictureAfter() {
        return pictureAfter;
    }

    public void setPictureAfter(String pictureAfter) {
        this.pictureAfter = pictureAfter;
    }

    @Override
    public String toString() {
        return "RentalDao{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", car_id=" + car_id +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", pictureBefore='" + pictureBefore + '\'' +
                ", pictureAfter='" + pictureAfter + '\'' +
                '}';
    }
}
