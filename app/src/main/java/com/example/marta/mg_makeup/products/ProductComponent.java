package com.example.marta.mg_makeup.products;

import javax.inject.Singleton;
import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {ProductModule.class})
public interface ProductComponent {
    void inject(ProductActivity productActivity);
}
