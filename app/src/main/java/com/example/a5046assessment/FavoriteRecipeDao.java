package com.example.a5046assessment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoriteRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addFavoriteRecipe(FavoriteRecipe favoriteRecipe);

    @Delete
    void delete(FavoriteRecipe favoriteRecipe);

    @Query("SELECT * FROM favorite_recipes")
    LiveData<List<FavoriteRecipe>> getAllFavoriteRecipes();

    @Query("SELECT * FROM favorite_recipes")
    List<FavoriteRecipe> getFavoriteRecipeList();
}
