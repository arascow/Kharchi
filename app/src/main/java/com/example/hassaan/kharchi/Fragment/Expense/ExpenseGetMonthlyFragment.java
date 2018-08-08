package com.example.hassaan.kharchi.Fragment.Expense;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hassaan.kharchi.Adapters.Expense.MonthlyExpenseAdapter;
import com.example.hassaan.kharchi.Data.Repo.ExpenseRepo;
import com.example.hassaan.kharchi.Models.Expense;
import com.example.hassaan.kharchi.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseGetMonthlyFragment extends Fragment {
    private RecyclerView recyclerView;

    private Context context = getContext();

    public ExpenseGetMonthlyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ExpenseRepo expenseRepo = new ExpenseRepo();
        List<Expense> expenseList = expenseRepo.getExpensebyMonth();

        View view = inflater.inflate(R.layout.fragment_expense_get_monthly, container, false);
        MonthlyExpenseAdapter monthlyExpenseAdapter = new MonthlyExpenseAdapter(expenseList, getContext());
        recyclerView = view.findViewById(R.id.recycler_monthly_expense);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(monthlyExpenseAdapter);


        return view;
    }

}
