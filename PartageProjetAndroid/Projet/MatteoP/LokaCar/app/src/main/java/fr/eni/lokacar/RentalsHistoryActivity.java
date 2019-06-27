package fr.eni.lokacar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import fr.eni.lokacar.adapter.RentalAdapter;
import fr.eni.lokacar.bo.Rental;
import fr.eni.lokacar.services.VehiclesService;

public class RentalsHistoryActivity extends AppCompatActivity {

    private VehiclesService vehiclesService = VehiclesService.getInstance();
    private RecyclerView ongoingRecyclerView;
    private RecyclerView endedRecyclerView;
    private RentalAdapter ongoingRentalAdapter;
    private RentalAdapter endedRentalAdapter;


    private List<Rental> ongoingRentals;
    private List<Rental> endedRentals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentals_history);
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Rental> rentals = vehiclesService.getRentals();
        ongoingRentals = new ArrayList<>();
        endedRentals = new ArrayList<>();

        for (Rental rental: rentals) {
            if (rental.hasEnded()) {
                endedRentals.add(rental);
            } else {
                ongoingRentals.add(rental);
            }
        }

        if (ongoingRentals.size() == 0) {
            findViewById(R.id.rentals_history_ongoing_header_Layout).setVisibility(View.GONE);
            findViewById(R.id.rentals_history_ongoing_recylerView).setVisibility(View.GONE);
            findViewById(R.id.rentals_history_none_Layout).setVisibility(View.GONE);
        }
        if (endedRentals.size() == 0) {
            findViewById(R.id.rentals_history_ended_header_Layout).setVisibility(View.GONE);
            findViewById(R.id.rentals_history_ended_recylerView).setVisibility(View.GONE);
            findViewById(R.id.rentals_history_none_Layout).setVisibility(View.GONE);
        }
        if (ongoingRentals.size() == 0 && endedRentals.size() == 0) {
            findViewById(R.id.rentals_history_none_Layout).setVisibility(View.VISIBLE);
            findViewById(R.id.rentals_history_ongoing_header_Layout).setVisibility(View.GONE);
            findViewById(R.id.rentals_history_ongoing_recylerView).setVisibility(View.GONE);
            findViewById(R.id.rentals_history_ended_header_Layout).setVisibility(View.GONE);
            findViewById(R.id.rentals_history_ended_recylerView).setVisibility(View.GONE);
        }

        ongoingRecyclerView = findViewById(R.id.rentals_history_ongoing_recylerView);
        LinearLayoutManager ongoingLayoutManager = new LinearLayoutManager(this);
        ongoingRecyclerView.setLayoutManager(ongoingLayoutManager);

        endedRecyclerView = findViewById(R.id.rentals_history_ended_recylerView);
        LinearLayoutManager endedLayoutManager = new LinearLayoutManager(this);
        endedRecyclerView.setLayoutManager(endedLayoutManager);

        ongoingRentalAdapter = new RentalAdapter(this, ongoingRentals, R.layout.activity_rentals_history_item);
        endedRentalAdapter = new RentalAdapter(this, endedRentals, R.layout.activity_rentals_history_item);
        ongoingRecyclerView.setAdapter(ongoingRentalAdapter);
        endedRecyclerView.setAdapter(endedRentalAdapter);
    }
}
