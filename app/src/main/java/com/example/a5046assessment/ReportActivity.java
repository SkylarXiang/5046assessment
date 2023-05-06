package com.example.a5046assessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.a5046assessment.databinding.ActivityReportBinding;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    private ActivityReportBinding binding;

    final String CHART_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 6766));
        barEntries.add(new BarEntry(1, 4444));
        barEntries.add(new BarEntry(2, 2222));
        barEntries.add(new BarEntry(3, 5555));
        barEntries.add(new BarEntry(4, 1111));
        barEntries.add(new BarEntry(5, 3656));
        barEntries.add(new BarEntry(6, 3435));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Steps");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    }
}