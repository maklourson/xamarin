package fr.eni.lokacar.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Rental {

    private int id;
    private String plate;
    private Customer customer;
    private Date startDate;
    private Date endDate;
    private List<String> preRentalPictures;
    private List<String> postRentalPictures;

    public Rental(String plate, Customer customer, Date startDate, List<String> filePaths) {
        this.plate = plate;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = null;
        this.preRentalPictures = filePaths;
        this.postRentalPictures = new ArrayList<>();
    }

    public Rental(String plate, Customer customer, Date startDate, Date endDate, List<String> filePaths) {
        this.plate = plate;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.preRentalPictures = filePaths;
        this.postRentalPictures = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getPlate() { return plate; }
    public Customer getCustomer() { return customer; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public List<String> getPreRentalPictures() { return preRentalPictures; }
    public List<String> getPostRentalPictures() { return postRentalPictures; }

    public void setId(int id) { this.id = id; }
    public void setPlate(String plate) { this.plate = plate; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public void addPostRentalPicture(String filePath) {
        if (!postRentalPictures.contains(filePath)) {
            postRentalPictures.add(filePath);
        }
    }

    public void removePostRentalPicture(String filePath) {
        if (postRentalPictures.contains(filePath)) {
            postRentalPictures.remove(filePath);
        }
    }

    public int getDuration() {
        double rentalTime = new Date().getTime() - this.startDate.getTime();
        return (int) Math.ceil(rentalTime / (1000 * 60 * 60 * 24));
    }
    public Boolean hasEnded() {
        return this.getEndDate() != null;
    }
}
