package com.sara.currencybemobile.interfaces;

/**
 * Created by saravillarreal on 22/2/18.
 */

public interface MainInteractorInterface {
    void getProducts(MainOnListener listener);
    void getRates (MainOnListener listener);
    void calculateRate (MainOnListener listener, String product);
}
