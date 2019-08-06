package com.example.marta.mg_makeup.products;

import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marta.mg_makeup.MakeupApplication;
import com.example.marta.mg_makeup.MenuActivity;
import com.example.marta.mg_makeup.R;
import com.example.marta.mg_makeup.data.Product;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductActivity extends AppCompatActivity implements ProductsContract.View, AdapterView.OnItemSelectedListener {

    public static final String FILTER_TYPE_ITEM = "filterTypeItem";

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.products_recycler)
    RecyclerView productRecycler;

    @BindView(R.id.products_progress)
    ProgressBar productProgress;

    @BindView(R.id.products_error_image)
    ImageView productErrorImage;

    @BindView(R.id.products_error_message)
    TextView productErrorTextView;

    private ProductAdapter adapter;


    @Inject
    ProductsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        ButterKnife.bind(this);

        ((MakeupApplication)getApplication()).getAppComponent()
                .plus(new ProductModule(this))
                .inject(this);

        int filterType= getIntent().getIntExtra(MenuActivity.FILTER_TYPE, 1);
        setSpinner(filterType);

        adapter = new ProductAdapter();
        productRecycler.setLayoutManager(new LinearLayoutManager(this));
        productRecycler.setAdapter(adapter);
    }

    private void setSpinner(int filterType) {
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
                filterType, R.layout.simple_spinner_item_mg);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);
    }

    @Override
    public void showProgress() {
        productRecycler.setVisibility(View.INVISIBLE);
        productErrorTextView.setVisibility(View.INVISIBLE);
        productErrorImage.setVisibility(View.INVISIBLE);
        productProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showData(List<Product> products) {
        adapter.updateProducts(products);
        productProgress.setVisibility(View.INVISIBLE);
        productErrorTextView.setVisibility(View.INVISIBLE);
        productErrorImage.setVisibility(View.INVISIBLE);
        productRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        productRecycler.setVisibility(View.INVISIBLE);
        productProgress.setVisibility(View.INVISIBLE);
        productErrorTextView.setVisibility(View.VISIBLE);
        productErrorImage.setVisibility(View.VISIBLE);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        presenter.getProducts(item);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
