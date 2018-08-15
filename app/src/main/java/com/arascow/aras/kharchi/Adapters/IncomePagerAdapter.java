package com.arascow.aras.kharchi.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arascow.aras.kharchi.Fragment.Income.IncomeGetDailyFragment;
import com.arascow.aras.kharchi.Fragment.Income.IncomeGetFragment;
import com.arascow.aras.kharchi.Fragment.Income.IncomeGetMonthlyFragment;

public class IncomePagerAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public IncomePagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                IncomeGetFragment tab0 = new IncomeGetFragment() ;
                return tab0;
            case 1:
                IncomeGetDailyFragment tab1 = new IncomeGetDailyFragment();
                return tab1;
            case 2:
                IncomeGetMonthlyFragment tab3 = new IncomeGetMonthlyFragment();
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
