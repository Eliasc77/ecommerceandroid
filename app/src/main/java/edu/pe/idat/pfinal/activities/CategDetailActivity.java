package edu.pe.idat.pfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.pe.idat.pfinal.R;
import edu.pe.idat.pfinal.adapter.ProductAdapter;
import edu.pe.idat.pfinal.common.Constantes;
import edu.pe.idat.pfinal.databinding.ActivityCategDetailBinding;
import edu.pe.idat.pfinal.model.PopularModel;
import edu.pe.idat.pfinal.model.ProductoModel;

public class CategDetailActivity extends AppCompatActivity {

    ActivityCategDetailBinding binding;
    ProductAdapter prodcatAdapter;



    String id ;
    JSONArray J;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityCategDetailBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.recCategoriasProdu.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        prodcatAdapter = new ProductAdapter(getApplicationContext());
        binding.recCategoriasProdu.setAdapter(prodcatAdapter);

        Bundle mibBundle = this.getIntent().getExtras();
        if(mibBundle != null){
            id = mibBundle.getString("key");
            binding.categoryName.setText(mibBundle.getString("descripcion"));
        }

        getProductosbyCategoria(new Constantes().URL_PRODUCTS_BY_CATEGORY+id);
    }

    private void getProductosbyCategoria(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<ProductoModel> miList = new ArrayList<>();
                        if(response.length()>0){
                            for(int i=0; i<= response.length() ; i++){
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    ProductoModel pop = new ProductoModel();
                                    pop.setIdproducto(obj.getInt("idproducto"));
                                    pop.setNombreprod(obj.getString("nombreprod"));
                                    pop.setPrecio(obj.getDouble("precio"));
                                    pop.setFoto(obj.getString("foto"));
                                    miList.add(pop);

                                }catch (JSONException x){
                                    x.printStackTrace();
                                }
                            }
                        }
                        prodcatAdapter.agregarLista(miList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}