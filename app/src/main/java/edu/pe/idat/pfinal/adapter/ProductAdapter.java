package edu.pe.idat.pfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


import edu.pe.idat.pfinal.activities.CategDetailActivity;
import edu.pe.idat.pfinal.activities.DetalleActivity;
import edu.pe.idat.pfinal.databinding.ProductListCatBinding;
import edu.pe.idat.pfinal.model.PopularModel;
import edu.pe.idat.pfinal.model.ProductoModel;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProductoModel> list;

    public ProductAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProductListCatBinding recycler = ProductListCatBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(recycler);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProductoModel obj = list.get(position);
        Glide.with(context).load(obj.getFoto()).into(holder.recycler.producCat);
        holder.recycler.productName.setText(obj.getNombreprod());
        holder.recycler.price.setText(String.valueOf(obj.getPrecio()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle miBundle = new Bundle();
                miBundle.putString("key", obj.getIdproducto()+"");
                Intent intent = new Intent(context, DetalleActivity.class);
                intent.putExtras(miBundle);
                context.startActivity(intent);
            }
        });

    }

    public void agregarLista(ArrayList<ProductoModel> lista){
        list.clear();
        list.addAll(lista);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProductListCatBinding recycler;
        public ViewHolder(@NonNull ProductListCatBinding itemView) {
            super(itemView.getRoot());
            recycler = itemView;
        }
    }
}
