package com.example.hassaan.kharchi.Models;

public class Expense {

    public static final String TABLE_NAME = "EXPENSE";
    public static final String KEY_ExpenseID = "ExpenseId";
    public static final String KEY_ExpenseType = "ExpenseType";
    public static final String KEY_Amount = "Amount";
    public static final String KEY_Date = "Date";


    public String ExpenseID;
    public String ExpenseType;
    public String Amount;
    public String Date;

    public Expense(String expenseID, String expenseType, String amount, String date) {
        ExpenseID = expenseID;
        ExpenseType = expenseType;
        Amount = amount;
        Date = date;
    }

    public Expense() {

    }

    public String getExpenseID() {
        return ExpenseID;
    }

    public void setExpenseID(String expenseID) {
        ExpenseID = expenseID;
    }

    public String getExpenseType() {
        return ExpenseType;
    }

    public void setExpenseType(String expenseType) {
        ExpenseType = expenseType;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
