package com.example.a5046assessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");
    }
}