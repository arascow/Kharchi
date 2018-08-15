package com.arascow.aras.kharchi.Data.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.arascow.aras.kharchi.Data.DatabaseManager;
import com.arascow.aras.kharchi.Models.Income;
import com.arascow.aras.kharchi.Models.Income_Type;

import java.util.ArrayList;
import java.util.List;

public class IncomeTypeRepo {

    private Income income;

    public IncomeTypeRepo() {
        income = new Income();
    }

    public static String createTable() {
        return "CREATE TABLE " + Income_Type.TABLE_NAME +
                " (" + Income_Type.KEY_IncomeTypeId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Income_Type.KEY_IncomeName + " VARCHAR, " +
                Income_Type.KEY_IncomeImage + " BLOB )";
    }

    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Income_Type.TABLE_NAME, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public int insertInIncomeType(Income_Type incomeType) {
        int IncomeTypeId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Income_Type.KEY_IncomeName, incomeType.IncomeName);
        values.put(Income_Type.KEY_IncomeImage, incomeType.IncomeImg);


        // Inserting Row
        IncomeTypeId = (int) db.insert(Income_Type.TABLE_NAME, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return IncomeTypeId;
    }

    public List<Income_Type> getIncomeTypeList() {

        Income_Type GetIncomeType = new Income_Type();
        List<Income_Type> IncomeTypeLists = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Income_Type.TABLE_NAME;

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                GetIncomeType = new Income_Type();
                GetIncomeType.setIncomeName(cursor.getString(cursor.getColumnIndex(Income_Type.KEY_IncomeName)));
                GetIncomeType.setIncomeTypeId(cursor.getString(cursor.getColumnIndex(Income_Type.KEY_IncomeTypeId)));
                GetIncomeType.setIncomeImg(cursor.getBlob(cursor.getColumnIndex(Income_Type.KEY_IncomeImage)));


                IncomeTypeLists.add(GetIncomeType);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return IncomeTypeLists;
    }
}
