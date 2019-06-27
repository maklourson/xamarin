package com.example.eni_parking.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eni_parking.AppDatabase;
import com.example.eni_parking.MainActivity;
import com.example.eni_parking.R;
import com.example.eni_parking.bo.CarType;

public class FormManageCarTypeActivity extends AppCompatActivity {

    private CarType carType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_manage_car_type);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button editButton = ((Button) findViewById(R.id.EditButtonCarType));

        int carType_id = getIntent().getIntExtra("CARTYPE_ID", -1);

        carType = AppDatabase.getAppDatabase(this).carTypeDao().findCarTypeWithId(carType_id);

        if(carType!=null){
            ((EditText) findViewById(R.id.txtType)).setText(carType.getType());

            editButton.setText("Modifier");
        }

        final FormManageCarTypeActivity context = this;
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add or Update in database
                String type = ((EditText) findViewById(R.id.txtType)).getText().toString();

                if(type.length() == 0){
                    Toast.makeText(context,"Veuillez saisir tous les champs", Toast.LENGTH_LONG).show();
                }
                else{
                    if(carType==null){
                        carType = new CarType();
                        carType.setType(type);

                        AppDatabase.getAppDatabase(context).carTypeDao().insertCarType(carType);
                    }
                    else{
                        carType.setType(type);

                        AppDatabase.getAppDatabase(context).carTypeDao().updateCarType(carType);
                    }

                    // redirect
                    context.finish();
                }
            }
        });
    }
}
