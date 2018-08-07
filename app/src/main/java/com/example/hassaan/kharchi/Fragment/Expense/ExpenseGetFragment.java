package com.example.hassaan.kharchi.Fragment.Expense;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hassaan.kharchi.Adapters.Expense.RecentExpenseAdapter;
import com.example.hassaan.kharchi.Data.Repo.ExpenseRepo;
import com.example.hassaan.kharchi.Data.Repo.ExpenseTypeRepo;
import com.example.hassaan.kharchi.Models.Expense;
import com.example.hassaan.kharchi.Models.Expense_Type;
import com.example.hassaan.kharchi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseGetFragment extends Fragment {
    private RecyclerView recyclerView;
    private Context context;

    private List<Expense> recentExpenses = new ArrayList<>();


    public ExpenseGetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_expense_get, container, false);

        ExpenseTypeRepo expenseTypeRepo = new ExpenseTypeRepo();
        ExpenseRepo expenseRepo = new ExpenseRepo();
        List<Expense_Type> ExpTypes = expenseTypeRepo.getExpenseTypeList();


        final RecentExpenseAdapter recentExpenseAdapter = new RecentExpenseAdapter(recentExpenses, ExpTypes, context);
        recyclerView = view.findViewById(R.id.recyler_all_expense);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recentExpenseAdapter);


        recentExpenses = expenseRepo.getExpenseList();
        recentExpenseAdapter.setList(recentExpenses,ExpTypes);





        return view;
    }

}
