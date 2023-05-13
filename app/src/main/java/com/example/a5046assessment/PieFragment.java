package com.example.a5046assessment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.a5046assessment.databinding.PieFragmentBinding;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieFragment extends Fragment {

    private PieFragmentBinding pieBinding;

    public PieFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        pieBinding = PieFragmentBinding.inflate(inflater, container, false);
        View view = pieBinding.getRoot();


        //get data from database
        AppDatabase db = AppDatabase.getInstance(getContext());
        LiveData<List<FavoriteRecipe>> favoriteRecipesLiveData = db.favoriteRecipeDao().getAllFavoriteRecipes();

        favoriteRecipesLiveData.observe(getViewLifecycleOwner(), new Observer<List<FavoriteRecipe>>() {
            @Override
            public void onChanged(List<FavoriteRecipe> favoriteRecipes) {
                List<String> area = new ArrayList<>();
                for(FavoriteRecipe temp: favoriteRecipes){
                    area.add(temp.getArea());
                }

                Map<String, Integer> count = count(area);

                List<PieEntry> pieEntries = new ArrayList<>();

                for(Map.Entry<String, Integer> entry: count.entrySet()){
                    pieEntries.add(new PieEntry(entry.getValue(), entry.getKey()));
                }

                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Data");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                PieData pieData = new PieData(pieDataSet);
                pieBinding.pieChart.setData(pieData);
                pieBinding.pieChart.animateY(1000);
                pieBinding.pieChart.invalidate();
            }
        });

        return view;
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
    public void onDestroyView(){
        super.onDestroyView();
        pieBinding = null;
    }

}
