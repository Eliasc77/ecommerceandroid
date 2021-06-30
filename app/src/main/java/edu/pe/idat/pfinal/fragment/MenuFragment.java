package edu.pe.idat.pfinal.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.pe.idat.pfinal.R;
import edu.pe.idat.pfinal.activities.SearchActivity;
import edu.pe.idat.pfinal.adapter.CategoriaAdapter;
import edu.pe.idat.pfinal.adapter.PopularProductAdapter;
import edu.pe.idat.pfinal.common.Constantes;
import edu.pe.idat.pfinal.model.CategoriaModel;
import edu.pe.idat.pfinal.model.PopularModel;


public class MenuFragment extends Fragment {

    TextView tvsearch;

    //Categoria
    CategoriaAdapter categoriaAdapter;
    RecyclerView categRecyclerView;

    //PopularProducts!
    PopularProductAdapter popularAdapter;
    RecyclerView popularRecycler;


    public MenuFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_menu, container, false);

        //Banner presentacion
        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner1, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2, ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3, ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        tvsearch = root.findViewById(R.id.searchtv);
        tvsearch.setOnClickListener(v->{
            tvsearch.setFocusableInTouchMode(true);
            tvsearch.requestFocus();
            if(!root.findViewById(R.id.searchtv).toString().equals("")){
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });

        //Categoria....
        categRecyclerView = root.findViewById(R.id.rec_category);
        categRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        categoriaAdapter = new CategoriaAdapter(getContext());
        categRecyclerView.setAdapter(categoriaAdapter);

        //Popular.....
        popularRecycler = root.findViewById(R.id.new_product_rec);
        popularRecycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        popularAdapter = new PopularProductAdapter(getContext());
        popularRecycler.setAdapter(popularAdapter);

        //Metodos
        obtenerCategoria(new Constantes().URL_CATEGORIA);
        obtenerPopularProducts(new Constantes().URL_POPULAR_PRODUCTS);



        return  root;
    }

    private void obtenerPopularProducts(String url) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<PopularModel> milist = new ArrayList<>();
                        if(response.length()>0){
                            for(int i=0; i<= response.length() ; i++){
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    PopularModel pop = new PopularModel();
                                    pop.setIdproducto(obj.getInt("idproducto"));
                                    pop.setNombreprod(obj.getString("nombreprod"));
                                    pop.setPrecio(obj.getDouble("precio"));
                                    pop.setFoto(obj.getString("foto"));
                                    milist.add(pop);
                                }catch (JSONException ex){
                                    ex.printStackTrace();
                                }
                            }
                            popularAdapter.agregarLista(milist);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
    }

    private void obtenerCategoria(String url) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<CategoriaModel> mlista = new ArrayList<>();
                        if(response.length()>0){
                            for(int i =0; i<= response.length() ; i++){
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    CategoriaModel cat = new CategoriaModel();
                                    cat.setIdcategoria(obj.getInt("idcategoria"));
                                    cat.setDescripcion(obj.getString("descripcion"));
                                    cat.setImg(obj.getString("img"));
                                    mlista.add(cat);
                                }catch (JSONException ex){
                                    ex.printStackTrace();
                                }
                            }
                            categoriaAdapter.agregarLista(mlista);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int x;
            }
        });
        queue.add(request);
    }
}