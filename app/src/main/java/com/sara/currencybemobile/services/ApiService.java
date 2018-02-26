package com.sara.currencybemobile.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sara.currencybemobile.beans.APIResponse;
import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.beans.Rate;
import com.sara.currencybemobile.globals.Urls;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by saravillarreal on 22/2/18.
 */

public class ApiService {
    private static ApiService apiInstance;
    private Api mApi;

    /**
     * Private constructor
     */
    private ApiService(boolean catalogue) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        String baseUrl;

        baseUrl = Urls.BASE_URL;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mApi = retrofit.create(Api.class);
    }

    public static ApiService newInstance(boolean catalogue) {

        ApiService fragment = new ApiService(catalogue);
        return fragment;
    }


    /**
     * Singlenton Pattern
     *
     * @return ApiService {@link ApiService}
     */
    public static final synchronized ApiService getInstance(boolean catalogue) {
        if (apiInstance == null) {
            apiInstance = new ApiService(catalogue);
        }
        return apiInstance;
    }

    /**
     * Getting the product
     *
     */
    public Call<List<Product>> getProducts(String header) {
        return mApi.getProducts(header);
    }

    /**
     * Getting the rates
     *
     */
    public Call<List<Rate>> getRates(String header) {
        return mApi.getRates(header);
    }



}
