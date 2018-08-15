package com.arascow.aras.kharchi.Adapters.Expense;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arascow.aras.kharchi.Models.Expense;
import com.arascow.aras.kharchi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthlyExpenseAdapter extends RecyclerView.Adapter<MonthlyExpenseAdapter.ViewHolder> {

    private List<Expense> expenseList = new ArrayList<>();
    private Context context;

    public MonthlyExpenseAdapter(List<Expense> expenseList, Context context) {
        this.expenseList = expenseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.month_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Date date = null;
        String strdate = "";
        SimpleDateFormat Format = new SimpleDateFormat("EEE, dd/MM/yyyy");
        SimpleDateFormat Formatout = new SimpleDateFormat("MMM");

        try {
            date = Format.parse(expenseList.get(position).getDate());
            strdate = Formatout.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.ExpAmount.setText(expenseList.get(position).getAmount());
        holder.ExpMonth.setText(strdate);


    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ExpAmount, ExpMonth;
        public ViewHolder(View itemView) {
            super(itemView);
            ExpAmount = itemView.findViewById(R.id.TotalAmountTextView);
            ExpMonth = itemView.findViewById(R.id.MonthTextView);
        }
    }
}
