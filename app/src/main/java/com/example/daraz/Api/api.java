package com.example.daraz.Api;

import com.example.daraz.Model.Product_daraz;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api {
    @GET("daraz_products")
    Call <List <Product_daraz>> getallProduct();
}
