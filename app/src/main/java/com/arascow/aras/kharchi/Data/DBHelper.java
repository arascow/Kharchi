package com.arascow.aras.kharchi.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arascow.aras.kharchi.App.app;
import com.arascow.aras.kharchi.Data.Repo.ExpenseRepo;
import com.arascow.aras.kharchi.Data.Repo.ExpenseTypeRepo;
import com.arascow.aras.kharchi.Data.Repo.IncomeRepo;
import com.arascow.aras.kharchi.Data.Repo.IncomeTypeRepo;

public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION =4;
    // Database Name
    private static final String DATABASE_NAME = "Kharchi.db";
    private Context context;



    public DBHelper() {
        super(app.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        context=app.getContext();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ExpenseRepo.createTable());
        db.execSQL(ExpenseTypeRepo.createTable());
        db.execSQL(IncomeRepo.createTable());
        db.execSQL(IncomeTypeRepo.createTable());


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
