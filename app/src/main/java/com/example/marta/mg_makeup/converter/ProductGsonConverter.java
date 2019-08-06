package com.example.marta.mg_makeup.converter;

import android.arch.persistence.room.TypeConverter;

import com.example.marta.mg_makeup.data.Product;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProductGsonConverter {

    @TypeConverter
    public static List<Product> stringToProduct(String json){
        Gson gson = new Gson();
        Type type  = new TypeToken<List<Product>>(){}.getType();
        List<Product> products = gson.fromJson(json, type);
        return products;
    }

    @TypeConverter
    public static String productToString(List<Product> products){
        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>(){}.getType();
        String json = gson.toJson(products, type);
        return json;
    }

}
