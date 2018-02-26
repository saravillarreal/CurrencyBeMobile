package com.sara.currencybemobile.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.sara.currencybemobile.beans.Product;
import com.sara.currencybemobile.beans.Rate;
import com.sara.currencybemobile.dao.ProductDao;
import com.sara.currencybemobile.globals.Constants;

/**
 * Created by saravillarreal on 25/2/18.
 */

@Database(entities = {Product.class, Rate.class}, version = 1, exportSchema = false)
public abstract class DatabaseApp extends RoomDatabase {
    private static DatabaseApp db;

    public abstract ProductDao productDao();

    public static DatabaseApp getAppDatabase(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(), DatabaseApp.class, Constants.NAME_DATABASE_LOCAL)

                    // don't do this on a real app!
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db;
    }
}
