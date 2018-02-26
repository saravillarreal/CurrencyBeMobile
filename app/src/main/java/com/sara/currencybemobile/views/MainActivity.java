package com.sara.currencybemobile.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sara.currencybemobile.R;
import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.beans.Rate;
import com.sara.currencybemobile.databinding.ActivityMainBinding;
import com.sara.currencybemobile.globals.Constants;
import com.sara.currencybemobile.interfaces.MainPresenterInterface;
import com.sara.currencybemobile.interfaces.MainView;
import com.sara.currencybemobile.presenters.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements MainView {

    private MainPresenterInterface presenter;
    private static Context context;
    public static Context getContext(){
        return context;
    }
    ActivityMainBinding activityMainBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        deleteDatabase(Constants.NAME_DATABASE_LOCAL);
        context = getApplicationContext();
        //MVP Implementation
        presenter = new MainPresenterImpl(this);
        // Get ListRates
        presenter.getRates();


    }

    /**
     * Method to show Product in view
     * @param result
     */
    @Override
    public void showProducts(List<String> result) {
        List<String> resultAux = new ArrayList<>();
        resultAux.add("Seleccione");
        resultAux.addAll(result);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, resultAux);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityMainBinding.spnProduct.setAdapter(dataAdapter);
        activityMainBinding.spnProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    presenter.calculateRates( activityMainBinding.spnProduct.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**
     * Method to notify ListRates is loaded
     *
     */
    @Override
    public void notifyRatesReady() {
        presenter.getProducts();
    }

    /**
     * Method to show the final result
     *
     */
    @Override
    public void showResult(Float totalSum) {
        activityMainBinding.txtResult.setText(totalSum.toString()+  " €");
    }

}
