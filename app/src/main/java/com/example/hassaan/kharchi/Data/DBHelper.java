package com.example.hassaan.kharchi.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.hassaan.kharchi.App.app;
import com.example.hassaan.kharchi.Data.Repo.ExpenseRepo;
import com.example.hassaan.kharchi.Data.Repo.ExpenseTypeRepo;
import com.example.hassaan.kharchi.Data.Repo.IncomeRepo;
import com.example.hassaan.kharchi.Data.Repo.IncomeTypeRepo;
import com.example.hassaan.kharchi.Models.Expense;
import com.example.hassaan.kharchi.Models.Expense_Type;
import com.example.hassaan.kharchi.Models.Income;
import com.example.hassaan.kharchi.Models.Income_Type;
import com.example.hassaan.kharchi.R;

import java.io.ByteArrayOutputStream;

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
