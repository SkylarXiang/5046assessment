package com.example.a5046assessment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5046assessment.databinding.HomervLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeRecipeAdapter extends RecyclerView.Adapter <HomeRecipeAdapter.ViewHolder>{

    private List<Recipe> recipes;

    public HomeRecipeAdapter(List<Recipe> recipes){
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public HomeRecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomervLayoutBinding binding = HomervLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.binding.tvRecipeName.setText(recipe.getStrMeal());
        Picasso.get()
                .load(recipe.getStrMealThumb())
                .into(holder.binding.tvRecipeImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeDetailActivity.class);
                intent.putExtra("recipe", recipe);  // pass the recipe to the detail activity
                v.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void setRecipes(List<Recipe> recipes){
        this.recipes = recipes;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private HomervLayoutBinding binding;
        public ViewHolder(HomervLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}


