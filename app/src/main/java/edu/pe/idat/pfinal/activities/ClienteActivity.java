package edu.pe.idat.pfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.pe.idat.pfinal.R;
import edu.pe.idat.pfinal.databinding.ActivityClienteBinding;

public class ClienteActivity extends AppCompatActivity {
    ActivityClienteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EnvioActivity.class));
            }
        });
    }
}