package com.arascow.aras.kharchi.App;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;

import com.arascow.aras.kharchi.Data.DBHelper;
import com.arascow.aras.kharchi.Data.DatabaseManager;
import com.arascow.aras.kharchi.Data.Repo.ExpenseRepo;
import com.arascow.aras.kharchi.Data.Repo.ExpenseTypeRepo;
import com.arascow.aras.kharchi.Data.Repo.IncomeRepo;
import com.arascow.aras.kharchi.Data.Repo.IncomeTypeRepo;
import com.arascow.aras.kharchi.Models.Expense_Type;
import com.arascow.aras.kharchi.Models.Income_Type;
import com.arascow.aras.kharchi.R;

import java.io.ByteArrayOutputStream;

public class app extends Application {
    private static Context context;
    private static DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DBHelper();
        DatabaseManager.initializeInstance(dbHelper);
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
            insertSampleData();
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }



    }

    public static Context getContext() {
        return context;
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

        Expense_Type expense_type = new Expense_Type();

        expense_type.setExpenseName("SHOPPING");
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.shoping);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 70, baos);
        final byte[] b = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(b, Base64.CRLF);
        expense_type.setExpenseImg(b);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("ENTERTAINMENT");
        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.entertainment);
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        bm1.compress(Bitmap.CompressFormat.PNG, 70, baos1);
        final byte[] b1 = baos1.toByteArray();
        // String encodedImage1 = Base64.encodeToString(b1, Base64.CRLF);
        expense_type.setExpenseImg(b1);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("VACATION");
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.vacation);
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        bm2.compress(Bitmap.CompressFormat.PNG, 70, baos2);
        final byte[] b2 = baos2.toByteArray();
//        String encodedImage2 = Base64.encodeToString(b2, Base64.CRLF);
        expense_type.setExpenseImg(b2);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("FOOD");
        Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.foodd);
        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        bm3.compress(Bitmap.CompressFormat.PNG, 70, baos3);
        final byte[] b3 = baos3.toByteArray();
//        String encodedImage3 = Base64.encodeToString(b3, Base64.CRLF);
        expense_type.setExpenseImg(b3);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("DRINKS");
        Bitmap bm4 = BitmapFactory.decodeResource(getResources(), R.drawable.drinks);
        ByteArrayOutputStream baos4 = new ByteArrayOutputStream();
        bm4.compress(Bitmap.CompressFormat.PNG, 70, baos4);
        final byte[] b4 = baos4.toByteArray();
//        String encodedImage4 = Base64.encodeToString(b4, Base64.CRLF);
        expense_type.setExpenseImg(b4);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("PUBLIC TRANSPORT");
        Bitmap bm5 = BitmapFactory.decodeResource(getResources(), R.drawable.public_transport);
        ByteArrayOutputStream baos5 = new ByteArrayOutputStream();
        bm5.compress(Bitmap.CompressFormat.PNG, 70, baos5);
        final byte[] b5 = baos5.toByteArray();
//        String encodedImage5 = Base64.encodeToString(b5, Base64.CRLF);
        expense_type.setExpenseImg(b5);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("FUEL");
        Bitmap bm6 = BitmapFactory.decodeResource(getResources(), R.drawable.gas);
        ByteArrayOutputStream baos6 = new ByteArrayOutputStream();
        bm6.compress(Bitmap.CompressFormat.PNG, 70, baos6);
        final byte[] b6 = baos6.toByteArray();
//        String encodedImage6 = Base64.encodeToString(b6, Base64.CRLF);
        expense_type.setExpenseImg(b6);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("BILLS");
        Bitmap bm7 = BitmapFactory.decodeResource(getResources(), R.drawable.bills);
        ByteArrayOutputStream baos7 = new ByteArrayOutputStream();
        bm7.compress(Bitmap.CompressFormat.PNG, 70, baos7);
        final byte[] b7 = baos7.toByteArray();
//        String encodedImage7 = Base64.encodeToString(b7, Base64.CRLF);
        expense_type.setExpenseImg(b7);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("SPORTS");
        Bitmap bm8 = BitmapFactory.decodeResource(getResources(), R.drawable.sports);
        ByteArrayOutputStream baos8 = new ByteArrayOutputStream();
        bm8.compress(Bitmap.CompressFormat.PNG, 70, baos8);
        final byte[] b8 = baos8.toByteArray();
//        String encodedImage8 = Base64.encodeToString(b8, Base64.CRLF);
        expense_type.setExpenseImg(b8);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("HEALTH");
        Bitmap bm9 = BitmapFactory.decodeResource(getResources(), R.drawable.health);
        ByteArrayOutputStream baos9 = new ByteArrayOutputStream();
        bm9.compress(Bitmap.CompressFormat.PNG, 70, baos9);
        final byte[] b9 = baos9.toByteArray();
//        String encodedImage9 = Base64.encodeToString(b9, Base64.CRLF);
        expense_type.setExpenseImg(b9);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("BARBER");
        Bitmap bm10 = BitmapFactory.decodeResource(getResources(), R.drawable.barber);
        ByteArrayOutputStream baos10 = new ByteArrayOutputStream();
        bm10.compress(Bitmap.CompressFormat.PNG, 70, baos10);
        final byte[] b10 = baos10.toByteArray();
