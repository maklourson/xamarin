package fr.eni.lokacar.db;
/*
import android.app.Activity;
import android.content.Context;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
*/
import java.util.ArrayList;
import java.util.List;

import fr.eni.lokacar.bo.Vehicle;

public class VehiclesMemory {

    //private static String STORAGE_NAME = "vehicles_storage.json";
    private static List<Vehicle> vehicles;
    private static VehiclesMemory SINGLETON;

    public VehiclesMemory() {
        vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("AA-001-AA", "Citroën", "Saxo", "Vert", 10000, 30.0));
        vehicles.add(new Vehicle("BB-020-BB", "Citroën", "C3", "Gris", 55000, 40.0));
        vehicles.add(new Vehicle("CC-300-CC", "Renault", "Kangoo", "Blanc", 115000, 35.0));
        vehicles.add(new Vehicle("DD-440-DD", "Renault", "Kangoo", "Blanc", 155000, 35.0));
        vehicles.add(new Vehicle("EE-055-EE", "Citroën", "C5", "Noir", 50000, 65.0));
        vehicles.add(new Vehicle("AB-002-AB", "Citroën", "Saxo", "Vert", 10000, 30.0));
        vehicles.add(new Vehicle("BC-021-BC", "Citroën", "C3", "Gris", 55000, 40.0));
        vehicles.add(new Vehicle("CD-301-CD", "Renault", "Kangoo", "Blanc", 115000, 35.0));
        vehicles.add(new Vehicle("DE-441-DE", "Renault", "Kangoo", "Blanc", 155000, 35.0));
        vehicles.add(new Vehicle("EF-056-EF", "Citroën", "C5", "Noir", 50000, 65.0));
    }

    public static VehiclesMemory getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new VehiclesMemory();
        }
        return SINGLETON;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(String plate) {
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            if (vehicle.getPlate().equalsIgnoreCase(plate)) {
                vehicles.remove(i);
                return;
            }
        }
    }

    /*public List<Vehicle> getVehicles() {
        FileInputStream fos;
        try {
            fos = new FileInputStream(STORAGE_NAME);
            fos.

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }

        return null;
    }*/

    /*private JSONObject getJSONVehicles() {
        FileInputStream fos;
        try {
            fos = new FileInputStream(STORAGE_NAME);
            fos

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }
    }*/
}
