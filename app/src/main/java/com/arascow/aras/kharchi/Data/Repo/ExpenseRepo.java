package com.arascow.aras.kharchi.Data.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.arascow.aras.kharchi.Data.DatabaseManager;
import com.arascow.aras.kharchi.Models.Expense;
import com.arascow.aras.kharchi.Models.Expense_Type;
import com.arascow.aras.kharchi.Models.Income;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ExpenseRepo {

    private Expense expense;

    public ExpenseRepo() {
        expense = new Expense();
    }


    public static String createTable() {
        return "CREATE TABLE " + Expense.TABLE_NAME +
                " (" + Expense.KEY_ExpenseID + " INTEGER PRIMARY KEY ," +
                Expense.KEY_ExpenseType + " VARCHAR, " +
                Expense.KEY_Amount + " VARCHAR, " +
                Expense.KEY_Date + " DATE , FOREIGN KEY(" + Expense.KEY_ExpenseType + ")REFERENCES " + Expense_Type.TABLE_NAME + "(" + Expense_Type.KEY_ExpenseTypeID + ") )";
    }

    public int insertInExpense(Expense expense) {
        int expenseId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Expense.KEY_ExpenseID, expense.ExpenseID);
        values.put(Expense.KEY_ExpenseType, expense.ExpenseType);
        values.put(Expense.KEY_Amount, expense.Amount);
        values.put(Expense.KEY_Date, expense.Date);

        // Inserting Row
        expenseId = (int) db.insert(Expense.TABLE_NAME, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return expenseId;
    }

    public int updateInExpense(Expense expense) {
        int expenseId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Expense.KEY_ExpenseID, expense.ExpenseID);
        values.put(Expense.KEY_ExpenseType, expense.ExpenseType);
        values.put(Expense.KEY_Amount, expense.Amount);
        values.put(Expense.KEY_Date, expense.Date);

        // update-ing Row
        expenseId = db.update(Expense.TABLE_NAME, values, Expense.KEY_ExpenseID + " = " + expense.getExpenseID(), null);
        DatabaseManager.getInstance().closeDatabase();
        return expenseId;
    }

    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Expense.TABLE_NAME, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public int getExpenseSum() {

        Expense GetExpenses = new Expense();
        int SUMEXPENSE = 0;
        List<Expense> ExpenseLists = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Expense.TABLE_NAME;

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                GetExpenses = new Expense();
                GetExpenses.setExpenseID(cursor.getString(cursor.getColumnIndex(Expense.KEY_ExpenseID)));
                GetExpenses.setAmount(cursor.getString(cursor.getColumnIndex(Expense.KEY_Amount)));
                GetExpenses.setExpenseType(cursor.getString(cursor.getColumnIndex(Expense.KEY_ExpenseType)));
                GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));

                SUMEXPENSE += Integer.parseInt(GetExpenses.Amount);
                ExpenseLists.add(GetExpenses);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return SUMEXPENSE;
    }

    public int getExpenseSumbyMonth() {

        Expense GetExpenses = new Expense();
        int SUMEXPENSE = 0;
        List<Expense> ExpenseLists = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Expense.TABLE_NAME + " ORDER BY " + Expense.KEY_Date + " DESC";

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            String DBDate = cursor.getString(cursor.getColumnIndex(Expense.KEY_Date));
            Date date;
            String month = "";

            Date currentTime = Calendar.getInstance().getTime();
            String currentmonth = "";
            SimpleDateFormat Format = new SimpleDateFormat("EEE, dd/MM/yyyy");
            SimpleDateFormat Formatout = new SimpleDateFormat("/MM/yyyy");

            try {
                date = Format.parse(DBDate);
                month = Formatout.format(date);
                currentmonth = Formatout.format(currentTime);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "getExpenseSumbyMonth: " + currentmonth);

            Log.i(TAG, "getExpenseSumbyMonth: " + month);
            do {
                try {
                    date = Format.parse(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));
                    month = Formatout.format(date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (month.equals(currentmonth)) {
                    GetExpenses = new Expense();
                    GetExpenses.setExpenseID(cursor.getString(cursor.getColumnIndex(Expense.KEY_ExpenseID)));
                    GetExpenses.setAmount(cursor.getString(cursor.getColumnIndex(Expense.KEY_Amount)));
                    GetExpenses.setExpenseType(cursor.getString(cursor.getColumnIndex(Expense.KEY_ExpenseType)));
                    GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));

                    SUMEXPENSE += Integer.parseInt(GetExpenses.Amount);
                    ExpenseLists.add(GetExpenses);
                    try {
                        date = Format.parse(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));
                        month = Formatout.format(date);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        date = Format.parse(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));
                        month = Formatout.format(date);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (month.equals(currentmonth)) {
                        GetExpenses = new Expense();
                        GetExpenses.setExpenseID(cursor.getString(cursor.getColumnIndex(Expense.KEY_ExpenseID)));
                        GetExpenses.setAmount(cursor.getString(cursor.getColumnIndex(Expense.KEY_Amount)));
                        GetExpenses.setExpenseType(cursor.getString(cursor.getColumnIndex(Expense.KEY_ExpenseType)));
                        GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));

                        SUMEXPENSE += Integer.parseInt(GetExpenses.Amount);
                        ExpenseLists.add(GetExpenses);
                    }
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return SUMEXPENSE;
    }

    public List<Expense> getExpenseList() {

        Expense GetExpenses = new Expense();
        List<Expense> ExpenseLists = new ArrayList<>();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Expense.TABLE_NAME;

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                GetExpenses = new Expense();
                GetExpenses.setExpenseID(cursor.getString(cursor.getColumnIndex(Expense.KEY_ExpenseID)));
                GetExpenses.setAmount(cursor.getString(cursor.getColumnIndex(Expense.KEY_Amount)));
                GetExpenses.setExpenseType(cursor.getString(cursor.getColumnIndex(Expense.KEY_ExpenseType)));
                GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));


                ExpenseLists.add(GetExpenses);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return ExpenseLists;
    }

    public List<Expense> getExpensebyMonth() {

        Expense GetExpenses = new Expense();
        List<Expense> ExpenseLists = new ArrayList<>();
        int Sum_of_one_month = 0;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Expense.TABLE_NAME + " ORDER BY " + Expense.KEY_Date + " DESC";

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            String DBDate = cursor.getString(cursor.getColumnIndex(Expense.KEY_Date));
            Date date;
            String month = "";
            SimpleDateFormat Format = new SimpleDateFormat("EEE, dd/MM/yyyy");
            SimpleDateFormat Formatout = new SimpleDateFormat("/MM/");

            try {
                date = Format.parse(DBDate);
                month = Formatout.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            do {
                DBDate = cursor.getString(cursor.getColumnIndex(Expense.KEY_Date));
                GetExpenses = new Expense();
                if (DBDate.contains(month)) {
                    Sum_of_one_month = Sum_of_one_month + Integer.parseInt(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                    if (cursor.isLast()) {

                        GetExpenses.setAmount(String.valueOf(Sum_of_one_month));
                        DBDate = cursor.getString(cursor.getColumnIndex(Expense.KEY_Date));
                        GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));
                        try {
                            date = Format.parse(DBDate);
                            month = Formatout.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ExpenseLists.add(GetExpenses);
                    }

                } else if (!DBDate.contains(month)) {

                    GetExpenses.setAmount(String.valueOf(Sum_of_one_month));
                    cursor.moveToPrevious();
                    GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));
                    cursor.moveToNext();
                    DBDate = cursor.getString(cursor.getColumnIndex(Expense.KEY_Date));
                    try {
                        date = Format.parse(DBDate);
                        month = Formatout.format(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    ExpenseLists.add(GetExpenses);
                    Sum_of_one_month = 0;
                    Sum_of_one_month = Sum_of_one_month + Integer.parseInt(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                    if (cursor.isLast()) {
                        GetExpenses = new Expense();
                        GetExpenses.setAmount(String.valueOf(Sum_of_one_month));
                        DBDate = cursor.getString(cursor.getColumnIndex(Expense.KEY_Date));
                        GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));
                        try {
                            date = Format.parse(DBDate);
                            month = Formatout.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ExpenseLists.add(GetExpenses);
                    }
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return ExpenseLists;
    }

    public List<Expense> getExpensebyDay() {

        Expense GetExpenses = new Expense();
        List<Expense> ExpenseLists = new ArrayList<>();
        int Sum_of_one_day = 0;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Expense.TABLE_NAME + " ORDER BY " + Expense.KEY_Date + " DESC";

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            String DBDate = cursor.getString(cursor.getColumnIndex(Expense.KEY_Date));
            Date date;
            String month = "";
            SimpleDateFormat Format = new SimpleDateFormat("EEE, dd/MM/yyyy");
            SimpleDateFormat Formatout = new SimpleDateFormat("dd/MM/yyyy");

            try {
                date = Format.parse(DBDate);
                month = Formatout.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            do {
                DBDate = cursor.getString(cursor.getColumnIndex(Expense.KEY_Date));
                GetExpenses = new Expense();
                if (DBDate.contains(month)) {
                    Sum_of_one_day = Sum_of_one_day + Integer.parseInt(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                    if (cursor.isLast()) {
                        GetExpenses.setAmount(String.valueOf(Sum_of_one_day));
                        DBDate = cursor.getString(cursor.getColumnIndex(Expense.KEY_Date));
                        GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));
                        try {
                            date = Format.parse(DBDate);
                            month = Formatout.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ExpenseLists.add(GetExpenses);
                    }

                } else if (!DBDate.contains(month)) {

                    GetExpenses.setAmount(String.valueOf(Sum_of_one_day));
                    cursor.moveToPrevious();
                    GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));
                    cursor.moveToNext();
                    ExpenseLists.add(GetExpenses);
                    try {
                        date = Format.parse(DBDate);
                        month = Formatout.format(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Sum_of_one_day = 0;
                    Sum_of_one_day = Sum_of_one_day + Integer.parseInt(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));

                    if (cursor.isLast()) {
                        GetExpenses = new Expense();
                        GetExpenses.setAmount(String.valueOf(Sum_of_one_day));
                        GetExpenses.setDate(cursor.getString(cursor.getColumnIndex(Expense.KEY_Date)));
                        try {
                            date = Format.parse(DBDate);
                            month = Formatout.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        ExpenseLists.add(GetExpenses);
                    }
                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return ExpenseLists;
    }

}
