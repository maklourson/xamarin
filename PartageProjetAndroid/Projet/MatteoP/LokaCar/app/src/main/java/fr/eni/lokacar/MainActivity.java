package fr.eni.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import fr.eni.lokacar.services.VehiclesService;

public class MainActivity extends AppCompatActivity {

    private VehiclesService vehiclesService = VehiclesService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView turnover = (TextView) findViewById(R.id.main_menu_turnover);
        turnover.setText(getString(R.string.main_menu_turnover) + ": " + vehiclesService.getTurnover() + "€");
    }

    public void toPassword(View view) {
        Intent i = new Intent(MainActivity.this, PasswordActivity.class);
        startActivity(i);
        this.finish();
    }

    public void toVehiclesList(View view) {
        Intent i = new Intent(MainActivity.this, VehiclesListActivity.class);
        startActivity(i);
    }

    public void toRentalsHistory(View view) {
        Intent i = new Intent(MainActivity.this, RentalsHistoryActivity.class);
        startActivity(i);
    }
}
