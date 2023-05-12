package com.example.a5046assessment.ui.notifications;

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

import com.example.a5046assessment.AppDatabase;
import com.example.a5046assessment.FavoriteRecipe;
import com.example.a5046assessment.Recipe;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.example.a5046assessment.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNotifications;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        //get data from database
        AppDatabase db = AppDatabase.getInstance(getContext());
        LiveData<List<FavoriteRecipe>> favoriteRecipesLiveData = db.favoriteRecipeDao().getAllFavoriteRecipes();

        favoriteRecipesLiveData.observe(getViewLifecycleOwner(), new Observer<List<FavoriteRecipe>>() {
            @Override
            public void onChanged(List<FavoriteRecipe> favoriteRecipes) {
                List<String> name = new ArrayList<>();
                for(FavoriteRecipe temp: favoriteRecipes){
                    name.add(temp.getRecipeName());
                }

                Map<String, Integer> count = count(name);

                List<PieEntry> pieEntries = new ArrayList<>();

                for(Map.Entry<String, Integer> entry: count.entrySet()){
                    pieEntries.add(new PieEntry(entry.getValue(), entry.getKey()));
                }

                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Data");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                PieData pieData = new PieData(pieDataSet);
                binding.pieChart.setData(pieData);
            }
        });

        //test pie

        return root;
    }

    public Map<String, Integer> count(List<String> items){
        Map<String, Integer> map = new HashMap<>();
        for(String s:items){
            Integer count = map.get(s);
            map.put(s, (count == null) ? 1 : count + 1);
        }
        return map;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}