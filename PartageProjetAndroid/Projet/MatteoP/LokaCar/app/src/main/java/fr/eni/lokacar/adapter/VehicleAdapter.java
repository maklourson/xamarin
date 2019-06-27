package fr.eni.lokacar.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import fr.eni.lokacar.R;
import fr.eni.lokacar.VehicleDetailsActivity;
import fr.eni.lokacar.bo.Vehicle;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    private static int availableColor = R.color.availableVehicle;
    private static int unavailableColor = R.color.unavailableVehicle;

    private Context context;
    private List<Vehicle> vehicles;
    private HashMap<Vehicle, Boolean> vehiclesAvailability;
    private int recyclerItemRes;

    public VehicleAdapter(Context context, List<Vehicle> vehicles, HashMap<Vehicle, Boolean> vehiclesAvailability, int recyclerItemRes) {
        this.context = context;
        this.vehicles = vehicles;
        this.vehiclesAvailability = vehiclesAvailability;
        this.recyclerItemRes = recyclerItemRes;
    }

    @NonNull
    @Override
    public VehicleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(recyclerItemRes, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VehicleAdapter.ViewHolder viewHolder, int position) {
        final Vehicle vehicle = vehicles.get(position);
        Boolean isAvailable = vehiclesAvailability.get(vehicle);

        if (isAvailable) {
            viewHolder.vehicleAvailability.setBackgroundResource(availableColor);
        } else {
            viewHolder.vehicleAvailability.setBackgroundResource(unavailableColor);
        }
        viewHolder.vehicleBrand.setText(vehicle.getBrand());
        viewHolder.vehicleModel.setText(vehicle.getModel());
        viewHolder.vehiclePlate.setText(vehicle.getPlate());
        viewHolder.vehicleDailyCost.setText(vehicle.getDailyCost() + " €/j");
        viewHolder.gotoDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VehicleDetailsActivity.class);
                i.putExtra("plate", vehicle.getPlate());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (vehicles != null) {
            return vehicles.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout vehicleAvailability;
        private TextView vehicleBrand;
        private TextView vehicleModel;
        private TextView vehiclePlate;
        private TextView vehicleDailyCost;
        private Button gotoDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            vehicleAvailability = (FrameLayout) itemView.findViewById(R.id.vehicles_list_item_available);
            vehicleBrand = (TextView) itemView.findViewById(R.id.vehicles_list_item_brand);
            vehicleModel = (TextView) itemView.findViewById(R.id.vehicles_list_item_model);
            vehiclePlate = (TextView) itemView.findViewById(R.id.vehicles_list_item_plate);
            vehicleDailyCost = (TextView) itemView.findViewById(R.id.vehicles_list_item_dailyCost);
            gotoDetails = (Button) itemView.findViewById(R.id.vehicles_list_item_goToDetails);
        }
    }
}
