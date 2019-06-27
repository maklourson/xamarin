package com.example.eni_parking;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.eni_parking.activity.HomeActivity;
import com.example.eni_parking.bo.Agency;
import com.example.eni_parking.bo.CarType;
import com.example.eni_parking.bo.Manager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] PERMISSIONS = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (
                (ContextCompat.checkSelfPermission(this, PERMISSIONS[0]) != PackageManager.PERMISSION_GRANTED) ||
                        (ContextCompat.checkSelfPermission(this, PERMISSIONS[1]) != PackageManager.PERMISSION_GRANTED) ||
                            (ContextCompat.checkSelfPermission(this, PERMISSIONS[2]) != PackageManager.PERMISSION_GRANTED)
        )
        {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }
        setContentView(R.layout.activity_main);

        if(AppDatabase.getAppDatabase(this).agencyDao().loadAllAgencies().length == 0){
            createData();
        }

        Spinner dropdownAgency = ((Spinner) findViewById(R.id.dpAgency));

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, AppDatabase.getAppDatabase(this).agencyDao().loadAllAgencies());

        dropdownAgency.setAdapter(spinnerArrayAdapter);

        Button btnLogin = findViewById(R.id.btnLogin);

        final MainActivity context = this;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = ((EditText) findViewById(R.id.txtLogin)).getText().toString();
                String password = ((EditText) findViewById(R.id.txtPassword)).getText().toString();
                Spinner dropdownAgency = ((Spinner) findViewById(R.id.dpAgency));
                Agency agency = (Agency)dropdownAgency.getSelectedItem();

                if (TextUtils.isEmpty(login) || TextUtils.isEmpty(password)){
                    Toast.makeText(context,"Veuillez renseigner tous les champs", Toast.LENGTH_LONG).show();
                }
                else{
                    Manager user = AppDatabase.getAppDatabase(context).managerDao().findByMailAndPass(login, password, agency.getId());

                    if(user==null){
                        Toast.makeText(context,"L'utilisateur n'est pas connu, réessayer de vous reconnecter", Toast.LENGTH_LONG).show();
                    }
                    else{
                        // IF VALID
                        Intent intent = new Intent(context, HomeActivity.class);
                        intent.putExtra("AgencyID", agency.getId());
                        intent.putExtra("UserID", user.getId());
                        context.startActivityForResult(intent,1);
                        context.finish();
                    }
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ENI_PARKING_USER", MODE_PRIVATE);
        int UserID = Integer.parseInt(sharedPreferences.getString("UserID", "-1"));

        if(UserID > 0){
            Intent intent = new Intent(this, HomeActivity.class);
            this.startActivityForResult(intent,2);
            this.finish();
        }
    }

    private void createData(){
        Agency agency1 = new Agency();
        agency1.setName("Agence 1");
        agency1.setPhone("0606060606");
        agency1.setAddress("");
        AppDatabase.getAppDatabase(this).agencyDao().insertAgency(agency1);
        Agency agency2 = new Agency();
        agency2.setName("Agence 2");
        agency2.setPhone("0606060606");
        agency2.setAddress("");
        AppDatabase.getAppDatabase(this).agencyDao().insertAgency(agency2);

        Manager manager1 = new Manager();
        manager1.setAgencyID(1);
        manager1.setFirstname("Jean");
        manager1.setLastname("René");
        manager1.setMail("manager1@gmail.com");
        manager1.setPassword("123");
        AppDatabase.getAppDatabase(this).managerDao().insertManager(manager1);
        Manager manager2 = new Manager();
        manager2.setAgencyID(2);
        manager2.setFirstname("Pierre");
        manager2.setLastname("Marie");
        manager2.setMail("manager2@gmail.com");
        manager2.setPassword("123");
        AppDatabase.getAppDatabase(this).managerDao().insertManager(manager2);

        CarType carType1 = new CarType();
        carType1.setType("Break");
        AppDatabase.getAppDatabase(this).carTypeDao().insertCarType(carType1);
        CarType carType2 = new CarType();
        carType2.setType("Berline");
        AppDatabase.getAppDatabase(this).carTypeDao().insertCarType(carType2);
        CarType carType3 = new CarType();
        carType3.setType("Citadine");
        AppDatabase.getAppDatabase(this).carTypeDao().insertCarType(carType3);
        CarType carType4 = new CarType();
        carType4.setType("Monospace");
        AppDatabase.getAppDatabase(this).carTypeDao().insertCarType(carType4);
        CarType carType5 = new CarType();
        carType5.setType("4x4");
        AppDatabase.getAppDatabase(this).carTypeDao().insertCarType(carType5);
        CarType carType6 = new CarType();
        carType6.setType("Crossover");
        AppDatabase.getAppDatabase(this).carTypeDao().insertCarType(carType6);
        CarType carType7 = new CarType();
        carType7.setType("Utilitaire");
        AppDatabase.getAppDatabase(this).carTypeDao().insertCarType(carType7);
    }
}
