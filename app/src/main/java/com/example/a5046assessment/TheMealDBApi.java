package com.example.a5046assessment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheMealDBApi {

    @GET("search.php")
    Call<RecipesResponse> searchRecipes(@Query("s") String query);

    @GET("random.php")
    Call<RecipesResponse> getRandomRecipe();

}