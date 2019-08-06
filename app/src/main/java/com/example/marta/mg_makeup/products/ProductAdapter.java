package com.example.marta.mg_makeup.products;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.marta.mg_makeup.R;
import com.example.marta.mg_makeup.data.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter <ProductAdapter.ViewHolder>{

private List <Product> products = new ArrayList<>();
private int selectedItemPosition= -1;

public void updateProducts (List<Product> productsList){
    products.clear();
    products.addAll(productsList);
    notifyDataSetChanged();
}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_products, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setup(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.item_product_card_view)
        CardView itemProductCardView;

    @BindView(R.id.item_product_image)
        ImageView productImage;

    @BindView(R.id.item_product_name)
        TextView productName;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setup(Product product) {
            Glide.with(itemView.getContext())
                   .load(product.imageLink)
                  .into(productImage);

            productName.setText(product.name);
        }
    }
}
