package com.sara.currencybemobile.presenters;

import android.content.Context;

import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.interactors.MainInteractorImpl;
import com.sara.currencybemobile.interfaces.MainInteractorInterface;
import com.sara.currencybemobile.interfaces.MainOnListener;
import com.sara.currencybemobile.interfaces.MainPresenterInterface;
import com.sara.currencybemobile.interfaces.MainView;
import com.sara.currencybemobile.views.MainActivity;

import java.util.List;

/**
 * Created by saravillarreal on 22/2/18.
 */

public class MainPresenterImpl implements MainPresenterInterface, MainOnListener {

    //region #### DECLARATIONS OF VARIABLES ####
    private MainView view;
    private MainInteractorInterface interactor;
    //endregion



    public MainPresenterImpl(MainView view) {
        this.view = view;
        this.interactor = new MainInteractorImpl(MainActivity.getContext());
    }

    @Override
    public void getProducts() {
        interactor.getProducts(this);
    }

    @Override
    public void getRates() {
        interactor.getRates(this);
    }

    @Override
    public void calculateRates(String product) {
        interactor.calculateRate(this, product);
    }



    @Override
    public void resultProduct(List<String> result) {

        view.showProducts(result);
    }

    @Override
    public void resultRates() {
        view.notifyRatesReady();
    }

    @Override
    public void resultCalculeRate(Float totalSum) {
        view.showResult(totalSum);
    }
}
