package com.example.marta.mg_makeup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marta.mg_makeup.data.Product;
import com.example.marta.mg_makeup.products.ProductActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    public static final String FILTER_TYPE = "filterType";

    @BindView(R.id.button_brand)
    Button buttonBrand;

    @BindView(R.id.button_product_type)
    Button buttonProductType;

    @BindView(R.id.button_tag)
    Button buttonTag;

    @BindView(R.id.button_all)
    Button buttonAll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button_brand)
    public void startFilterProductByBrand(){
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(FILTER_TYPE, R.array.brands);
        startActivity(intent);
    }

    @OnClick(R.id.button_product_type)
    public void startFilterProductByProductType(){
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(FILTER_TYPE, R.array.productType);
        startActivity(intent);
    }

    @OnClick(R.id.button_tag)
    public void startFilterProductByTag(){
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(FILTER_TYPE, R.array.tags);
        startActivity(intent);
    }

    @OnClick(R.id.button_all)
    public void getAllProduct(){
        Intent intent = new Intent(this, ProductActivity.class);
        startActivity(intent);
    }

}
