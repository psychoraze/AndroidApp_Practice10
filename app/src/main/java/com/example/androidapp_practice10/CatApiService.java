package com.example.androidapp_practice10;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CatApiService {
    @GET("v1/images/search")
    Call<List<Cat>> getCats();
}
