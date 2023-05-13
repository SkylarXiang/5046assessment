package com.example.a5046assessment;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.Executor;

@Database(entities = {FavoriteRecipe.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static Executor databaseWriteExecutor;
    private static AppDatabase instance;

    public abstract FavoriteRecipeDao favoriteRecipeDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
