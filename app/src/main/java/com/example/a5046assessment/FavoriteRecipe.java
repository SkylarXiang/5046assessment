package com.example.a5046assessment;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_recipes")
public class FavoriteRecipe {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String recipeId;
    private String recipeName;
    private String imageUrl;

    public FavoriteRecipe() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

