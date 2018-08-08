package com.example.hassaan.kharchi.Adapters.Expense;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hassaan.kharchi.Models.Expense;
import com.example.hassaan.kharchi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyExpenseAdapter extends RecyclerView.Adapter<DailyExpenseAdapter.ViewHolder> {
    private List<Expense> expenseList= new ArrayList<>();
    private Context context;

    public DailyExpenseAdapter(List<Expense> expenseList, Context context) {
        this.expenseList = expenseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.month_list,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Date date;
        String datestr = expenseList.get(position).getDate();
        String output = null;
        SimpleDateFormat Format = new SimpleDateFormat("EEE, dd/MM/yyyy");

        try {
            date = Format.parse(datestr);
            output = Format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.ExpAmount.setText(expenseList.get(position).getAmount());
        holder.ExpDay.setText(output);
    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ExpAmount, ExpDay;
        public ViewHolder(View itemView) {
            super(itemView);
            ExpAmount = itemView.findViewById(R.id.TotalAmountTextView);
            ExpDay = itemView.findViewById(R.id.MonthTextView);
        }
    }
}
