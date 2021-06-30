package edu.pe.idat.pfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import edu.pe.idat.pfinal.R;
import edu.pe.idat.pfinal.databinding.ActivityMetodoPaymentBinding;

public class MetodoPaymentActivity extends AppCompatActivity {
    ActivityMetodoPaymentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMetodoPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PagoActivity.class));
            }
        });
    }
}