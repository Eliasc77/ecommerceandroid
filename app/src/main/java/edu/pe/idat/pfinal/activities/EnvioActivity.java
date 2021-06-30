package edu.pe.idat.pfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.pe.idat.pfinal.R;
import edu.pe.idat.pfinal.databinding.ActivityEnvioBinding;

public class EnvioActivity extends AppCompatActivity {

    ActivityEnvioBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityEnvioBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MetodoPaymentActivity.class));
            }
        });
    }
}