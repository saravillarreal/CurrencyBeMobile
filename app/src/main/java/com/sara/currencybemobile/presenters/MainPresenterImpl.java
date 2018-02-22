package com.sara.currencybemobile.presenters;

import android.content.Context;

import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.interactors.MainInteractorImpl;
import com.sara.currencybemobile.interfaces.MainInteractorInterface;
import com.sara.currencybemobile.interfaces.MainOnListener;
import com.sara.currencybemobile.interfaces.MainPresenterInterface;
import com.sara.currencybemobile.interfaces.MainView;

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
        this.interactor = new MainInteractorImpl(getContext());
    }

    @Override
    public void getProducts() {
        interactor.getProducts(this);
    }

    @Override
    public Context getContext() {
        return view.getContext();
    }

    @Override
    public void resultProduct(List<Product> result) {

    }
}
