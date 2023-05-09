package com.example.a5046assessment.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a5046assessment.AppDatabase;
import com.example.a5046assessment.FavoriteRecipe;
import com.example.a5046assessment.FavoriteRecipeAdapter;
import com.example.a5046assessment.R;
import com.example.a5046assessment.databinding.FragmentDashboardBinding;

import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private FavoriteRecipeAdapter favoriteRecipeAdapter;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Initialize the RecyclerView and the FavoriteRecipeAdapter
        RecyclerView recyclerView = binding.favoriteRecipesRecyclerview;
        favoriteRecipeAdapter = new FavoriteRecipeAdapter();
        recyclerView.setAdapter(favoriteRecipeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get the AppDatabase instance
        AppDatabase db = AppDatabase.getInstance(getContext());

        // Get a reference to the LiveData object
        LiveData<List<FavoriteRecipe>> favoriteRecipesLiveData = db.favoriteRecipeDao().getAllFavoriteRecipes();

        // Observe the LiveData object and update your UI when the data changes
        favoriteRecipesLiveData.observe(getViewLifecycleOwner(), new Observer<List<FavoriteRecipe>>() {
            @Override
            public void onChanged(List<FavoriteRecipe> favoriteRecipes) {
                // Update your RecyclerView here
                favoriteRecipeAdapter.setFavoriteRecipes(favoriteRecipes);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
