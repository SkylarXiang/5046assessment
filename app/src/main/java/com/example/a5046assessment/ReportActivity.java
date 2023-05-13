package com.example.a5046assessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.a5046assessment.databinding.ActivityReportBinding;

public class ReportActivity extends AppCompatActivity {
    private ActivityReportBinding binding;

    final String CHART_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        
    }
}