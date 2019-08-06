package com.example.marta.mg_makeup.api;

import com.example.marta.mg_makeup.data.Product;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MakeupApi {


    String BASE_URL = "http://makeup-api.herokuapp.com/api/v1/";

    @GET("products.json")
    Single<List<Product>> getProductsApi();

    @GET("products.json")
    Single<List<Product>> getProductsApiByBrand(@Query("brand") String brand);

}
