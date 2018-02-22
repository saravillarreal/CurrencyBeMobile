package com.sara.currencybemobile.interactors;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.sara.currencybemobile.beans.APIResponse;
import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.interfaces.MainInteractorInterface;
import com.sara.currencybemobile.interfaces.MainOnListener;
import com.sara.currencybemobile.services.ApiService;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by saravillarreal on 22/2/18.
 */

public class MainInteractorImpl extends Application implements MainInteractorInterface {

    private Context c;
    public MainInteractorImpl(Context c) {
            this.c = c;
        }
    public static ApiService apiService;


    @Override
    public void getProducts(final MainOnListener listener) {
        // callService
        //get unique Instance of ApiService
        apiService = apiService.newInstance(false); // boolean false, url coming from hxc
        Call<List<Product>> catalogueService =  apiService.getProducts("application/json");
        catalogueService.enqueue(new retrofit2.Callback<List<Product>>() {

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                switch (response.code()) {
                    case 200:
                        //((BaseActivity)context).dismissDialog();
                        Gson gson = new Gson();
                        //Convert response.body().getResults() to JsonArray because coming in LinkedTreeMap
                        JsonArray jsonArray = gson.toJsonTree(response.body()).getAsJsonArray();
                        Type type = new TypeToken<List<Product>>() {}.getType();
                        //Transform the JsonArray to List <Catalogue>
                        List<Product> productList = gson.fromJson(jsonArray, type);
                        listener.resultProduct(productList);

                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i("onFailure", "loadProduct");

            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void groupProduct (List<Product> productList){
      /*  //group by price
        Map<String, List<Product>> groupByPriceMap =
                productList.stream().collect(Collectors.groupingBy(productList::g));

        System.out.println(groupByPriceMap);

        // group by price, uses 'mapping' to convert List<Item> to Set<String>
        Map<String, Set<String>> result =
                productList.stream().collect(
                        Collectors.groupingBy(productList::getSku,
                                Collectors.mapping(productList::getName, Collectors.toSet())
                        )
                );*/
    }
}
