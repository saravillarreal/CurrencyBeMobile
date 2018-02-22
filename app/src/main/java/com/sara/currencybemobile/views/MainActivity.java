package com.sara.currencybemobile.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sara.currencybemobile.R;
import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.beans.Rate;
import com.sara.currencybemobile.interfaces.MainPresenterInterface;
import com.sara.currencybemobile.interfaces.MainView;
import com.sara.currencybemobile.presenters.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements MainView {

    List<Rate> rateList = new ArrayList();
    Float sumTotal = 0f;
    Float sumTotal2 = 0f;
    private MainPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenterImpl(this);

        Rate rate = new Rate();
        rate.setFrom("USD");
        rate.setTo("EUR");
        rate.setRate(0.58F);
        Rate rate1 = new Rate();
        rate1.setFrom("EUR");
        rate1.setTo("USD");
        rate1.setRate(1.72F);
        Rate rate2 = new Rate();
        rate2.setFrom("USD");
        rate2.setTo("AUD");
        rate2.setRate(0.97F);
        Rate rate3 = new Rate();
        rate3.setFrom("AUD");
        rate3.setTo("USD");
        rate3.setRate(1.03F);
        Rate rate4 = new Rate();
        rate4.setFrom("EUR");
        rate4.setTo("CAD");
        rate4.setRate(0.9F);
        Rate rate5 = new Rate();
        rate5.setFrom("CAD");
        rate5.setTo("EUR");
        rate5.setRate(1.11F);
        rateList.add(rate);
        rateList.add(rate1);
        rateList.add(rate2);
        rateList.add(rate3);
        rateList.add(rate4);
        rateList.add(rate5);

        Product product = new Product();
        product.setCurrency("USD");
        product.setAmount("10.00");
        Float sum = calculateRate(Float.parseFloat(product.getAmount()), product.getCurrency());
        sumTotal = sum;


        Product product2 = new Product();
        product2.setCurrency("EUR");
        product2.setAmount("7.63");

        Float sum2 = calculateRate(Float.parseFloat(product2.getAmount()), product2.getCurrency());
        sumTotal2 =  sum2;
        Float aux = sumTotal + sumTotal2;

        Toast.makeText(this, "sumtotal" +aux.toString(), Toast.LENGTH_LONG).show();
        presenter.getProducts();
    }

    private float calculateRate (Float val, String from){
        if (!from.equalsIgnoreCase("EUR")){
        for (int i= 0; i< rateList.size(); i++){
            if (from.equalsIgnoreCase( rateList.get(i).getFrom())){
                if (rateList.get(i).getTo().equalsIgnoreCase("EUR")){
                    return val * rateList.get(i).getRate();
                }
                else{
                    return calculateRate(val * rateList.get(i).getRate(), rateList.get(i).getTo());
                }
            }
        }
        }
        return val;

    }

    @Override
    public void showProducts() {

    }

    @Override
    public Context getContext() {
        return this;
    }
}
