package com.example.hassaan.kharchi;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hassaan.kharchi.Data.Repo.ExpenseRepo;
import com.example.hassaan.kharchi.Data.Repo.ExpenseTypeRepo;
import com.example.hassaan.kharchi.Data.Repo.IncomeRepo;
import com.example.hassaan.kharchi.Data.Repo.IncomeTypeRepo;
import com.example.hassaan.kharchi.Fragment.ExpenseGetFragment;
import com.example.hassaan.kharchi.Models.Expense;
import com.example.hassaan.kharchi.Models.Expense_Type;
import com.example.hassaan.kharchi.Models.Income;
import com.example.hassaan.kharchi.Models.Income_Type;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
        int a = pb.getProgress();

        TextView Income, Expense;
        int Getincome, GetExpense;

//        Button addButton = (Button) findViewById(R.id.BtnAdd);
//        Button subButton = (Button) findViewById(R.id.BtnSub);

        Income = findViewById(R.id.Income);
        Expense = findViewById(R.id.Expense);

//        insertSampleData();


        ExpenseRepo expenseRepo = new ExpenseRepo();
        IncomeRepo incomeRepo = new IncomeRepo();

        Getincome = incomeRepo.getIncomeSum();
        GetExpense = expenseRepo.getExpenseSum();

        Income.setText("" + Getincome);
        Expense.setText(GetExpense + "");

        pb.setMax(Getincome);
        pb.setProgress(Getincome-GetExpense);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(getApplicationContext(), PickExpenseCategoryActivity.class);
                startActivity(intent);


//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
//                builder.setView(inflater.inflate(R.layout.expense_dialog, null));
//                final EditText editText = (EditText) new EditText(MainActivity.this);
//                builder.setMessage("Enter Expense").setTitle("Enter Expense here");
//                builder.setView(editText);
//                builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        Expense expense = new Expense();
//                        ExpenseRepo ExRepo = new ExpenseRepo();
//
//                        if (editText.getText().equals("")) {
//                            Toast.makeText(MainActivity.this, "You Must Enter Amount", Toast.LENGTH_SHORT).show();
//                        } else {
//                            String amount = editText.getText().toString();
//                            expense.setAmount(amount);
//                            expense.setDate("22");
//                            expense.setExpenseType("2");
//                            ExRepo.insertInExpense(expense);
//                            Intent i = new Intent(MainActivity.this, MainActivity.class);
//                            startActivity(i);
//                        }
//                        List<Expense> Expenselist = ExRepo.getExpenseList();
//                        Log.d("TAG", "=============================================================");
//                        for (int i = 0; i < Expenselist.size(); i++) {
//                            Log.d("TAG", Expenselist.get(i).getExpenseID() +
//                                    Expenselist.get(i).getAmount()+
//                                    Expenselist.get(i).getDate()+
//                                    Expenselist.get(i).getExpenseType()
//                            );
//
//
//                        }
//                    }
//                });
//                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener()
//
//                {
//                    public void onClick(DialogInterface dialog, int id) {
//                        // User cancelled the dialog
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();

            }
        });

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
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void insertSampleData() {

        ExpenseRepo ExRepo = new ExpenseRepo();
        IncomeRepo IncRepo = new IncomeRepo();
        ExpenseTypeRepo ExTRepo = new ExpenseTypeRepo();
        IncomeTypeRepo InTRepo = new IncomeTypeRepo();


        ExRepo.delete();
        IncRepo.delete();
        ExTRepo.delete();
        InTRepo.delete();

        //Insert Sample data if the table is empty
        Expense_Type expense_type = new Expense_Type();

        expense_type.setExpenseName("SHOPPING");
        Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.shoping);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 70, baos);
        final byte[] b = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(b, Base64.CRLF);
        expense_type.setExpenseImg(b);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("ENTERTAINMENT");
        Bitmap bm1 = BitmapFactory.decodeResource(getResources(),R.drawable.entertainment);
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        bm1.compress(Bitmap.CompressFormat.PNG, 70, baos1);
        final byte[] b1 = baos1.toByteArray();
       // String encodedImage1 = Base64.encodeToString(b1, Base64.CRLF);
        expense_type.setExpenseImg(b1);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("VACATION");
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(),R.drawable.vacation);
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        bm2.compress(Bitmap.CompressFormat.PNG, 70, baos2);
        final byte[] b2 = baos2.toByteArray();
//        String encodedImage2 = Base64.encodeToString(b2, Base64.CRLF);
        expense_type.setExpenseImg(b2);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("FOOD");
        Bitmap bm3 = BitmapFactory.decodeResource(getResources(),R.drawable.foodd);
        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        bm3.compress(Bitmap.CompressFormat.PNG, 70, baos3);
        final byte[] b3 = baos3.toByteArray();
//        String encodedImage3 = Base64.encodeToString(b3, Base64.CRLF);
        expense_type.setExpenseImg(b3);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("DRINKS");
        Bitmap bm4 = BitmapFactory.decodeResource(getResources(),R.drawable.drinks);
        ByteArrayOutputStream baos4 = new ByteArrayOutputStream();
        bm4.compress(Bitmap.CompressFormat.PNG, 70, baos4);
        final byte[] b4 = baos4.toByteArray();
//        String encodedImage4 = Base64.encodeToString(b4, Base64.CRLF);
        expense_type.setExpenseImg(b4);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("PUBLIC TRANSPORT");
        Bitmap bm5 = BitmapFactory.decodeResource(getResources(),R.drawable.public_transport);
        ByteArrayOutputStream baos5 = new ByteArrayOutputStream();
        bm5.compress(Bitmap.CompressFormat.PNG, 70, baos5);
        final byte[] b5 = baos5.toByteArray();
