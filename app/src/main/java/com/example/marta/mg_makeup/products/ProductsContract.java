package com.example.marta.mg_makeup.products;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.example.marta.mg_makeup.data.Product;

import java.util.List;

public interface ProductsContract {

    interface View {

        void showProgress();

        void showData(List<Product> products);

        void showError();

    }

    interface Presenter {
        void getProducts(String item) ;
    }
}
