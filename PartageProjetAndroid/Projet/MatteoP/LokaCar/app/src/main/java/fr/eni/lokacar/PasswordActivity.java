package fr.eni.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import fr.eni.lokacar.services.VehiclesService;

public class PasswordActivity extends AppCompatActivity {

    private VehiclesService vehiclesService = VehiclesService.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }

    public void checkPassword(View view) {
        EditText passwordInput = (EditText) findViewById(R.id.password);
        String password = passwordInput.getText().toString();

        if (password.equals("abc123")) {
            Intent i = new Intent(PasswordActivity.this, MainActivity.class);
            startActivity(i);
            this.finish();
        } else {
            Toast.makeText(PasswordActivity.this, "Mot de passe incorrect !", Toast.LENGTH_SHORT).show();
        }
    }
}
