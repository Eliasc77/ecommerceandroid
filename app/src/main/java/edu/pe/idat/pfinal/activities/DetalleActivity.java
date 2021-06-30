package edu.pe.idat.pfinal.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.pe.idat.pfinal.R;
import edu.pe.idat.pfinal.common.Constantes;
import edu.pe.idat.pfinal.common.SharedPreferencesManager;
import edu.pe.idat.pfinal.databinding.ActivityDetalleBinding;
import edu.pe.idat.pfinal.fragment.CartFragment;
import edu.pe.idat.pfinal.model.Carrito;
import edu.pe.idat.pfinal.model.ProductoModel;

public class DetalleActivity extends AppCompatActivity {

    ActivityDetalleBinding binding;
    String id ;
    RadioButton t26,t28,t30,t32,t34;
    String talla;
    //pruebas
    private List<Carrito> tasklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetalleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //prueba
        tasklist = new ArrayList<>();
        t26=binding.t26;
        t28=binding.t28;
        t30=binding.t30;
        t32=binding.t32;
        t34=binding.t34;


        Bundle mibundle= this.getIntent().getExtras();

        if(mibundle!=null){
            id = mibundle.getString("key");
        }
        detalleAPI(new Constantes().URL_PRODUCTO_BY_ID+id);


        binding.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTocartItem();
            }
        });

    }

    private void addTocartItem(){
            if(t26.isChecked()){
                talla="26";
            }else if(t28.isChecked()){
                talla="28";
            }else if(t30.isChecked()){
                talla="30";
            }else if(t32.isChecked()){
                talla="32";
            }else if(t34.isChecked()){
                talla="34";
            }

            Carrito caritem = new Carrito(
                    SharedPreferencesManager.getSomeIntValue(Constantes.PREF_ID_CARRITO),
                    binding.detailedName.getText().toString(),
                    Double.parseDouble(binding.detailedPrice.getText().toString()),
                    Integer.parseInt(binding.quantity.getText().toString()),
                    talla,
                    Double.parseDouble(binding.detailedPrice.getText().toString()),
                    binding.detailedImg.toString()
                     );
            tasklist.add(caritem);
            SharedPreferencesManager.writeListInPreference(getApplicationContext(),tasklist);


    }

    private void detalleAPI(String url) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            if(response.length()>0){
                                JSONObject obj = new JSONObject(response.get(0).toString());
                                SharedPreferencesManager.setSomeIntValue(new Constantes().PREF_ID_CARRITO, obj.getInt("idproducto"));
                                binding.detailedDesc.setText(obj.getString("descripcion"));
                                binding.detailedName.setText(obj.getString("nombreprod"));
                                binding.detailedPrice.setText(obj.getString("precio"));
                                Glide.with(getApplicationContext()).load(obj.getString("foto")).
                                        into(binding.detailedImg);
                            }
                        }catch (JSONException ex){
                            ex.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}