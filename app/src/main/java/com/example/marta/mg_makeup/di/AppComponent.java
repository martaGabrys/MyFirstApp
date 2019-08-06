package com.example.marta.mg_makeup.di;


import com.example.marta.mg_makeup.ApplicationScope;
import com.example.marta.mg_makeup.products.ProductComponent;
import com.example.marta.mg_makeup.products.ProductModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    ProductComponent plus (ProductModule productModule);
}
