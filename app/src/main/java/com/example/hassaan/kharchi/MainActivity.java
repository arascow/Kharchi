package com.example.hassaan.kharchi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hassaan.kharchi.Adapters.Expense.RecentExpenseAdapter;
import com.example.hassaan.kharchi.Adapters.Income.RecentIncomeAdapter;
import com.example.hassaan.kharchi.Data.Repo.ExpenseRepo;
import com.example.hassaan.kharchi.Data.Repo.ExpenseTypeRepo;
import com.example.hassaan.kharchi.Data.Repo.IncomeRepo;
import com.example.hassaan.kharchi.Data.Repo.IncomeTypeRepo;
import com.example.hassaan.kharchi.Models.Expense;
import com.example.hassaan.kharchi.Models.Expense_Type;
import com.example.hassaan.kharchi.Models.Income;
import com.example.hassaan.kharchi.Models.Income_Type;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private AdView mAdView;
    private AdView mAdView2;
    private String YOUR_ADMOB_APP_ID = "ca-app-pub-7446902067334217/1558871719";
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private List<Expense> recentExpenses = new ArrayList<>();
    private List<Income> recentIncome = new ArrayList<>();
    private List<Expense_Type> ExpTypes = new ArrayList<>();
    private List<Income_Type> IncTypes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MobileAds.initialize(this, "YOUR_ADMOB_APP_ID");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView2 = findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);

        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
        int a = pb.getProgress();

        TextView Income, Expense, incomeTV, expenseTV, recentincome,recentexpense;
        int Getincome, GetExpense;


        Income = findViewById(R.id.Income);
        incomeTV = findViewById(R.id.incomeText);
        Expense = findViewById(R.id.Expense);
        expenseTV = findViewById(R.id.expenseText);

        recentexpense = findViewById(R.id.recentexpenseText);
        recentincome = findViewById(R.id.recentincomeText);

//        insertSampleData();

        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        ExpenseRepo expenseRepo = new ExpenseRepo();
        ExpenseTypeRepo expenseTypeRepo = new ExpenseTypeRepo();
        IncomeRepo incomeRepo = new IncomeRepo();
        IncomeTypeRepo incomeTypeRepo = new IncomeTypeRepo();

        Getincome = incomeRepo.getIncomeSumbyMonth();
        GetExpense = expenseRepo.getExpenseSumbyMonth();

        Income.setText("" + Getincome);
        Expense.setText(GetExpense + "");

        pb.setMax(Getincome);
        pb.setProgress(Getincome - GetExpense);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vibe.vibrate(50);

                Intent intent = new Intent(getApplicationContext(), PickExpenseCategoryActivity.class);
                startActivity(intent);


            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibe.vibrate(50);
//                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), PickIncomeCategoryActivity.class);
                startActivity(intent);
            }
        });

        ExpTypes = expenseTypeRepo.getExpenseTypeList();
        IncTypes = incomeTypeRepo.getIncomeTypeList();

        RecentExpenseAdapter recentExpenseAdapter = new RecentExpenseAdapter(recentExpenses, ExpTypes, getApplicationContext());
        final RecentIncomeAdapter recentIncomeAdapter = new RecentIncomeAdapter(recentIncome, IncTypes, getApplicationContext());
        recyclerView = findViewById(R.id.recylerRecentExp);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(recentExpenseAdapter);

        recentExpenses = expenseRepo.getExpenseList();
        recentIncome = incomeRepo.getIncomeList();

        if (recentExpenses.size() < 10) {
            recentExpenseAdapter.setList(recentExpenses, ExpTypes);
        } else {
            List<Expense> filtered = new ArrayList<>();
            Collections.reverse(recentExpenses);

            for (int i = 0; i < 10; i++) {
                filtered.add(recentExpenses.get(i));
            }
            Collections.reverse(filtered);
            recentExpenseAdapter.setList(filtered, ExpTypes);
        }

        recyclerView2 = findViewById(R.id.recylerRecentIncome);
        recyclerView2.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManagerinc = new LinearLayoutManager(getApplicationContext());
        recyclerView2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView2.setAdapter(recentIncomeAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(MainActivity.this, "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                //Remove swiped item from list and notify the RecyclerView
                final int position = viewHolder.getAdapterPosition();
                recentIncomeAdapter.removeFromList(position);
                Toast.makeText(MainActivity.this, "on Swiped " + position, Toast.LENGTH_SHORT).show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView2);


        if (recentIncome.size() < 10) {
            recentIncomeAdapter.setList(recentIncome, IncTypes);
        } else {
            List<Income> filtered = new ArrayList<>();
            Collections.reverse(recentIncome);

            for (int i = 0; i < 10; i++) {
                filtered.add(recentIncome.get(i));
            }
            Collections.reverse(filtered);
            recentIncomeAdapter.setList(filtered, IncTypes);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            // super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_expense) {


            Intent intent = new Intent(MainActivity.this, ExpenseTabActivity.class);
            startActivity(intent);
//            ExpenseGetFragment expenseGetFragment = new ExpenseGetFragment();
//            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.container, expenseGetFragment);
//            fragmentTransaction.commit();

        } else if (id == R.id.nav_income) {

            Intent intent = new Intent(MainActivity.this, IncomeTabActivity.class);
            startActivity(intent);
//            IncomeGetFragment incomeGetFragment = new IncomeGetFragment();
//            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.container, incomeGetFragment);
//            fragmentTransaction.commit();

        } else if (id == R.id.nav_home) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(getIntent());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
