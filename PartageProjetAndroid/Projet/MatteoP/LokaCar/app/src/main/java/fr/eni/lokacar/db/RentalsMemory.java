package fr.eni.lokacar.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.lokacar.bo.Customer;
import fr.eni.lokacar.bo.Rental;

public class RentalsMemory {

    private static RentalsMemory SINGLETON;
    private List<Rental> rentals;

    public RentalsMemory() {
        rentals = new ArrayList<>();
    }

    public static RentalsMemory getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new RentalsMemory();
        }
        return SINGLETON;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void startRental (String plate, Customer customer, List<String> filePaths) {
        Rental rental = new Rental(plate, customer, new Date(), filePaths);
        rental.setId(rentals.size());
        rentals.add(rental);
    }

    public void endRental (int id, List<String> filePaths) {
        for (Rental rental: rentals) {
            if (rental.getId() == id) {
                for (String filePath: filePaths) {
                    rental.addPostRentalPicture(filePath);
                }
                rental.setEndDate(new Date());
            }
        }
    }
}
