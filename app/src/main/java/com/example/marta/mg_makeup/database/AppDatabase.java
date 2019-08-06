package com.example.marta.mg_makeup.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.marta.mg_makeup.dao.ProductDao;
import com.example.marta.mg_makeup.data.Product;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}