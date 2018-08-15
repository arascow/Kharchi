package com.arascow.aras.kharchi.Models;

public class Income {

    public static final String TABLE_NAME = "INCOME";
    public static final String KEY_IncomeID = "IncomeId";
    public static final String KEY_IncomeType = "IncomeType";
    public static final String KEY_Amount = "Amount";
    public static final String KEY_Date = "Date";


    public String IncomeId;
    public String IncomeType;
    public String Amount;
    public String Date;

    public Income(String incomeId, String incomeType, String amount, String date) {
        IncomeId = incomeId;
        IncomeType = incomeType;
        Amount = amount;
        Date = date;
    }

    public Income() {

    }

    public String getIncomeId() {
        return IncomeId;
    }

    public void setIncomeId(String incomeId) {
        IncomeId = incomeId;
    }

    public String getIncomeType() {
        return IncomeType;
    }

    public void setIncomeType(String incomeType) {
        IncomeType = incomeType;
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
