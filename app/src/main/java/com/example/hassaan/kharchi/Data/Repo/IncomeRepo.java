package com.example.hassaan.kharchi.Data.Repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.hassaan.kharchi.Data.DatabaseManager;
import com.example.hassaan.kharchi.Models.Expense;
import com.example.hassaan.kharchi.Models.Income;
import com.example.hassaan.kharchi.Models.Income_Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.support.constraint.Constraints.TAG;

public class IncomeRepo {

    private Income income;

    public IncomeRepo() {
        income = new Income();
    }

    public static String createTable() {
        return "CREATE TABLE " + Income.TABLE_NAME +
                " (" + Income.KEY_IncomeID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                Income.KEY_IncomeType + " VARCHAR, " +
                Income.KEY_Amount + " VARCHAR, " +
                Income.KEY_Date + " DATE, FOREIGN KEY(" + Income.KEY_IncomeType + ")REFERENCES " + Income_Type.TABLE_NAME + "(" + Income_Type.KEY_IncomeTypeId + ") )";
    }


    public int insertInIncome(Income income) {
        int IncomeId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Income.KEY_Amount, income.Amount);
        values.put(Income.KEY_IncomeType, income.IncomeType);
        values.put(Income.KEY_Date, income.Date);

        // Inserting Row
        IncomeId = (int) db.insert(Income.TABLE_NAME, null, values);
        DatabaseManager.getInstance().closeDatabase();
        return IncomeId;
    }

    public int updateInIncome(Income income) {
        int IncomeId;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Income.KEY_IncomeID, income.IncomeId);
        values.put(Income.KEY_Amount, income.Amount);
        values.put(Income.KEY_IncomeType, income.IncomeType);
        values.put(Income.KEY_Date, income.Date);

        // updating Row
        IncomeId = db.update(Income.TABLE_NAME, values, Income.KEY_IncomeID + " = " + income.getIncomeId(), null);
        DatabaseManager.getInstance().closeDatabase();
        return IncomeId;
    }

    public void delete() {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Income.TABLE_NAME, null, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public int getIncomeSum() {

        Income GetIncome = new Income();
        int SUMINCOME = 0;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Income.TABLE_NAME;

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                GetIncome = new Income();
                GetIncome.setIncomeId(cursor.getString(cursor.getColumnIndex(Income.KEY_IncomeID)));
                GetIncome.setAmount(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                GetIncome.setIncomeType(cursor.getString(cursor.getColumnIndex(Income.KEY_IncomeType)));
                GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));

                SUMINCOME += Integer.parseInt(GetIncome.Amount);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return SUMINCOME;
    }

    public int getIncomeSumbyMonth() {

        Income GetIncome = new Income();
        int SUMINCOME = 0;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Income.TABLE_NAME + " ORDER BY " + Income.KEY_Date + " DESC";

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            String DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
            Date date;
            String month = "";
            String currentMonth = "";
            Date currentTime = Calendar.getInstance().getTime();
            SimpleDateFormat Format = new SimpleDateFormat("EEE, dd/MM/yyyy");
            SimpleDateFormat Formatout = new SimpleDateFormat("/MM/yyyy");

            try {
                date = Format.parse(DBDate);
                month = Formatout.format(date);
                currentMonth = Formatout.format(currentTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            do {
                if (month.equals(currentMonth)) {
                    GetIncome = new Income();
                    GetIncome.setIncomeId(cursor.getString(cursor.getColumnIndex(Income.KEY_IncomeID)));
                    GetIncome.setAmount(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                    GetIncome.setIncomeType(cursor.getString(cursor.getColumnIndex(Income.KEY_IncomeType)));
                    GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));

                    SUMINCOME += Integer.parseInt(GetIncome.Amount);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return SUMINCOME;
    }

    public List<Income> getIncomeList() {

        Income GetIncome = new Income();
        List<Income> incomeList = new ArrayList<>();


        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Income.TABLE_NAME;

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {

                GetIncome = new Income();
                GetIncome.setIncomeId(cursor.getString(cursor.getColumnIndex(Income.KEY_IncomeID)));
                GetIncome.setAmount(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                GetIncome.setIncomeType(cursor.getString(cursor.getColumnIndex(Income.KEY_IncomeType)));
                GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));

                incomeList.add(GetIncome);

            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return incomeList;
    }

    public List<Income> getIncomeByMonth() {

        Income GetIncome;
        List<Income> incomeList = new ArrayList<>();
        int Sum_of_one_month = 0;
        List<Integer> SUMINCOMEBYMONTH = new ArrayList<>();


        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Income.TABLE_NAME + " ORDER BY " + Income.KEY_Date + " DESC";

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            String DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
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
                GetIncome = new Income();
                if (DBDate.contains(month)) {
                    Sum_of_one_month = Sum_of_one_month + Integer.parseInt(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                    if (cursor.isLast()) {

                        GetIncome.setAmount(String.valueOf(Sum_of_one_month));
                        DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                        GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));
                        try {
                            date = Format.parse(DBDate);
                            month = Formatout.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        incomeList.add(GetIncome);
                    }

                } else if (!DBDate.contains(month)) {
                    GetIncome.setAmount(String.valueOf(Sum_of_one_month));
                    cursor.moveToPrevious();
                    GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));
                    cursor.moveToNext();
                    DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                    try {
                        date = Format.parse(DBDate);
                        month = Formatout.format(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    incomeList.add(GetIncome);
                    Sum_of_one_month = 0;
                    Sum_of_one_month = Sum_of_one_month + Integer.parseInt(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                    if (cursor.isLast()) {
                        GetIncome = new Income();
                        GetIncome.setAmount(String.valueOf(Sum_of_one_month));
                        DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                        GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));
                        try {
                            date = Format.parse(DBDate);
                            month = Formatout.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        incomeList.add(GetIncome);
                    }
                }


            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return incomeList;
    }

    public List<Income> getIncomeByDay() {

        Income GetIncome;
        List<Income> incomeList = new ArrayList<>();
        int Sum_of_one_day = 0;
        List<Integer> SUMINCOMEBYMONTH = new ArrayList<>();


        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Income.TABLE_NAME + " ORDER BY " + Income.KEY_Date + " DESC";

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            String DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
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
                DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                Log.i(TAG, "getIncomeByDay: " + DBDate);
                GetIncome = new Income();
                if (DBDate.contains(month)) {
                    Sum_of_one_day = Sum_of_one_day + Integer.parseInt(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                    if (cursor.isLast()) {
                        GetIncome.setAmount(String.valueOf(Sum_of_one_day));
                        DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                        GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));
                        try {
                            date = Format.parse(DBDate);
                            month = Formatout.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        incomeList.add(GetIncome);
                        Sum_of_one_day = 0;
                    }

                } else if (!DBDate.contains(month)) {
                    GetIncome.setAmount(String.valueOf(Sum_of_one_day));
                    cursor.moveToPrevious();
                    DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                    GetIncome.setDate(DBDate);
                    cursor.moveToNext();
                    DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                    try {
                        date = Format.parse(DBDate);
                        month = Formatout.format(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    incomeList.add(GetIncome);
                    Sum_of_one_day = 0;
                    Sum_of_one_day = Sum_of_one_day + Integer.parseInt(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));

                    if (cursor.isLast()) {
                        GetIncome = new Income();
                        GetIncome.setAmount(String.valueOf(Sum_of_one_day));
                        DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                        GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));
                        try {
                            date = Format.parse(DBDate);
                            month = Formatout.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        incomeList.add(GetIncome);
                        Sum_of_one_day = 0;
                    }

                }


            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return incomeList;
    }

    public List<Income> getIncomeByWeek() {

        Income GetIncome;
        List<Income> incomeList = new ArrayList<>();
        int Sum_of_one_day = 0;
        List<Integer> SUMINCOMEBYMONTH = new ArrayList<>();


        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT * FROM " + Income.TABLE_NAME + " ORDER BY " + Income.KEY_Date + " DESC";

        Log.d("TAG", selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            String DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
            Date date;
            String month = "";
            SimpleDateFormat Format = new SimpleDateFormat("EEEE, dd-MM-yyyy");
            SimpleDateFormat Formatout = new SimpleDateFormat("dd/");

            try {
                date = Format.parse(DBDate);
                month = Formatout.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            do {
                GetIncome = new Income();
                if (DBDate.contains(month)) {
                    Sum_of_one_day = Sum_of_one_day + Integer.parseInt(cursor.getString(cursor.getColumnIndex(Income.KEY_Amount)));
                    if (cursor.isLast()) {

                        GetIncome.setAmount(String.valueOf(Sum_of_one_day));
                        DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                        GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));
                        try {
                            date = Format.parse(DBDate);
                            month = Formatout.format(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        incomeList.add(GetIncome);
                    }

                } else {
                    GetIncome.setAmount(String.valueOf(Sum_of_one_day));
                    DBDate = cursor.getString(cursor.getColumnIndex(Income.KEY_Date));
                    GetIncome.setDate(cursor.getString(cursor.getColumnIndex(Income.KEY_Date)));
                    try {
                        date = Format.parse(DBDate);
                        month = Formatout.format(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    incomeList.add(GetIncome);
                }


            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return incomeList;
    }
}
