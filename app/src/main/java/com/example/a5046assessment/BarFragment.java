package com.example.a5046assessment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.a5046assessment.databinding.BarFragmentBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarFragment extends Fragment {
    private BarFragmentBinding barBinding;

    public BarFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        barBinding = BarFragmentBinding.inflate(inflater, container, false);
        View view = barBinding.getRoot();

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

                List<BarEntry> barEntries = new ArrayList<>();

                int temp = 0;

                List<String> xAxisValues = new ArrayList<>();

                for(Map.Entry<String, Integer> entry: count.entrySet()){
                    barEntries.add(new BarEntry(temp, entry.getValue()));
                    xAxisValues.add(entry.getKey());
                    temp++;
                }

                BarDataSet barDataSet = new BarDataSet(barEntries, "Country");
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                barBinding.barChart.getXAxis().setValueFormatter(new
                        com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));
                BarData barData = new BarData(barDataSet);
                barBinding.barChart.setData(barData);
                barBinding.barChart.animateY(1000);
                barBinding.barChart.invalidate();
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
        barBinding = null;
    }
}
