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


import edu.pe.idat.pfinal.activities.DetalleActivity;
import edu.pe.idat.pfinal.databinding.SearchListBinding;
import edu.pe.idat.pfinal.model.CategoriaModel;
import edu.pe.idat.pfinal.model.SearchModel;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private ArrayList<SearchModel> list;

    public SearchAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SearchListBinding recycler = SearchListBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(recycler);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SearchModel obj = list.get(position);
        Glide.with(context).load(obj.getFoto()).into(holder.recycler.producSearch);
        holder.recycler.productNameSearch.setText(obj.getNombreprod());
        holder.recycler.priceSearch.setText(String.valueOf(obj.getPrecio()));

        holder.recycler.producSearch.setOnClickListener(new View.OnClickListener() {
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

    public void agregarLista(ArrayList<SearchModel> lista){
        list.clear();
        list.addAll(lista);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SearchListBinding recycler;
        public ViewHolder(@NonNull SearchListBinding itemView) {
            super(itemView.getRoot());
            recycler = itemView;
        }
    }
}
