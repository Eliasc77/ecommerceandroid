package edu.pe.idat.pfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import edu.pe.idat.pfinal.R;
import edu.pe.idat.pfinal.common.Constantes;
import edu.pe.idat.pfinal.databinding.ActivityLoginBinding;
import edu.pe.idat.pfinal.fragment.MenuActivity;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    JSONArray resp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tvregister.setOnClickListener(view ->{
            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
        });

        binding.btnlogin.setOnClickListener(view ->{

            if(!binding.etemail.getText().toString().equals("")&&
                !binding.etpassword.getText().toString().equals("")){
                Autenticacion(new Constantes().URL_LOGIN+binding.etemail.getText().toString());

            }else{
                message("Usuario y Password obligatorio");
            }
        });
    }

    private void Autenticacion(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request =new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            resp = new JSONArray(response);
                            String contraseña = resp.getString(0);
                            if(contraseña.equals(binding.etpassword.getText().toString())){
                                message("Bienvenido :D");
                                Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                                startActivity(intent);
                            }else{
                                message("verificar su contraseña");
                            }

                        }catch (JSONException ex){
                            message("Usuario no existe en la abse de datos");
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(request);
    }

    private void message(String msj) {
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
    }


}