//        String encodedImage10 = Base64.encodeToString(b10, Base64.CRLF);
        expense_type.setExpenseImg(b10);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("PHONE");
        Bitmap bm11 = BitmapFactory.decodeResource(getResources(), R.drawable.phone);
        ByteArrayOutputStream baos11 = new ByteArrayOutputStream();
        bm11.compress(Bitmap.CompressFormat.PNG, 70, baos11);
        final byte[] b11 = baos11.toByteArray();
//        String encodedImage11 = Base64.encodeToString(b11, Base64.CRLF);
        expense_type.setExpenseImg(b11);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("MOVIES");
        Bitmap bm12 = BitmapFactory.decodeResource(getResources(), R.drawable.movies);
        ByteArrayOutputStream baos12 = new ByteArrayOutputStream();
        bm12.compress(Bitmap.CompressFormat.PNG, 70, baos12);
        final byte[] b12 = baos12.toByteArray();
//        String encodedImage12 = Base64.encodeToString(b12, Base64.CRLF);
        expense_type.setExpenseImg(b12);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("GIFTS");
        Bitmap bm13 = BitmapFactory.decodeResource(getResources(), R.drawable.gift);
        ByteArrayOutputStream baos13 = new ByteArrayOutputStream();
        bm13.compress(Bitmap.CompressFormat.PNG, 70, baos13);
        final byte[] b13 = baos13.toByteArray();
//        String encodedImage13 = Base64.encodeToString(b13, Base64.CRLF);
        expense_type.setExpenseImg(b13);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("CAR");
        Bitmap bm14 = BitmapFactory.decodeResource(getResources(), R.drawable.car);
        ByteArrayOutputStream baos14 = new ByteArrayOutputStream();
        bm14.compress(Bitmap.CompressFormat.PNG, 70, baos14);
        final byte[] b14 = baos14.toByteArray();
//        String encodedImage14 = Base64.encodeToString(b14, Base64.CRLF);
        expense_type.setExpenseImg(b14);
        ExTRepo.insertInExpenseType(expense_type);

        expense_type.setExpenseName("OTHER");
        Bitmap bm15 = BitmapFactory.decodeResource(getResources(), R.drawable.others);
        ByteArrayOutputStream baos15 = new ByteArrayOutputStream();
        bm15.compress(Bitmap.CompressFormat.PNG, 70, baos15);
        final byte[] b15 = baos15.toByteArray();
//        String encodedImage15 = Base64.encodeToString(b15, Base64.CRLF);
        expense_type.setExpenseImg(b15);
        ExTRepo.insertInExpenseType(expense_type);


        Income_Type income_type = new Income_Type();

        income_type.setIncomeName("SALARY");
        Bitmap bm16 = BitmapFactory.decodeResource(getResources(), R.drawable.salary);
        ByteArrayOutputStream baos16 = new ByteArrayOutputStream();
        bm16.compress(Bitmap.CompressFormat.PNG, 70, baos16);
        final byte[] b16 = baos16.toByteArray();
//        String encodedImage15 = Base64.encodeToString(b15, Base64.CRLF);
        income_type.setIncomeImg(b15);
        InTRepo.insertInIncomeType(income_type);

        income_type.setIncomeName("PROFIT");
        Bitmap bm17 = BitmapFactory.decodeResource(getResources(), R.drawable.profits);
        ByteArrayOutputStream baos17 = new ByteArrayOutputStream();
        bm17.compress(Bitmap.CompressFormat.PNG, 70, baos17);
        final byte[] b17 = baos17.toByteArray();
//        String encodedImage15 = Base64.encodeToString(b15, Base64.CRLF);
        income_type.setIncomeImg(b17);
        InTRepo.insertInIncomeType(income_type);

        income_type.setIncomeName("INVESTMENT");
        Bitmap bm18 = BitmapFactory.decodeResource(getResources(), R.drawable.investment);
        ByteArrayOutputStream baos18 = new ByteArrayOutputStream();
        bm18.compress(Bitmap.CompressFormat.PNG, 70, baos18);
        final byte[] b18 = baos18.toByteArray();
//        String encodedImage15 = Base64.encodeToString(b15, Base64.CRLF);
        income_type.setIncomeImg(b18);
        InTRepo.insertInIncomeType(income_type);

        income_type.setIncomeName("SAVING");
        Bitmap bm19 = BitmapFactory.decodeResource(getResources(), R.drawable.saving);
        ByteArrayOutputStream baos19 = new ByteArrayOutputStream();
        bm19.compress(Bitmap.CompressFormat.PNG, 70, baos19);
        final byte[] b19 = baos19.toByteArray();
//        String encodedImage15 = Base64.encodeToString(b15, Base64.CRLF);
        income_type.setIncomeImg(b19);
        InTRepo.insertInIncomeType(income_type);
    }


}
