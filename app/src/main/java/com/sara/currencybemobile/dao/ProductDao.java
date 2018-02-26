package com.sara.currencybemobile.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.beans.Rate;

import java.util.List;

/**
 * Created by saravillarreal on 25/2/18.
 */
@Dao
public interface ProductDao {
    @Insert
    void insertListProduct(List<Product> productList);

    @Insert
    void insertListRate(List<Rate> rateList);

    @Query("SELECT DISTINCT sku FROM Product" )
    List<String> getAllNameOfProduct();

    @Query("SELECT * FROM Product WHERE sku = :nameProduct")
    List<Product> getListValuesByNameProduct(String nameProduct);

    @Query("SELECT * FROM Rate")
    List<Rate> getListRate();

}
