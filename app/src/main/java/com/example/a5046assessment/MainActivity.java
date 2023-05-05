package com.example.a5046assessment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.ui.NavigationUI;

import com.example.a5046assessment.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TheMealDBApi theRandomMealDBApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        theRandomMealDBApi = retrofit.create(TheMealDBApi.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Button buttonSearch = findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        getRandomRecipe();
    }

    private void getRandomRecipe() {
        Call<RecipesResponse> call = theRandomMealDBApi.getRandomRecipe();
        call.enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                if (response.isSuccessful()) {
                    List<Recipe> recipes = response.body().getMeals();
                    if (recipes != null && !recipes.isEmpty()) {
                        // Display the recipe in your UI
                        Recipe recipe = recipes.get(0);
                        displayRandomRecipe(recipe);
                    }
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                // Handle the error
            }
        });
    }

    private void displayRandomRecipe(Recipe recipe) {
        ImageView imageView = findViewById(R.id.imageView_recipe);
        TextView textView = findViewById(R.id.textView_recipe_name);

        Picasso.get()
                .load(recipe.getStrMealThumb())
                .into(imageView);

        textView.setText(recipe.getStrMeal());
    }


}