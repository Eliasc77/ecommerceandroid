package edu.pe.idat.pfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.pe.idat.pfinal.R;
import edu.pe.idat.pfinal.common.Constantes;
import edu.pe.idat.pfinal.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    EditText etemail,etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        etemail = binding.etregemail;
        etpassword = binding.etregpass;


        binding.btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();

                if(email.equals("") || password.equals("")){
                    mensaje("Ambos Campos son requeridos");
                }else{
                    SaveUsurario(email, password);
                    mensaje("usuagrio grabado");
                }

            }
        });
    }

    private void mensaje(String msj) {
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
    }

    private void SaveUsurario(final String email,final String password) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(
                Request.Method.POST,
                new Constantes().URL_LOGIN_SAVE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                        }catch (JSONException x){

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params  = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        queue.add(request);

    }
}