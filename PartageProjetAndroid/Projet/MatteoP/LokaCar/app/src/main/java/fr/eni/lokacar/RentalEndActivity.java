package fr.eni.lokacar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import fr.eni.lokacar.bo.Customer;
import fr.eni.lokacar.bo.Rental;
import fr.eni.lokacar.bo.Vehicle;
import fr.eni.lokacar.services.VehiclesService;

public class RentalEndActivity extends AppCompatActivity {

    private VehiclesService vehiclesService = VehiclesService.getInstance();

    private Vehicle vehicle;
    private Rental rental;
    private Customer customer;
    private File[] pictureFiles = new File[4];
    private byte[][] pictureBytes = new byte[4][];
    private int nbPictures = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_end);

        Intent intent = this.getIntent();
        vehicle = vehiclesService.getVehicle(intent.getStringExtra("plate"));
        rental = vehiclesService.getOngoingRental(vehicle.getPlate());
        customer = rental.getCustomer();

        TextView vehicleBrand = (TextView) findViewById(R.id.end_rental_vehicle_brand);
        TextView vehicleModel = (TextView) findViewById(R.id.end_rental_vehicle_model);
        TextView vehiclePlate = (TextView) findViewById(R.id.end_rental_vehicle_plate);
        TextView vehicleDailyCost = (TextView) findViewById(R.id.end_rental_vehicle_dailyCost);
        TextView customerName = (TextView) findViewById(R.id.end_rental_customer_name_TextView);
        TextView rentalDuration = (TextView) findViewById(R.id.end_rental_duration_TextView);
        TextView rentalDailyCost = (TextView) findViewById(R.id.end_rental_dailyCost_TextView);
        TextView rentalTotalCost = (TextView) findViewById(R.id.end_rental_totalCost_TextView);

        vehicleBrand.setText(vehicle.getBrand());
        vehicleModel.setText(vehicle.getModel());
        vehiclePlate.setText(vehicle.getPlate());
        vehicleDailyCost.setText(String.valueOf(vehicle.getDailyCost()) + " €/Jour");
        customerName.setText(customer.getFullName());
        rentalDuration.setText(getString(R.string.end_rental_duration) + ": " + rental.getDuration() + " jour(s)");
        rentalDailyCost.setText(getString(R.string.end_rental_dailyCost) + ": " + vehicle.getDailyCost() + "€/Jour");
        rentalTotalCost.setText(getString(R.string.end_rental_totalCost) + ": " + vehiclesService.getCurrentCost(vehicle.getPlate()) + "€");
    }

    public void toDetails(View view) {
        this.finish();
    }

    public void endRental(View view) {
        vehiclesService.endRental(rental, pictureFiles);
        Intent sendSms = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + customer.getPhoneNumber()));
        StringBuilder smsBody = new StringBuilder();
        smsBody.append(getString(R.string.end_rental_sms_greeting) + " " + customer.getFullName());
        smsBody.append("\n\n" + getString(R.string.end_rental_sms_body) + ":");
        smsBody.append("\n - " + getString(R.string.end_rental_sms_vehicle) + ": " + vehicle.getBrand() + " " + vehicle.getModel());
        smsBody.append("\n - " + getString(R.string.end_rental_sms_duration) + ": " + rental.getDuration() + " jour(s)");
        smsBody.append("\n - " + getString(R.string.end_rental_sms_totalCost) + ": " + vehiclesService.getFinalCost(rental.getId()) + "€");
        smsBody.append("\n\n" + getString(R.string.end_rental_sms_bye));
        sendSms.putExtra("sms_body", smsBody.toString());
        startActivity(sendSms);
        this.finish();
    }
}
