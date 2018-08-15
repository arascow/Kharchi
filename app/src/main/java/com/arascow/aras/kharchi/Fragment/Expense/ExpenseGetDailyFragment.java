package com.arascow.aras.kharchi.Fragment.Expense;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arascow.aras.kharchi.Adapters.Expense.DailyExpenseAdapter;
import com.arascow.aras.kharchi.Data.Repo.ExpenseRepo;
import com.arascow.aras.kharchi.Models.Expense;
import com.arascow.aras.kharchi.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseGetDailyFragment extends Fragment {
    private RecyclerView recyclerView;
    private Context context = getContext();


    public ExpenseGetDailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ExpenseRepo expenseRepo = new ExpenseRepo();
        List<Expense> expenseList = expenseRepo.getExpensebyDay();


        View view = inflater.inflate(R.layout.fragment_expense_get_daily, container, false);
        DailyExpenseAdapter dailyExpenseAdapter = new DailyExpenseAdapter(expenseList, context);
        recyclerView = view.findViewById(R.id.recycler_daily_expense);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(dailyExpenseAdapter);


        return view;
    }

}
