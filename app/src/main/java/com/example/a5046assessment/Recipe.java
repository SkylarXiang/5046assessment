package com.example.a5046assessment;
import java.io.Serializable;

public class Recipe implements Serializable {
    private String idMeal;
    private String strMeal;
    private String strMealThumb;

    private String strInstructions;

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
