package com.arascow.aras.kharchi.Adapters.Income;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arascow.aras.kharchi.Data.Repo.IncomeRepo;
import com.arascow.aras.kharchi.MainActivity;
import com.arascow.aras.kharchi.Models.Income;
import com.arascow.aras.kharchi.Models.Income_Type;
import com.arascow.aras.kharchi.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecentIncomeAdapter extends RecyclerView.Adapter<RecentIncomeAdapter.ViewHolder> {

    private List<Income> recentIncome = new ArrayList<>();
    private List<Income_Type> IncType;
    private Context context;

    public RecentIncomeAdapter(List<Income> recentIncome, List<Income_Type> expType, Context context) {
        this.recentIncome = recentIncome;
        IncType = expType;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    public void setList(List<Income> recentIncome, List<Income_Type> incTypes) {
        this.recentIncome=recentIncome;
        Collections.reverse(this.recentIncome);
        this.IncType = incTypes;
        notifyDataSetChanged();
    }
    public void removeFromList(int position){
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView IncAmount, IncDate;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.RecentImage);
            IncAmount = itemView.findViewById(R.id.RecentAmountTextView);
            IncDate = itemView.findViewById(R.id.RecentDateTextView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        context = holder.imageView.getContext();

        holder.IncAmount.setText("Rs: "+recentIncome.get(position).getAmount());
        holder.IncDate.setText(recentIncome.get(position).getDate());

        for (Income_Type IT : IncType) {

            if (IT.getIncomeTypeId().equals(recentIncome.get(position).getIncomeType())) {


                byte[] temp = IT.getIncomeImg();
                Bitmap decodedByte = BitmapFactory.decodeByteArray(temp, 0, temp.length);
                holder.imageView.setImageBitmap(decodedByte);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.expense_dialog, null);
                builder.setView(view);
                final EditText editText = new EditText(context);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.setText(recentIncome.get(position).getAmount());
                builder.setTitle("Edit Income");
                builder.setView(editText);
                builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Income income = new Income();
                        IncomeRepo incomeRepo = new IncomeRepo();
                        if (editText.getText().toString().equals("")) {
                            Toast.makeText(context, "You Must Enter Amount", Toast.LENGTH_SHORT).show();
                        } else {
                            String amount = editText.getText().toString();
                            income.setIncomeId(recentIncome.get(position).getIncomeId());
                            income.setAmount(amount);
                            income.setDate(recentIncome.get(position).getDate());
                            income.setIncomeType(recentIncome.get(position).getIncomeType());

                            incomeRepo.updateInIncome(income);

                            Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                            context.startActivity(i);

                            ((Activity)context).finish();
                        }
                    }
                });
                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }

        });
    }

    @Override
    public int getItemCount() {
        return recentIncome.size();
    }


}
