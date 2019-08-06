package com.example.marta.mg_makeup.products;

import com.example.marta.mg_makeup.api.MakeupApi;
import com.example.marta.mg_makeup.dao.ProductDao;
import com.example.marta.mg_makeup.data.Product;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductModule {
    private ProductsContract.View view;

    public ProductModule(ProductsContract.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    ProductsContract.Presenter provideProductPresenter(MakeupApi makeupApi, ProductDao productDao){
        return new ProductsPresenter(view, makeupApi, productDao);
    }

}
