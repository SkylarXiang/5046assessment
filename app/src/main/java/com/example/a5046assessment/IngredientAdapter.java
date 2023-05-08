package com.example.a5046assessment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5046assessment.databinding.IngredientrvBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter <IngredientAdapter.ViewHolder>{

    private List<String> ingredients;

    public IngredientAdapter(List<String> ingredients){
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IngredientrvBinding binding = IngredientrvBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        String ingredient = ingredients.get(position);
        holder.binding.tvIngredient.setText(ingredient);

        Picasso.get()
                .load("https:\\/\\/www.themealdb.com\\/images\\/ingredients\\/" + ingredient + ".png")
                .into(holder.binding.ivIngredient);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private IngredientrvBinding binding;
        public ViewHolder(IngredientrvBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
