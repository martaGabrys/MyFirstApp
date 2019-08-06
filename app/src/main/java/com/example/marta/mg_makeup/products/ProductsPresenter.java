package com.example.marta.mg_makeup.products;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import com.example.marta.mg_makeup.api.MakeupApi;
import com.example.marta.mg_makeup.dao.ProductDao;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class ProductsPresenter implements ProductsContract.Presenter, LifecycleObserver {

    private ProductsContract.View view;
    private MakeupApi makeupApi;
    private ProductDao productDao;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ProductsPresenter(ProductsContract.View view, MakeupApi makeupApi, ProductDao productDao) {
        this.view = view;
        this.makeupApi = makeupApi;
        ((LifecycleOwner) this.view).getLifecycle().addObserver(this);
        this.productDao = productDao;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onStart(){
        view.showProgress();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy(){compositeDisposable.clear();}

    @Override
    public void getProducts(String item) {
        compositeDisposable.clear();
       if (!compositeDisposable.isDisposed()){
            compositeDisposable.add(makeupApi.getProductsApiByBrand(item)
                    .subscribeOn(Schedulers.io())
                    .flatMapObservable(products -> {
                    productDao.insertProductDao(products);
                    return Observable.just(products);
            })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            products -> {
                                view.showData(products);
                                },
                            throwable -> {throwable.printStackTrace();
                                    view.showError();}
                    ));
        }

        else{
           view.showData(productDao.getAllProductsDao());
        }
    }
}
