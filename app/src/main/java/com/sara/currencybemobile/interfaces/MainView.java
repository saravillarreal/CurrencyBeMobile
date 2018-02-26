package com.sara.currencybemobile.interfaces;

import android.content.Context;

import com.sara.currencybemobile.beans.Product;

import java.util.List;

/**
 * Created by saravillarreal on 22/2/18.
 */

public interface MainView {

    void showProducts(List<String> result);
    void notifyRatesReady ();
    void showResult(Float totalSum);

}
