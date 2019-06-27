package fr.eni.lokacar.bo;

public class Vehicle {

    private String plate;
    private String brand;
    private String model;
    private String color;
    private Integer km;
    private double dailyCost;

    public Vehicle(String plate, String brand, String model, String color, Integer km, double dailyCost) {
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.km = km;
        this.dailyCost = dailyCost;
    }

    public String getPlate() { return plate; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getColor() { return color; }
    public Integer getKm() { return km; }
    public double getDailyCost() { return dailyCost; }

    public void setPlate(String plate) { this.plate = plate; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setColor(String color) { this.color = color; }
    public void setKm(Integer km) { this.km = km; }
    public void setDailyCost(double dailyCost) { this.dailyCost = dailyCost; }
}
