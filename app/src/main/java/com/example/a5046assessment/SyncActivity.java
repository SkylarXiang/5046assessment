package com.example.a5046assessment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
                Log.i("Message", "One-time Synchronisation Complete");
                Toast.makeText(SyncActivity.this, "One-time Sync Complete", Toast.LENGTH_SHORT).show();
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
                Log.i("Message", "Auto-Synchronisation per 24 Hours Starts");
                Toast.makeText(SyncActivity.this, "Auto-sync Starts", Toast.LENGTH_SHORT).show();
            }
        });
    }
}