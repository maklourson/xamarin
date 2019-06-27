package fr.eni.lokacar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import fr.eni.lokacar.adapter.VehicleAdapter;
import fr.eni.lokacar.bo.Vehicle;
import fr.eni.lokacar.services.VehiclesService;

public class VehiclesListActivity extends AppCompatActivity {

    private VehiclesService vehiclesService = new VehiclesService();
    private RecyclerView recyclerView;
    private VehicleAdapter vehicleAdapter;

    private List<Vehicle> vehicles;
    private HashMap<Vehicle, Boolean> vehiclesAvailability;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicles_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        vehicles = vehiclesService.getVehicles();
        vehiclesAvailability = new HashMap<>();
        for (Vehicle vehicle: vehicles) {
            vehiclesAvailability.put(vehicle, vehiclesService.isAvailable(vehicle.getPlate()));
        }

        recyclerView = findViewById(R.id.vehicles_list_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        vehicleAdapter = new VehicleAdapter(this, vehicles, vehiclesAvailability, R.layout.activity_vehicles_list_item);
        recyclerView.setAdapter(vehicleAdapter);
    }

    public void toAddVehicle(View view) {
        Intent i = new Intent(VehiclesListActivity.this, VehicleAddActivity.class);
        startActivity(i);
    }
}
