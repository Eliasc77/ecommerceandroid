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
import edu.pe.idat.pfinal.databinding.CategoriaListBinding;

import edu.pe.idat.pfinal.model.CategoriaModel;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CategoriaModel> list;

    public CategoriaAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CategoriaListBinding rbinding =CategoriaListBinding.inflate(layoutInflater,parent,false);
        return new ViewHolder(rbinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final CategoriaModel obj = list.get(position);
        //holder.recyclerbinding.imgviewcategoria.setImageResource(obj.getImg());
        Glide.with(context).load(obj.getImg()).into(holder.recyclerbinding.imgviewcategoria);
        holder.recyclerbinding.descCategoria.setText(obj.getDescripcion());

        holder.recyclerbinding.imgviewcategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle miBundle = new Bundle();
                miBundle.putString("key", obj.getIdcategoria()+"");
                miBundle.putString("descripcion",obj.getDescripcion());
                Intent intent = new Intent(context, CategDetailActivity.class);
                intent.putExtras(miBundle);
                context.startActivity(intent);
            }

        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void agregarLista(ArrayList<CategoriaModel> lista){
        list.clear();
        list.addAll(lista);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CategoriaListBinding recyclerbinding;
        public ViewHolder(@NonNull CategoriaListBinding itemView) {
            super(itemView.getRoot());
            recyclerbinding = itemView;
        }
    }
}