//        String encodedImage5 = Base64.encodeToString(b5, Base64.CRLF);
        expense_type.setExpenseImg(b5);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("FUEL");
        Bitmap bm6 = BitmapFactory.decodeResource(getResources(),R.drawable.gas);
        ByteArrayOutputStream baos6 = new ByteArrayOutputStream();
        bm6.compress(Bitmap.CompressFormat.PNG, 70, baos6);
        final byte[] b6 = baos6.toByteArray();
//        String encodedImage6 = Base64.encodeToString(b6, Base64.CRLF);
        expense_type.setExpenseImg(b6);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("BILLS");
        Bitmap bm7 = BitmapFactory.decodeResource(getResources(),R.drawable.bills);
        ByteArrayOutputStream baos7 = new ByteArrayOutputStream();
        bm7.compress(Bitmap.CompressFormat.PNG, 70, baos7);
        final byte[] b7 = baos7.toByteArray();
//        String encodedImage7 = Base64.encodeToString(b7, Base64.CRLF);
        expense_type.setExpenseImg(b7);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("SPORTS");
        Bitmap bm8 = BitmapFactory.decodeResource(getResources(),R.drawable.sports);
        ByteArrayOutputStream baos8 = new ByteArrayOutputStream();
        bm8.compress(Bitmap.CompressFormat.PNG, 70, baos8);
        final byte[] b8 = baos8.toByteArray();
//        String encodedImage8 = Base64.encodeToString(b8, Base64.CRLF);
        expense_type.setExpenseImg(b8);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("HEALTH");
        Bitmap bm9 = BitmapFactory.decodeResource(getResources(),R.drawable.health);
        ByteArrayOutputStream baos9 = new ByteArrayOutputStream();
        bm9.compress(Bitmap.CompressFormat.PNG, 70, baos9);
        final byte[] b9 = baos9.toByteArray();
//        String encodedImage9 = Base64.encodeToString(b9, Base64.CRLF);
        expense_type.setExpenseImg(b9);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("BARBER");
        Bitmap bm10 = BitmapFactory.decodeResource(getResources(),R.drawable.barber);
        ByteArrayOutputStream baos10 = new ByteArrayOutputStream();
        bm10.compress(Bitmap.CompressFormat.PNG, 70, baos10);
        final byte[] b10 = baos10.toByteArray();
//        String encodedImage10 = Base64.encodeToString(b10, Base64.CRLF);
        expense_type.setExpenseImg(b10);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("PHONE");
        Bitmap bm11 = BitmapFactory.decodeResource(getResources(),R.drawable.phone);
        ByteArrayOutputStream baos11 = new ByteArrayOutputStream();
        bm11.compress(Bitmap.CompressFormat.PNG, 70, baos11);
        final byte[] b11 = baos.toByteArray();
//        String encodedImage11 = Base64.encodeToString(b11, Base64.CRLF);
        expense_type.setExpenseImg(b11);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("MOVIES");
        Bitmap bm12 = BitmapFactory.decodeResource(getResources(),R.drawable.movies);
        ByteArrayOutputStream baos12 = new ByteArrayOutputStream();
        bm12.compress(Bitmap.CompressFormat.PNG, 70, baos12);
        final byte[] b12 = baos.toByteArray();
//        String encodedImage12 = Base64.encodeToString(b12, Base64.CRLF);
        expense_type.setExpenseImg(b12);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("GIFTS");
        Bitmap bm13 = BitmapFactory.decodeResource(getResources(),R.drawable.gift);
        ByteArrayOutputStream baos13 = new ByteArrayOutputStream();
        bm13.compress(Bitmap.CompressFormat.PNG, 70, baos13);
        final byte[] b13 = baos.toByteArray();
//        String encodedImage13 = Base64.encodeToString(b13, Base64.CRLF);
        expense_type.setExpenseImg(b13);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("CAR");
        Bitmap bm14 = BitmapFactory.decodeResource(getResources(),R.drawable.car);
        ByteArrayOutputStream baos14 = new ByteArrayOutputStream();
        bm14.compress(Bitmap.CompressFormat.PNG, 70, baos14);
        final byte[] b14 = baos14.toByteArray();
//        String encodedImage14 = Base64.encodeToString(b14, Base64.CRLF);
        expense_type.setExpenseImg(b14);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("OTHER");
        Bitmap bm15 = BitmapFactory.decodeResource(getResources(),R.drawable.others);
        ByteArrayOutputStream baos15 = new ByteArrayOutputStream();
        bm15.compress(Bitmap.CompressFormat.PNG, 70, baos15);
        final byte[] b15 = baos15.toByteArray();
//        String encodedImage15 = Base64.encodeToString(b15, Base64.CRLF);
        expense_type.setExpenseImg(b15);
        ExTRepo.insertInExpenseType(expense_type);



        Income_Type income_type = new Income_Type();

        income_type.setIncomeName("SALARY");
        InTRepo.insertInIncomeType(income_type);

        Income income = new Income();

        income.setAmount("50000");
        income.setIncomeType("1");
        income.setDate("22");
        IncRepo.insertInIncome(income);

        Expense expense = new Expense();

        expense.setAmount("30000");
        expense.setDate("22");
        expense.setExpenseType("2");
        ExRepo.insertInExpense(expense);

    }
}
