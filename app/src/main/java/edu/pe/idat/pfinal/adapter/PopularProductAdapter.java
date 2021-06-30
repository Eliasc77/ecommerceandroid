package edu.pe.idat.pfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import edu.pe.idat.pfinal.activities.CategDetailActivity;
import edu.pe.idat.pfinal.activities.DetalleActivity;
import edu.pe.idat.pfinal.databinding.PopularListBinding;

import edu.pe.idat.pfinal.model.PopularModel;

public class PopularProductAdapter extends RecyclerView.Adapter<PopularProductAdapter.ViewHolder> {

    private ArrayList<PopularModel> list;
    private Context context;

    public PopularProductAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PopularListBinding recycler  = PopularListBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(recycler);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PopularModel obj = list.get(position);
        Glide.with(context).load(obj.getFoto()).into(holder.recyblerbinding.popularimg);
        holder.recyblerbinding.newProductName.setText(obj.getNombreprod());
        holder.recyblerbinding.newPrice.setText(String.valueOf(obj.getPrecio()));

        holder.recyblerbinding.popularimg.setOnClickListener(new View.OnClickListener() {
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

    public void agregarLista(ArrayList<PopularModel> lista){
        list.clear();
        list.addAll(lista);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PopularListBinding recyblerbinding;
        public ViewHolder(@NonNull PopularListBinding itemView) {
            super(itemView.getRoot());
            recyblerbinding = itemView;
        }
    }
}
