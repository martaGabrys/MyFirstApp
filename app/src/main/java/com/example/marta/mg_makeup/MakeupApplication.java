package com.example.marta.mg_makeup;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.marta.mg_makeup.database.AppDatabase;
import com.example.marta.mg_makeup.di.AppComponent;
import com.example.marta.mg_makeup.di.AppModule;
import com.example.marta.mg_makeup.di.DaggerAppComponent;
import com.example.marta.mg_makeup.di.DataModule;

import timber.log.Timber;

public class MakeupApplication extends Application {

    private static AppDatabase db;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + " *** " + element.getLineNumber();
                }
            });

        } else {
            Timber.plant(new CrashReportingTree());
        }
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();


        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();

    }

    public static AppDatabase getDb() {
        return db;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, @NonNull String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }
        }
    }
}
