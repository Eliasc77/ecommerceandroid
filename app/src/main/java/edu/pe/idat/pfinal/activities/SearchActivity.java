package edu.pe.idat.pfinal.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

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
import edu.pe.idat.pfinal.adapter.SearchAdapter;
import edu.pe.idat.pfinal.common.Constantes;
import edu.pe.idat.pfinal.databinding.ActivitySearchBinding;
import edu.pe.idat.pfinal.model.ProductoModel;
import edu.pe.idat.pfinal.model.SearchModel;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.recSearch.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        searchAdapter = new SearchAdapter(getApplicationContext());
        binding.recSearch.setAdapter(searchAdapter);


        binding.buscartv.setOnClickListener(view ->{
            SearchProducts(new Constantes().URL_PRODUCTO_SEARCH+binding.buscartv.getText().toString());
        });
    }

    private void SearchProducts(String url) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<SearchModel> miList = new ArrayList<>();
                        if(response.length()>0){
                            for(int i=0; i<= response.length() ; i++){
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    SearchModel sm = new SearchModel();
                                    sm.setIdproducto(obj.getInt("idproducto"));
                                    sm.setNombreprod(obj.getString("nombreprod"));
                                    sm.setPrecio(obj.getDouble("precio"));
                                    sm.setFoto(obj.getString("foto"));
                                    miList.add(sm);

                                }catch (JSONException e){

                                }
                            }
                        }
                        searchAdapter.agregarLista(miList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }
}