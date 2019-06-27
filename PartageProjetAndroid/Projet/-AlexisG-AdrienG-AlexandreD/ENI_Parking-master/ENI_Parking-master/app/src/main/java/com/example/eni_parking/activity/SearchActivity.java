package com.example.eni_parking.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.eni_parking.AppDatabase;
import com.example.eni_parking.R;
import com.example.eni_parking.bo.Car;
import com.example.eni_parking.bo.CarType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private AppDatabase db;
    private Button button;
    private Spinner spinner;
    private List<Car> lstCar;
    private int agencyID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ENI_PARKING_USER", MODE_PRIVATE);
        agencyID = Integer.parseInt(sharedPreferences.getString("AgencyID", "-1"));

        db = AppDatabase.getAppDatabase(this);

        button = findViewById(R.id.button);
        spinner = findViewById(R.id.dpCarType);

        ArrayAdapter<CarType> spinnerArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, AppDatabase.getAppDatabase(this).carTypeDao().loadAllCarType());

        spinner.setAdapter(spinnerArrayAdapter);

        lstCar = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartSearch();
            }
        });
    }

    private void StartSearch(){

        CarType carType = (CarType)spinner.getSelectedItem();

        lstCar.addAll(db.carDao().findCarByCarTypeAndAgency(carType.getId(), agencyID));

        if(lstCar != null && !lstCar.isEmpty()){
            Intent intent = new Intent(getApplicationContext(),ListCarActivity.class);
            intent.putExtra("listCarFromSearch", (Serializable) lstCar);
            startActivity(intent);
        }
    }
}
