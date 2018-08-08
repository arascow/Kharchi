package com.example.hassaan.kharchi.Models;

public class Expense_Type {

    public static final String TABLE_NAME = "EXPENSE_TYPE";
    public static final String KEY_ExpenseTypeID = "ExpenseTypeId";
    public static final String KEY_ExpenseName = "ExpenseName";
    public static final String KEY_ExpenseImage = "ExpenseImg";

    public String ExpenseTypeId;
    public String ExpenseName;
    public byte[] ExpenseImg;


    public Expense_Type() {

    }

    public String getExpenseTypeId() {
        return ExpenseTypeId;
    }

    public void setExpenseTypeId(String expenseTypeId) {
        ExpenseTypeId = expenseTypeId;
    }

    public String getExpenseName() {
        return ExpenseName;
    }

    public void setExpenseName(String expenseName) {
        ExpenseName = expenseName;
    }

    public byte[] getExpenseImg() {
        return ExpenseImg;
    }

    public void setExpenseImg(byte[] expenseImg) {
        ExpenseImg = expenseImg;
    }
}
