package com.example.a5046assessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.a5046assessment.databinding.ActivityRecipeDetailBinding;
import com.squareup.picasso.Picasso;

import android.content.Context;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecipeDetailActivity extends AppCompatActivity {
    private ActivityRecipeDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");


        binding.Name.setText(recipe.getStrMeal());

        Picasso.get()
                .load(recipe.getStrMealThumb())
                .into(binding.Image);

        binding.Instruction.setText("Cooking instructions: " + recipe.getStrInstructions());


        ImageButton myButton = findViewById(R.id.favorite_button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a FavoriteRecipe object and set its properties from the Recipe object
                FavoriteRecipe favoriteRecipe = new FavoriteRecipe();
                favoriteRecipe.setRecipeName(recipe.getStrMeal());
                favoriteRecipe.setImageUrl(recipe.getStrMealThumb());

                // Add the favorite recipe to the Room database
                AppDatabase db = AppDatabase.getInstance(RecipeDetailActivity.this);
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        db.favoriteRecipeDao().addFavoriteRecipe(favoriteRecipe);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RecipeDetailActivity.this, "Recipe added to favorites", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

    }
}