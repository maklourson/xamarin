package fr.eni.lokacar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import fr.eni.lokacar.bo.Customer;
import fr.eni.lokacar.bo.Rental;
import fr.eni.lokacar.bo.Vehicle;
import fr.eni.lokacar.services.VehiclesService;

public class RentalDetailsActivity extends AppCompatActivity {

    private VehiclesService vehiclesService = VehiclesService.getInstance();

    private Vehicle vehicle;
    private Rental rental;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_details);

        Intent intent = this.getIntent();
        rental = vehiclesService.getRental(intent.getIntExtra("rentalId", 0));
        vehicle = vehiclesService.getVehicle(rental.getPlate());
        customer = rental.getCustomer();

        TextView vehicleBrand = (TextView) findViewById(R.id.end_rental_vehicle_brand);
        TextView vehicleModel = (TextView) findViewById(R.id.end_rental_vehicle_model);
        TextView vehiclePlate = (TextView) findViewById(R.id.end_rental_vehicle_plate);
        TextView vehicleDailyCost = (TextView) findViewById(R.id.end_rental_vehicle_dailyCost);
        TextView customerName = (TextView) findViewById(R.id.end_rental_customer_name_TextView);
        TextView customerAddress = (TextView) findViewById(R.id.end_rental_customer_address_TextView);
        TextView customerPhone = (TextView) findViewById(R.id.end_rental_customer_phone_TextView);
        TextView rentalDuration = (TextView) findViewById(R.id.end_rental_duration_TextView);
        TextView rentalDailyCost = (TextView) findViewById(R.id.end_rental_dailyCost_TextView);
        TextView rentalTotalCost = (TextView) findViewById(R.id.end_rental_totalCost_TextView);

        vehicleBrand.setText(vehicle.getBrand());
        vehicleModel.setText(vehicle.getModel());
        vehiclePlate.setText(vehicle.getPlate());
        vehicleDailyCost.setText(String.valueOf(vehicle.getDailyCost()) + " €/Jour");
        customerName.setText(customer.getFullName());
        customerAddress.setText(customer.getAddress());
        customerPhone.setText(customer.getPhoneNumber());
        rentalDuration.setText(getString(R.string.end_rental_duration) + ": " + rental.getDuration() + " jour(s)");
        rentalDailyCost.setText(getString(R.string.end_rental_dailyCost) + ": " + vehicle.getDailyCost() + "€/Jour");
        rentalTotalCost.setText(getString(R.string.end_rental_totalCost) + ": " + vehiclesService.getCurrentCost(vehicle.getPlate()) + "€");
    }
}
