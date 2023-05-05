package com.example.a5046assessment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private SearchView searchView;
    private TheMealDBApi theMealDBApi;
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        theMealDBApi = retrofit.create(TheMealDBApi.class);

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRecipes(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        recipeAdapter = new RecipeAdapter(new ArrayList<>());
        recyclerView.setAdapter(recipeAdapter);

    }

    private void searchRecipes(String query) {
        Call<RecipesResponse> call = theMealDBApi.searchRecipes(query);
        call.enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                if (response.isSuccessful()) {
                    List<Recipe> recipes = response.body().getMeals();
                    // Handle the list of recipes here, e.g., display them in a RecyclerView
                    recipeAdapter.setRecipes(recipes);
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {
                // Handle network error
            }
        });
    }
}
