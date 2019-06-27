package com.example.eni_parking.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.eni_parking.MainActivity;
import com.example.eni_parking.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        int AgencyID = getIntent().getIntExtra("AgencyID", -1);
        int UserID = getIntent().getIntExtra("UserID", -1);
        if(AgencyID > 0 && UserID > 0){
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ENI_PARKING_USER", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("AgencyID", String.valueOf(AgencyID));
            editor.putString("UserID", String.valueOf(UserID));
            editor.commit();
        }

        Button btnFormCar = findViewById(R.id.btnFormCar);
        Button btnFormCarType = findViewById(R.id.btnFormCarType);
        Button btnList = findViewById(R.id.btnList);
        Button btnSearch = findViewById(R.id.btnSearch);

        final HomeActivity context = this;
        btnFormCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,FormManageCarActivity.class);
                intent.putExtra("CAR_ID", -1);
                context.startActivityForResult(intent,2);
            }
        });
        btnFormCarType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FormManageCarTypeActivity.class);
                intent.putExtra("CARTYPE_ID", -1);
                context.startActivityForResult(intent,2);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListCarActivity.class);
                context.startActivityForResult(intent,2);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchActivity.class);
                context.startActivityForResult(intent,2);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ENI_PARKING_USER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("AgencyID", "-1");
        editor.putString("UserID", "-1");
        editor.commit();
    }
}
