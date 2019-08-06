package com.example.marta.mg_makeup.di;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.marta.mg_makeup.ApplicationScope;
import com.example.marta.mg_makeup.api.MakeupApi;
import com.example.marta.mg_makeup.dao.ProductDao;
import com.example.marta.mg_makeup.database.AppDatabase;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {

    @Provides
    @ApplicationScope
    AppDatabase provideAppDatabase(Context context){
        return Room.databaseBuilder(context,
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @ApplicationScope
    ProductDao provideProductDao(AppDatabase appDatabase){ return appDatabase.productDao();}

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(MakeupApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    MakeupApi provideMakeupApi(Retrofit retrofit){
        return retrofit.create(MakeupApi.class);
    }

}
