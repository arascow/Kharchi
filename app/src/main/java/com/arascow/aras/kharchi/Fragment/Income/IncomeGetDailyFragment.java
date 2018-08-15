package com.arascow.aras.kharchi.Fragment.Income;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arascow.aras.kharchi.Adapters.Income.DailyIncomeAdapter;
import com.arascow.aras.kharchi.Data.Repo.IncomeRepo;
import com.arascow.aras.kharchi.Models.Income;
import com.arascow.aras.kharchi.R;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncomeGetDailyFragment extends Fragment {
    private RecyclerView recyclerView;
    private Context context = getContext();


    public IncomeGetDailyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        IncomeRepo incomeRepo = new IncomeRepo();

        List<Income> incomeList = incomeRepo.getIncomeByDay();
        for(Income item: incomeList){
            Log.i(TAG, "onCreateView:Date "+item.getDate()+" Amount: "+item.getAmount());
        }
        Log.i(TAG, "onCreateView: "+incomeList.size());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income_get_daily, container, false);
        DailyIncomeAdapter dailyIncomeAdapter = new DailyIncomeAdapter(incomeList, context);
        recyclerView = view.findViewById(R.id.recycler_daily_income);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(dailyIncomeAdapter);


        return view;
    }

}
