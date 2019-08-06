package com.example.marta.mg_makeup.di;

import android.content.Context;

import com.example.marta.mg_makeup.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    Context provideContext(){return context;}
}
