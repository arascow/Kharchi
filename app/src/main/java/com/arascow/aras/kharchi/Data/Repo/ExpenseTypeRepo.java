package com.arascow.aras.kharchi.Data.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.arascow.aras.kharchi.Data.DatabaseManager;
import com.arascow.aras.kharchi.Models.Expense_Type;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTypeRepo {

    public ExpenseTypeRepo() {
    }

    public static String createTable() {
        return " CREATE TABLE " + Expense_Type.TABLE_NAME +
                " (" + Expense_Type.KEY_ExpenseTypeID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Expense_Type.KEY_ExpenseName + " VARCHAR," +
                Expense_Type.KEY_ExpenseImage + " BLOB )";
    }

    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Expense_Type.TABLE_NAME, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public int insertInExpenseType(Expense_Type expenseType) {
        int expenseTypeId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Expense_Type.KEY_ExpenseName, expenseType.ExpenseName);
        values.put(Expense_Type.KEY_ExpenseImage,expenseType.ExpenseImg);

        // Inserting Row
        expenseTypeId = (int) db.insert(Expense_Type.TABLE_NAME, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return expenseTypeId;
    }

    public List<Expense_Type> getExpenseTypeList() {

        Expense_Type GetExpenseType = new Expense_Type();
        List<Expense_Type> ExpenseTypeLists = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Expense_Type.TABLE_NAME;

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                GetExpenseType = new Expense_Type();
                GetExpenseType.setExpenseName(cursor.getString(cursor.getColumnIndex(Expense_Type.KEY_ExpenseName)));
                GetExpenseType.setExpenseTypeId(cursor.getString(cursor.getColumnIndex(Expense_Type.KEY_ExpenseTypeID)));
                GetExpenseType.setExpenseImg(cursor.getBlob(cursor.getColumnIndex(Expense_Type.KEY_ExpenseImage)));


                ExpenseTypeLists.add(GetExpenseType);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return ExpenseTypeLists;
    }
}
