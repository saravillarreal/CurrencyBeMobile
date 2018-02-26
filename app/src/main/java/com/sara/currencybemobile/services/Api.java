package com.sara.currencybemobile.services;

import com.sara.currencybemobile.beans.APIResponse;
import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.beans.Rate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by saravillarreal on 22/2/18.
 */

public interface Api {
    @GET("transactions")
    Call<List<Product>> getProducts(@Header("Accept") String header);

    @GET("rates")
    Call<List<Rate>> getRates(@Header("Accept") String header);

}
