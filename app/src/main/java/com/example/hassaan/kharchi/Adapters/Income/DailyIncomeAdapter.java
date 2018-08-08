package com.example.hassaan.kharchi.Adapters.Income;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hassaan.kharchi.Models.Income;
import com.example.hassaan.kharchi.Models.Income_Type;
import com.example.hassaan.kharchi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyIncomeAdapter extends RecyclerView.Adapter<DailyIncomeAdapter.ViewHolder> {
    private List<Income> income = new ArrayList<>();
    private List<Income_Type> IncType;
    private Context context;

    public DailyIncomeAdapter(List<Income> income, Context context) {
        this.income = income;
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
        String datestr = income.get(position).getDate();
        String output = null;
        SimpleDateFormat Format = new SimpleDateFormat("EEE, dd/MM/yyyy");

        try {
            date = Format.parse(datestr);
           output = Format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.IncAmount.setText(income.get(position).getAmount());
        holder.IncMonth.setText(output);
    }

    @Override
    public int getItemCount() {
        return income.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView IncAmount, IncMonth;
        public ViewHolder(View itemView) {
            super(itemView);
            IncAmount = itemView.findViewById(R.id.TotalAmountTextView);
            IncMonth = itemView.findViewById(R.id.MonthTextView);
        }
    }
}
