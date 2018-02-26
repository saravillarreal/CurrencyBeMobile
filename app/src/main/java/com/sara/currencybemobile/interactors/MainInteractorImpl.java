package com.sara.currencybemobile.interactors;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.beans.Rate;
import com.sara.currencybemobile.data.DatabaseApp;
import com.sara.currencybemobile.interfaces.MainInteractorInterface;
import com.sara.currencybemobile.interfaces.MainOnListener;
import com.sara.currencybemobile.services.ApiService;
import com.sara.currencybemobile.views.MainActivity;

import java.lang.reflect.Type;
import java.util.List;


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
    public  static List<Rate> rateList;


    /**
     * Method to get the products from service
     *@param listener
     */
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
                        //SQLITE
                        DatabaseApp db = DatabaseApp.getAppDatabase(MainActivity.getContext());
                        // Product Insert
                        db.productDao().insertListProduct(productList);
                        listener.resultProduct(db.productDao().getAllNameOfProduct());

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


    /**
     * Method to get the rates from service
     *@param listener
     */
    @Override
    public void getRates(MainOnListener listener) {
        // callService
        //get unique Instance of ApiService
        apiService = apiService.newInstance(false); // boolean false, url coming from hxc
        Call<List<Rate>> ratesService =  apiService.getRates("application/json");
        ratesService.enqueue(new retrofit2.Callback<List<Rate>>() {

            @Override
            public void onResponse(Call<List<Rate>> call, Response<List<Rate>> response) {
                switch (response.code()) {
                    case 200:
                        //((BaseActivity)context).dismissDialog();
                        Gson gson = new Gson();
                        //Convert response.body().getResults() to JsonArray because coming in LinkedTreeMap
                        JsonArray jsonArray = gson.toJsonTree(response.body()).getAsJsonArray();
                        Type type = new TypeToken<List<Rate>>() {}.getType();
                        //Transform the JsonArray to List <Catalogue>
                        List<Rate> rateList = gson.fromJson(jsonArray, type);
                        //SQLITE
                        DatabaseApp db = DatabaseApp.getAppDatabase(MainActivity.getContext());
                        // Product Insert
                        db.productDao().insertListRate(rateList);
                        listener.resultRates();

                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Rate>> call, Throwable t) {
                Log.i("onFailure", "loadRates");

            }
        });
    }


    /**
     * Method to calculate the rate of products
     *@param listener
     */
    @Override
    public void calculateRate(MainOnListener listener, String nameProduct) {
        //SQLITE
        DatabaseApp db = DatabaseApp.getAppDatabase(MainActivity.getContext());
        List<Product> productList = db.productDao().getListValuesByNameProduct(nameProduct);
       rateList = db.productDao().getListRate();
       Float sum = 0F;
        if (productList.size()>0){
            for (int i = 0; i< productList.size(); i++){
                Log.i("product list", "Position" + i+ " Amount" +
                        productList.get(i).getAmount() + " Currency" + productList.get(i) );
                //Call method recursive to calcule value from Product List
                sum += calculateRateRecursive(Float.parseFloat(productList.get(i).getAmount()), productList.get(i).getCurrency());
                Log.i("sum", sum+ "");
            }
            //Toast.makeText(MainActivity.getContext(), String.valueOf(sum), Toast.LENGTH_LONG).show();
            listener.resultCalculeRate(sum);
        }
    }

    /**
     * Method return the value calculate of the rate on the list
     *@param val
     * @param from
     */
    private float calculateRateRecursive (Float val, String from){
        Log.i("calculateRateRecursive", "value: " + val+"" + " from: " + from);
        if (!from.equalsIgnoreCase("EUR")){
            for (int i= 0; i< rateList.size(); i++){
                Log.i("calculateRateRecursive2", "Position" + i + " TO" +
                        rateList.get(i).getTo() + " Currency" + rateList.get(i).getRate() );
                if (from.equalsIgnoreCase( rateList.get(i).getFrom())){
                    if (rateList.get(i).getTo().equalsIgnoreCase("EUR")){
                        val = val *rateList.get(i).getRate();
                        return val;
                    }
                    else{
                        return calculateRateRecursive(val * rateList.get(i).getRate(), rateList.get(i).getTo());
                    }
                }
            }
        }
        return val;

    }
}
