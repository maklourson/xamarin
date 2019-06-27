package fr.eni.lokacar.services;

import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.lokacar.bo.Customer;
import fr.eni.lokacar.bo.Rental;
import fr.eni.lokacar.bo.Vehicle;
import fr.eni.lokacar.db.RentalsMemory;
import fr.eni.lokacar.db.VehiclesMemory;

public class VehiclesService {

    private static VehiclesService SINGLETON;

    private RentalsMemory rentalsMemory;
    private VehiclesMemory vehiclesMemory;

    public VehiclesService() {
        this.rentalsMemory = RentalsMemory.getInstance();
        this.vehiclesMemory = VehiclesMemory.getInstance();
    }

    public static VehiclesService getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new VehiclesService();
        }
        return SINGLETON;
    }

    public List<Vehicle> getVehicles() {
        return vehiclesMemory.getVehicles();
    }

    public Vehicle getVehicle(String plate) {
        List<Vehicle> vehicles = vehiclesMemory.getVehicles();
        for (Vehicle vehicle: vehicles) {
            if (vehicle.getPlate().equalsIgnoreCase(plate)) {
                return vehicle;
            }
        }
        return null;
    }

    public void addVehicle(Vehicle vehicle) { vehiclesMemory.addVehicle(vehicle); }
    public void deleteVehicle(String plate) {
        vehiclesMemory.removeVehicle(plate);
    }

    public List<Rental> getRentals() {
        return rentalsMemory.getRentals();
    }
    public int getRentalsSize() { return rentalsMemory.getRentals().size(); }

    public Rental getRental(int id) {
        List<Rental> rentals = rentalsMemory.getRentals();
        for (Rental rental: rentals) {
            if (rental.getId() == id) {
                return rental;
            }
        }
        return null;
    }

    public Rental getOngoingRental(String plate) {
        for (Rental rental: rentalsMemory.getRentals()) {
            if (rental.getPlate().equalsIgnoreCase(plate) && !rental.hasEnded()) {
                return rental;
            }
        }
        return null;
    }

    public Boolean isAvailable(String plate) {
        for (Rental rental: rentalsMemory.getRentals()) {
            if (rental.getPlate().equalsIgnoreCase(plate) && !rental.hasEnded()) {
                return false;
            }
        }
        return true;
    }

    public String getCurrentCost(String plate) {
        Vehicle vehicle = this.getVehicle(plate);
        for (Rental rental: rentalsMemory.getRentals()) {
            if (rental.getPlate().equalsIgnoreCase(plate) && !rental.hasEnded()) {
                double rentalTime = new Date().getTime() - rental.getStartDate().getTime();
                return toCurrencyDisplay(vehicle.getDailyCost() * Math.ceil(rentalTime / (1000 * 60 * 60 * 24)));
            }
        }
        return toCurrencyDisplay(0);
    }

    public String getFinalCost(int id) {
        Rental rental = this.getRental(id);
        Vehicle vehicle = this.getVehicle(rental.getPlate());
        double rentalTime = rental.getEndDate().getTime() - rental.getStartDate().getTime();
        return toCurrencyDisplay(vehicle.getDailyCost() * Math.ceil(rentalTime / (1000 * 60 * 60 * 24)));
    }

    public String getTurnover() {
        double turnover = 0.0;
        for (Rental rental: rentalsMemory.getRentals()) {
            if (rental.hasEnded()) {
                Vehicle vehicle = this.getVehicle(rental.getPlate());
                double rentalTime = new Date().getTime() - rental.getStartDate().getTime();
                turnover += vehicle.getDailyCost() * Math.ceil(rentalTime / (1000 * 60 * 60 * 24));
            }
        }
        return this.toCurrencyDisplay(turnover);
    }

    private String toCurrencyDisplay(double n) {
        int a = (int) Math.floor(n);
        int b = (int) Math.floor((n - a) * 100);
        if (String.valueOf(b).length() == 1) {
            return a + "." + b + "0";
        } else {
            return a + "." + b;
        }
    }

    public void startRental(String plate, Customer customer, File[] pictures) {
        List<String> filePaths = new ArrayList();
        for (int i = 0; i < pictures.length; i++) {
            if (pictures[i] != null) {
                filePaths.add(pictures[i].getAbsolutePath());
            }
        }
        rentalsMemory.startRental(plate, customer, filePaths);
    }

    public void endRental(Rental rental, File[] pictures) {
        List<String> filePaths = new ArrayList<>();
        for (int i = 0; i < pictures.length; i++) {
            if (pictures[i] != null) {
                filePaths.add(pictures[i].getAbsolutePath());
            }
        }
        rentalsMemory.endRental(rental.getId(), filePaths);
    }
}
