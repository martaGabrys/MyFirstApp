package com.example.marta.mg_makeup.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.marta.mg_makeup.data.Product;

import java.util.List;

import javax.inject.Inject;

@Dao
public interface ProductDao {
    @Query("SELECT *FROM Product")
    public abstract List<Product> getAllProductsDao();
    @Insert
    void insertProductDao(List<Product> productList );


    @Query("SELECT*FROM Product WHERE brand = :brand")
    public abstract List<Product> getProductByBrand(String brand);



    @Query("SELECT*FROM Product WHERE category = :category")
    public abstract List <Product> getProductByCategory(String category);

//    @Query("SELECT*FROM Product WHERE tag = :tag")
//    public abstract List <Product> getProductByTag(String tag);
}
