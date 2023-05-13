package com.example.a5046assessment;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.a5046assessment.databinding.ActivitySyncBinding;

import java.util.concurrent.TimeUnit;

public class SyncActivity extends AppCompatActivity {
    private ActivitySyncBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySyncBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.manualSyncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(StoreFavoriteRecipeWorker.class)
                        .setConstraints(Constraints.NONE)
                        .build();
                WorkManager.getInstance().enqueue(workRequest);
            }
        });

        binding.autoSyncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(StoreFavoriteRecipeWorker.class,
                        24,
                        TimeUnit.HOURS)
                        .setConstraints(Constraints.NONE)
                        .build();
                WorkManager.getInstance().enqueue(workRequest);
            }
        });
    }
}