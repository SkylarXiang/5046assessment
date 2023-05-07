package com.example.a5046assessment;
import androidx.room.ColumnInfo;

import java.io.Serializable;

public class Recipe implements Serializable {
    private String idMeal;
    private String strMeal;
    private String strMealThumb;

    private String strInstructions;

    @ColumnInfo(name = "is_favorite")
    private boolean isFavorite;

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public String getStrInstructions(){
        return strInstructions;
    }
}
