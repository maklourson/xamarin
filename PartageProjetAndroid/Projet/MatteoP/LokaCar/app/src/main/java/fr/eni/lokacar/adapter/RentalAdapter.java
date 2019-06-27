package fr.eni.lokacar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import fr.eni.lokacar.R;
import fr.eni.lokacar.RentalDetailsActivity;
import fr.eni.lokacar.bo.Customer;
import fr.eni.lokacar.bo.Rental;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.ViewHolder> {

    private Context context;
    private List<Rental> rentals;
    private int recyclerItemRes;

    public RentalAdapter(Context context, List<Rental> rentals, int recyclerItemRes) {
        this.context = context;
        this.rentals = rentals;
        this.recyclerItemRes = recyclerItemRes;
    }

    @NonNull
    @Override
    public RentalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(recyclerItemRes, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RentalAdapter.ViewHolder viewHolder, int position) {
        final Rental rental = rentals.get(position);
        final Customer customer = rental.getCustomer();

        viewHolder.plate.setText(rental.getPlate());
        viewHolder.customerName.setText(customer.getFullName());
        viewHolder.gotoDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, RentalDetailsActivity.class);
                i.putExtra("rentalId", rental.getId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (rentals != null) {
            return rentals.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView plate;
        private TextView customerName;
        private Button gotoDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            plate = (TextView) itemView.findViewById(R.id.rentals_history_item_plate);
            customerName = (TextView) itemView.findViewById(R.id.rentals_history_item_customerName);
            gotoDetails = (Button) itemView.findViewById(R.id.rentals_history_item_goToDetails);
        }
    }
}
