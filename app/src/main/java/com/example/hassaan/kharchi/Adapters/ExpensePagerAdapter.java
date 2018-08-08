package com.example.hassaan.kharchi.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hassaan.kharchi.Fragment.Expense.ExpenseGetDailyFragment;
import com.example.hassaan.kharchi.Fragment.Expense.ExpenseGetFragment;
import com.example.hassaan.kharchi.Fragment.Expense.ExpenseGetMonthlyFragment;

public class ExpensePagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public ExpensePagerAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ExpenseGetFragment tab0 = new ExpenseGetFragment() ;
                return tab0;
            case 1:
                ExpenseGetDailyFragment tab1 = new ExpenseGetDailyFragment();
                return tab1;
            case 2:
                ExpenseGetMonthlyFragment tab3 = new ExpenseGetMonthlyFragment();
                return tab3;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
