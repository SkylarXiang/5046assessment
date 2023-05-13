package com.example.a5046assessment.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5046assessment.HomeActivity;
import com.example.a5046assessment.HomeRecipeAdapter;
import com.example.a5046assessment.MapActivity;
import com.example.a5046assessment.R;
import com.example.a5046assessment.Recipe;
import com.example.a5046assessment.RecipesResponse;
import com.example.a5046assessment.SearchActivity;
import com.example.a5046assessment.SyncActivity;
import com.example.a5046assessment.TheMealDBApi;
import com.example.a5046assessment.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private TheMealDBApi theRandomMealDBApi;
    private List<Recipe> recipeList = new ArrayList<Recipe>();
    private HomeRecipeAdapter adapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        theRandomMealDBApi = retrofit.create(TheMealDBApi.class);

        recyclerView = root.findViewById(R.id.homeRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new HomeRecipeAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        recipeList.clear();
        Button buttonSearch = root.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageViewMap = root.findViewById(R.id.imageViewMap);
        imageViewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageViewSync = root.findViewById(R.id.imageViewSync);
        imageViewSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SyncActivity.class);
                startActivity(intent);
            }
        });

        // Call getMultiRandom() in onStart
        for (int i = 0; i < 10; i++) {
            getMultiRandom();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getMultiRandom(){
        Call<RecipesResponse> call = theRandomMealDBApi.getRandomRecipe();
        call.enqueue(new Callback<RecipesResponse>() {
            @Override
            public void onResponse(Call<RecipesResponse> call, Response<RecipesResponse> response) {
                if(response.isSuccessful()){
                    List<Recipe> recipes = response.body().getMeals();
                    if(recipes != null && !recipes.isEmpty()){recipeList.add(recipes.get(0));
                        adapter.setRecipes(recipeList);
                    }
                }
            }

            @Override
            public void onFailure(Call<RecipesResponse> call, Throwable t) {

            }
        });
    }
}
