package fr.eni.lokacar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import fr.eni.lokacar.bo.Customer;
import fr.eni.lokacar.bo.Vehicle;
import fr.eni.lokacar.services.VehiclesService;

public class RentalStartActivity extends AppCompatActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private VehiclesService vehiclesService = VehiclesService.getInstance();
    private File[] pictureFiles = new File[4];
    private byte[][] pictureBytes = new byte[4][];
    private int nbPictures = 0;

    private Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_start);

        Intent intent = this.getIntent();
        vehicle = vehiclesService.getVehicle(intent.getStringExtra("plate"));

        TextView vehicleBrand = (TextView) findViewById(R.id.start_rental_vehicle_brand);
        TextView vehicleModel = (TextView) findViewById(R.id.start_rental_vehicle_model);
        TextView vehiclePlate = (TextView) findViewById(R.id.start_rental_vehicle_plate);
        TextView vehicleDailyCost = (TextView) findViewById(R.id.start_rental_vehicle_dailyCost);

        vehicleBrand.setText(vehicle.getBrand());
        vehicleModel.setText(vehicle.getModel());
        vehiclePlate.setText(vehicle.getPlate());
        vehicleDailyCost.setText(String.valueOf(vehicle.getDailyCost()) + " €/Jour");
    }

    public void toDetails(View view) {
        this.finish();
    }

    public void startRental(View view) {
        EditText customerFirstName = (EditText) findViewById(R.id.start_rental_customer_firstname_EditText);
        EditText customerLastName = (EditText) findViewById(R.id.start_rental_customer_lastname_EditText);
        EditText customerAddress = (EditText) findViewById(R.id.start_rental_customer_address_EditText);
        EditText customerPhone = (EditText) findViewById(R.id.start_rental_customer_phone_EditText);

        String firstName = customerFirstName.getText().toString().trim();
        String lastName = customerLastName.getText().toString().trim();
        String address = customerAddress.getText().toString().trim();
        String phoneNumber = customerPhone.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(RentalStartActivity.this, R.string.start_rental_error_form, Toast.LENGTH_SHORT).show();
        } else {
            vehiclesService.startRental(vehicle.getPlate(), new Customer(firstName, lastName, address, phoneNumber), pictureFiles);
            this.finish();
        }
    }

    public void addPicture(View view) {
        pictureFiles[nbPictures] = null;
        try {
            String path = "LokaCar_" + vehiclesService.getRentalsSize() + "_" + nbPictures;
            pictureFiles[nbPictures] = File.createTempFile(path, ".jpg");
            Uri uri = Uri.fromFile(pictureFiles[nbPictures]);
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //i.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (nbPictures < 4) {
                    //retrieveCapturedPicture();
                } else {
                    Toast.makeText(RentalStartActivity.this, R.string.start_rental_error_pictures_max, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void retrieveCapturedPicture() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(pictureFiles[nbPictures].getAbsolutePath(), options);
        nbPictures++;

        try {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            pictureBytes[nbPictures] = outStream.toByteArray();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }
}
