package com.example.a5046assessment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class StoreFavoriteRecipeWorker extends Worker {
    private FavoriteRecipeDao dao;

    public StoreFavoriteRecipeWorker(@NonNull Context context,
                                     @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        AppDatabase db = AppDatabase.getInstance(context);
        dao = db.favoriteRecipeDao();
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            List<FavoriteRecipe> favoriteRecipeList = dao.getFavoriteRecipeList();

            DatabaseReference dbRef = FirebaseDatabase.
                    getInstance("https://assessment-ba210-default-rtdb.asia-southeast1.firebasedatabase.app/").
                    getReference();

            if (favoriteRecipeList != null) {
                int id = 1;

                for (FavoriteRecipe favoriteRecipe : favoriteRecipeList) {
                    String recipeId = favoriteRecipe.getRecipeId();
                    String recipeName = favoriteRecipe.getRecipeName();
                    String imageUrl = favoriteRecipe.getImageUrl();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("RecipeID", recipeId);
                    hashMap.put("RecipeName", recipeName);
                    hashMap.put("ImageURL", imageUrl);
                    dbRef.child("FavoriteRecipe").child(""+id).setValue(hashMap);
                    id++;
                }
            }
            return Result.success();
        }

        catch (Exception e) {
            Data outputData = new Data.Builder()
                    .putString("error_message", e.getMessage())
                    .build();
            return Result.failure(outputData);
        }
    }
}
