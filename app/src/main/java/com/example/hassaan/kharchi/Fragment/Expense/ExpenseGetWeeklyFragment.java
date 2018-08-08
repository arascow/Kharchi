package com.example.hassaan.kharchi.Fragment.Expense;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hassaan.kharchi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseGetWeeklyFragment extends Fragment {


    public ExpenseGetWeeklyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense_get_weekly, container, false);
    }

}
