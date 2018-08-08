package com.example.hassaan.kharchi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.hassaan.kharchi.Adapters.Expense.ExpenseTypeAdapter;
import com.example.hassaan.kharchi.Data.Repo.ExpenseTypeRepo;
import com.example.hassaan.kharchi.Models.Expense_Type;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class PickExpenseCategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Context context;
    List<Expense_Type> expense_type = new ArrayList<>();

    private AdView mAdView;
    private String YOUR_ADMOB_APP_ID = "ca-app-pub-7446902067334217/1558871719";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_expense_category);

getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MobileAds.initialize(this, YOUR_ADMOB_APP_ID);
        mAdView = findViewById(R.id.adViewexp);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ExpenseTypeAdapter expenseTypeAdapter = new ExpenseTypeAdapter(expense_type,context);
        recyclerView = findViewById(R.id.recyclerExpenseCategory);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),4);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
        recyclerView.setAdapter(expenseTypeAdapter);

        ExpenseTypeRepo expenseTypeRepo = new ExpenseTypeRepo();
        expense_type  = expenseTypeRepo.getExpenseTypeList();

        expenseTypeAdapter.setList(expense_type);


    }
}
