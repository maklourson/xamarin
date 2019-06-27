package fr.eni.lokacar;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import fr.eni.lokacar.bo.Vehicle;
import fr.eni.lokacar.services.VehiclesService;

public class VehicleAddActivity extends AppCompatActivity {

    private VehiclesService vehiclesService = VehiclesService.getInstance();
    private final int availableColor = R.color.availableVehiclePale;
    private final int availableText = R.string.vehicle_details_available;
    private final int unavailableColor = R.color.unavailableVehiclePale;
    private final int unavailableText = R.string.vehicle_details_unavailable;

    private String plate;
    private Vehicle vehicle;
    private Boolean isAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_add);
    }

    public void toList(View view) {
        this.finish();
    }

    public void addVehicle(View view) {
        EditText vehiclePlate = (EditText) findViewById(R.id.vehicle_details_plate_EditText);
        EditText vehicleBrand = (EditText) findViewById(R.id.vehicle_details_brand_EditText);
        EditText vehicleModel = (EditText) findViewById(R.id.vehicle_details_model_EditText);
        EditText vehicleColor = (EditText) findViewById(R.id.vehicle_details_color_EditText);
        EditText vehicleDailyCost = (EditText) findViewById(R.id.vehicle_details_dailyCost_EditText);
        EditText vehicleMileage = (EditText) findViewById(R.id.vehicle_details_km_EditText);

        String plate = vehiclePlate.getText().toString().trim();
        String brand = vehicleBrand.getText().toString();
        String model = vehicleModel.getText().toString();
        String color = vehicleColor.getText().toString();
        double dailyCost = Double.parseDouble(vehicleDailyCost.getText().toString());
        int mileage = Integer.parseInt(vehicleMileage.getText().toString());

        Vehicle vehicle = new Vehicle(plate, brand, model, color, mileage, dailyCost);

        vehiclesService.addVehicle(vehicle);
        this.finish();
    }
}
