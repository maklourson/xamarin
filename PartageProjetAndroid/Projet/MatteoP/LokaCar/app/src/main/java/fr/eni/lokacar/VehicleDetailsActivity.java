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

public class VehicleDetailsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_vehicle_details);

        Intent intent = this.getIntent();
        plate = intent.getStringExtra("plate");
    }

    @Override
    protected void onResume() {
        super.onResume();

        vehicle = vehiclesService.getVehicle(plate);
        isAvailable = vehiclesService.isAvailable(plate);
        updateView();
    }

    private void updateView() {
        TextView vehiclePlate = (TextView) findViewById(R.id.vehicle_details_plate_TextView2);
        TextView vehicleBrand = (TextView) findViewById(R.id.vehicle_details_brand_TextView2);
        TextView vehicleModel = (TextView) findViewById(R.id.vehicle_details_model_TextView2);
        TextView vehicleColor = (TextView) findViewById(R.id.vehicle_details_color_TextView2);
        TextView vehicleDailyCost = (TextView) findViewById(R.id.vehicle_details_dailyCost_TextView2);
        TextView vehicleMileage = (TextView) findViewById(R.id.vehicle_details_km_TextView2);

        vehiclePlate.setText(vehicle.getPlate());
        vehicleBrand.setText(vehicle.getBrand());
        vehicleModel.setText(vehicle.getModel());
        vehicleColor.setText(vehicle.getColor());
        vehicleDailyCost.setText(String.valueOf(vehicle.getDailyCost() + " €"));
        vehicleMileage.setText(String.valueOf(vehicle.getKm() + " km"));

        ConstraintLayout vehicleAvailabilityBackground = (ConstraintLayout) findViewById(R.id.vehicle_details_availability_Layout);
        TextView vehicleAvailability = (TextView) findViewById(R.id.vehicle_details_availability_TextView);
        ConstraintLayout vehicleIsAvailableLayout = (ConstraintLayout) findViewById(R.id.vehicle_details_isAvailable_Layout);
        ConstraintLayout vehicleIsUnavailableLayout = (ConstraintLayout) findViewById(R.id.vehicle_details_isUnavailable_Layout);
        TextView vehicleIsUnavailableCostTextView = (TextView) findViewById(R.id.vehicle_details_isUnavailable_cost_TextView);

        if (isAvailable) {
            vehicleAvailabilityBackground.setBackgroundResource(availableColor);
            vehicleAvailability.setText(availableText);
            vehicleIsAvailableLayout.setVisibility(View.VISIBLE);
            vehicleIsUnavailableLayout.setVisibility(View.INVISIBLE);
        } else {
            vehicleAvailabilityBackground.setBackgroundResource(unavailableColor);
            vehicleAvailability.setText(unavailableText);
            vehicleIsUnavailableCostTextView.setText(vehiclesService.getCurrentCost(plate));
            vehicleIsAvailableLayout.setVisibility(View.INVISIBLE);
            vehicleIsUnavailableLayout.setVisibility(View.VISIBLE);
        }
    }

    public void toRentalStart(View view) {
        Intent i = new Intent(VehicleDetailsActivity.this, RentalStartActivity.class);
        i.putExtra("plate", plate);
        this.startActivity(i);
    }

    public void endRental(View view) {
        Intent i = new Intent(VehicleDetailsActivity.this, RentalEndActivity.class);
        i.putExtra("plate", plate);
        this.startActivity(i);
    }

    public void deleteVehicle(View view) {
        vehiclesService.deleteVehicle(vehicle.getPlate());
        this.finish();
    }
}
