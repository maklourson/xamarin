package com.example.eni_parking.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eni_parking.AppDatabase;
import com.example.eni_parking.R;
import com.example.eni_parking.bo.Car;
import com.example.eni_parking.bo.CarType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormManageCarActivity extends AppCompatActivity {

    private Car car;
    private int AgencyID = -1;

    private static final int CAMERA_REQUEST = 123;
    private ImageView mImageView;

    // The filepath for the photo
    String mCurrentPhotoPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_manage_car);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("ENI_PARKING_USER", MODE_PRIVATE);
        AgencyID = Integer.parseInt(sharedPreferences.getString("AgencyID", "-1"));

        Spinner dropdownCarType = ((Spinner) findViewById(R.id.dpCarType));

        ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, AppDatabase.getAppDatabase(this).carTypeDao().loadAllCarType());

        dropdownCarType.setAdapter(spinnerArrayAdapter);

        Button button = findViewById(R.id.addPicButton);
        mImageView = findViewById(R.id.PictureDefault);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CAMERA_REQUEST){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

            String currentMillisse = String.valueOf(System.currentTimeMillis());

            File destination = new File(Environment.getExternalStorageDirectory(),
                    currentMillisse + ".jpg");
            mCurrentPhotoPath = destination.getParent() + "/" + currentMillisse + ".jpg";

            FileOutputStream fo;
            try {
                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mImageView.invalidate();
            mImageView.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
            mImageView.invalidate();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Button editButton = ((Button) findViewById(R.id.EditButtonCar));
        Button addPicture = findViewById(R.id.addPicButton);

        int car_id = getIntent().getIntExtra("CAR_ID", -1);

        car = AppDatabase.getAppDatabase(this).carDao().findCarWithId(car_id);

        if(car!=null){
            ((EditText) findViewById(R.id.txtRegistrationNumber)).setText(car.getRegistrationNumber());
            ((EditText) findViewById(R.id.txtPrice)).setText(String.valueOf(car.getPrice()));
            ((Spinner) findViewById(R.id.dpCarType)).setSelection(car.getCarType_id()-1);

            String pathPicture = car.getPicture();
            if (pathPicture.length() > 0){
                ((ImageView)findViewById(R.id.PictureDefault)).setImageBitmap(BitmapFactory.decodeFile(pathPicture));
                addPicture.setText("Modifier l'image");
            }
            editButton.setText("Modifier");
            ((TextView) findViewById(R.id.titleAddCar)).setText("Modifier une voiture");
        }


        final FormManageCarActivity context = this;
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add or Update in database
                String registrationNumber = ((EditText) findViewById(R.id.txtRegistrationNumber)).getText().toString();
                double price = Double.parseDouble(((EditText) findViewById(R.id.txtPrice)).getText().toString());
                Spinner dropdownCarType = ((Spinner) findViewById(R.id.dpCarType));
                CarType carType = (CarType)dropdownCarType.getSelectedItem();

                if(registrationNumber.length() == 0 || price == 0 || mCurrentPhotoPath.length() == 0){
                    Toast.makeText(context,"Veuillez saisir tous les champs", Toast.LENGTH_LONG).show();
                }
                else{
                    if(car==null){
                        car = new Car();
                        car.setRegistrationNumber(registrationNumber);
                        car.setPrice(price);
                        car.setCarType_id(carType.getId());
                        car.setPicture(mCurrentPhotoPath);
                        car.setAgency_id(AgencyID);

                        AppDatabase.getAppDatabase(context).carDao().insertCar(car);
                    }
                    else{
                        car.setRegistrationNumber(registrationNumber);
                        car.setPrice(price);
                        car.setCarType_id(carType.getId());
                        car.setPicture(mCurrentPhotoPath);

                        AppDatabase.getAppDatabase(context).carDao().updateCar(car);
                    }

                    // redirect
                    Intent intent = new Intent(context,ListCarActivity.class);
                    context.startActivityForResult(intent,2);
                    context.finish();
                }
            }
        });
    }

}
