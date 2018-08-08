package com.example.hassaan.kharchi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import com.example.hassaan.kharchi.Adapters.Income.IncomeTypeAdapter;
import com.example.hassaan.kharchi.Data.Repo.IncomeTypeRepo;
import com.example.hassaan.kharchi.Models.Income_Type;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;


public class PickIncomeCategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Context context;
    private List<Income_Type> income_type;

    private AdView mAdView;
    private String YOUR_ADMOB_APP_ID = "ca-app-pub-7446902067334217/1558871719";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_income_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(this, YOUR_ADMOB_APP_ID);
        mAdView = findViewById(R.id.adViewinc);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        IncomeTypeAdapter incomeTypeAdapter = new IncomeTypeAdapter(income_type,context);
        recyclerView = findViewById(R.id.recyclerIncomeCategory);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),4);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
        recyclerView.setAdapter(incomeTypeAdapter);

        IncomeTypeRepo incomeTypeRepo = new IncomeTypeRepo();
        income_type= incomeTypeRepo.getIncomeTypeList();

        incomeTypeAdapter.setList(income_type);



    }
}
