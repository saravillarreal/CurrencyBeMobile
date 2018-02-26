package com.sara.currencybemobile.interfaces;

import android.content.Context;

/**
 * Created by saravillarreal on 22/2/18.
 */

public interface MainPresenterInterface  {

    void getProducts();
    void getRates();
    void calculateRates(String product);

}
