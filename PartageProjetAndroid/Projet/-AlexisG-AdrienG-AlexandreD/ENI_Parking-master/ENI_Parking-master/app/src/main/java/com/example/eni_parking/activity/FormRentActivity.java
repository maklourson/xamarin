package com.example.eni_parking.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eni_parking.AppDatabase;
import com.example.eni_parking.R;
import com.example.eni_parking.bo.Car;
import com.example.eni_parking.bo.Customer;
import com.example.eni_parking.bo.Rental;

import java.sql.Timestamp;

public class FormRentActivity extends AppCompatActivity {

    private Rental rental;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_rent_car);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button editButton = ((Button) findViewById(R.id.ValidateButton));

        final int car_id = getIntent().getIntExtra("CAR_ID", -1);

        final long  timeStampToday = new Timestamp(System.currentTimeMillis()).getTime();

        rental = AppDatabase.getAppDatabase(this).rentalDao().findRendalWithDate(timeStampToday, car_id);

        if(rental!=null){
            Customer customer = AppDatabase.getAppDatabase(this).customerDao().findCustomerWithId(rental.getCustomer_id());

            EditText firstnametxt = ((EditText) findViewById(R.id.firstname));
            firstnametxt.setText(customer.getFirstname());
            firstnametxt.setEnabled(false);
            EditText lastnametxt = ((EditText) findViewById(R.id.lastname));
            lastnametxt.setText(customer.getLastname());
            lastnametxt.setEnabled(false);
            editButton.setText("Finir la location");
        }


        final FormRentActivity context = this;
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customer_firstname = ((EditText) findViewById(R.id.firstname)).getText().toString();
                String customer_lastname = ((EditText) findViewById(R.id.lastname)).getText().toString();

                if(customer_firstname.length() == 0 || customer_lastname.length() == 0){
                    Toast.makeText(context,"Veuillez saisir tous les champs", Toast.LENGTH_LONG).show();
                }
                else{
                    if(context.rental!=null){
                        // Update rental
                        context.rental.setDateEnd(timeStampToday);
                        AppDatabase.getAppDatabase(context).rentalDao().update(context.rental);

                        Car car = AppDatabase.getAppDatabase(context).carDao().findCarWithId(car_id);
                        car.setIsBooked(0);
                        AppDatabase.getAppDatabase(context).carDao().updateCar(car);
                    }
                    else{
                        Customer customer = AppDatabase.getAppDatabase(context).customerDao().findCustomerWithFirstnameLastname(customer_firstname, customer_lastname);

                        if(customer==null) {
                            customer = new Customer();
                            customer.setLastname(customer_lastname);
                            customer.setFirstname(customer_firstname);

                            AppDatabase.getAppDatabase(context).customerDao().insertCustomer(customer);
                        }
                        customer = AppDatabase.getAppDatabase(context).customerDao().findCustomerWithFirstnameLastname(customer_firstname, customer_lastname);
                        // Create rental
                        context.rental = new Rental();
                        context.rental.setCar_id(car_id);
                        context.rental.setCustomer_id(customer.getId());
                        context.rental.setDateBegin(timeStampToday);
                        AppDatabase.getAppDatabase(context).rentalDao().insert(context.rental);

                        Car car = AppDatabase.getAppDatabase(context).carDao().findCarWithId(car_id);
                        car.setIsBooked(1);
                        AppDatabase.getAppDatabase(context).carDao().updateCar(car);
                    }
                    context.finish();
                }
            }
        });
    }
}
