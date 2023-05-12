package com.example.a5046assessment;
import androidx.room.ColumnInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Recipe implements Serializable {
    private String idMeal;
    private String strMeal;
    private String strMealThumb;

    private String strArea;

    private String strInstructions;

    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;
    private String strIngredient6;
    private String strIngredient7;
    private String strIngredient8;
    private String strIngredient9;
    private String strIngredient10;
    private String strIngredient11;
    private String strIngredient12;
    private String strIngredient13;
    private String strIngredient14;
    private String strIngredient15;
    private String strIngredient16;
    private String strIngredient17;
    private String strIngredient18;
    private String strIngredient19;
    private String strIngredient20;

    private ArrayList<String> ingredients = new ArrayList<String>();


    public void setIngredients(){
        ingredients.add(strIngredient1);
        ingredients.add(strIngredient2);
        ingredients.add(strIngredient3);
        ingredients.add(strIngredient4);
        ingredients.add(strIngredient5);
        ingredients.add(strIngredient6);
        ingredients.add(strIngredient7);
        ingredients.add(strIngredient8);
        ingredients.add(strIngredient9);
        ingredients.add(strIngredient10);
        ingredients.add(strIngredient11);
        ingredients.add(strIngredient12);
        ingredients.add(strIngredient13);
        ingredients.add(strIngredient14);
        ingredients.add(strIngredient15);
        ingredients.add(strIngredient16);
        ingredients.add(strIngredient17);
        ingredients.add(strIngredient18);
        ingredients.add(strIngredient19);
        ingredients.add(strIngredient20);

        Iterator<String> iterator = ingredients.iterator();
        while(iterator.hasNext()){
            String value = iterator.next();
            if(value == null ||value.equals(":") || value.isEmpty() || value.contains("null")){
                iterator.remove();
            }
        }
    }

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

    public ArrayList<String> getIngredients(){
        return ingredients;
    }

    public String getStrArea(){
        return strArea;
    }
}
