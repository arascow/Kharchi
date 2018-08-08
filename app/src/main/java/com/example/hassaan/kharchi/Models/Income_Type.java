package com.example.hassaan.kharchi.Models;

public class Income_Type {

    public static final String TABLE_NAME = "INCOME_TYPE";
    public static final String KEY_IncomeTypeId = "IncomeTypeId";
    public static final String KEY_IncomeName = "IncomeName";
    public static final String KEY_IncomeImage = "IncomeImg";

    public String IncomeTypeId;
    public String IncomeName;
    public byte[] IncomeImg;

    public Income_Type(String incomeTypeId, String incomeName, byte[] incomeImg) {
        IncomeTypeId = incomeTypeId;
        IncomeName = incomeName;
        IncomeImg = incomeImg;
    }

    public Income_Type() {

    }

    public byte[] getIncomeImg() {
        return IncomeImg;
    }

    public void setIncomeImg(byte[] incomeImg) {
        IncomeImg = incomeImg;
    }

    public String getIncomeTypeId() {
        return IncomeTypeId;
    }

    public void setIncomeTypeId(String incomeTypeId) {
        IncomeTypeId = incomeTypeId;
    }

    public String getIncomeName() {
        return IncomeName;
    }

    public void setIncomeName(String incomeName) {
        IncomeName = incomeName;
    }
}
