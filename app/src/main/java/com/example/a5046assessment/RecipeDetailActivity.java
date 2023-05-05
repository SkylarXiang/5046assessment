package com.example.a5046assessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.a5046assessment.databinding.ActivityRecipeDetailBinding;
import com.squareup.picasso.Picasso;

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
    }
}