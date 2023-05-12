package com.example.a5046assessment;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteRecipeAdapter extends RecyclerView.Adapter<FavoriteRecipeAdapter.FavoriteRecipeViewHolder> {
    private List<FavoriteRecipe> favoriteRecipes = new ArrayList<>();

    @NonNull
    @Override
    public FavoriteRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_recipe_item, parent, false);
        return new FavoriteRecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteRecipeViewHolder holder, int position) {
        FavoriteRecipe currentRecipe = favoriteRecipes.get(position);
        holder.recipeNameTextView.setText(currentRecipe.getRecipeName());
        Picasso.get().load(currentRecipe.getImageUrl()).into(holder.recipeImageView);
        holder.removeButton.setOnClickListener(v -> {
            FavoriteRecipe toRemove = favoriteRecipes.get(position);
            favoriteRecipes.remove(position);
            notifyItemRemoved(position);
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                AppDatabase.getInstance(holder.itemView.getContext()).favoriteRecipeDao().delete(toRemove);
                holder.itemView.post(() -> Toast.makeText(holder.itemView.getContext(), "Recipe removed from favorites", Toast.LENGTH_SHORT).show());
            });
        });
    }

    @Override
    public int getItemCount() {
        return favoriteRecipes.size();
    }

    public void setFavoriteRecipes(List<FavoriteRecipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
        notifyDataSetChanged();
    }

    static class FavoriteRecipeViewHolder extends RecyclerView.ViewHolder {
        private TextView recipeNameTextView;
        private ImageView recipeImageView;
        private ImageButton removeButton;

        public FavoriteRecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameTextView = itemView.findViewById(R.id.recipe_name);
            recipeImageView = itemView.findViewById(R.id.recipe_image);
            removeButton = itemView.findViewById(R.id.remove_button);
        }
    }
}
