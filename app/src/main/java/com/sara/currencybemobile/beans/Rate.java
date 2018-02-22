package com.sara.currencybemobile.beans;

import java.io.Serializable;

/**
 * Created by saravillarreal on 21/2/18.
 */

public class Rate implements Serializable{
    private String from;
    private String to;
    private Float rate;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
