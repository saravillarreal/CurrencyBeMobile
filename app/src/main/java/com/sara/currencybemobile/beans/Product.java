package com.sara.currencybemobile.beans;

import java.io.Serializable;

/**
 * Created by saravillarreal on 21/2/18.
 */

public class Product implements Serializable{
    private String sku;
    private String amount;
    private String currency;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
