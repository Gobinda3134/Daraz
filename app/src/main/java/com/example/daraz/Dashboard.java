package com.example.daraz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.daraz.Api.api;
import com.example.daraz.Model.Product_daraz;
import com.example.daraz.Url.url;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        recyclerView = findViewById(R.id.RecyclerView);
        getProduct();

    }

    private void getProduct() {
        api retrofitProductAPI = url.getRetrofit().create(api.class);
        Call <List <Product_daraz>> ProductsCall = retrofitProductAPI.getallProduct();
        ProductsCall.enqueue(new Callback <List<Product_daraz>>() {
            @Override
            public void onResponse(Call<List<Product_daraz>> call, Response <List<Product_daraz>> response) {
                System.out.println("Product list " + response.body());
                ProductsAdapter recyclerviewAdapter = new ProductsAdapter(getApplicationContext(), response.body());
                RecyclerView.LayoutManager mlayoutManager = new GridLayoutManager(getApplicationContext(), 3);
                recyclerView.setLayoutManager(mlayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(recyclerviewAdapter);
                recyclerviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Product_daraz>> call, Throwable t) {

            }
        });
    }

}
