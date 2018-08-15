package com.arascow.aras.kharchi.Adapters.Expense;

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

import com.arascow.aras.kharchi.Data.Repo.ExpenseRepo;
import com.arascow.aras.kharchi.MainActivity;
import com.arascow.aras.kharchi.Models.Expense;
import com.arascow.aras.kharchi.Models.Expense_Type;
import com.arascow.aras.kharchi.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecentExpenseAdapter extends RecyclerView.Adapter<RecentExpenseAdapter.ViewHolder> {

    private List<Expense> recentExpenses = new ArrayList<>();
    private List<Expense_Type> ExpType;
    private Context context;

    public RecentExpenseAdapter(List<Expense> recentExpenses, List<Expense_Type> expType, Context context) {
        this.recentExpenses = recentExpenses;
        ExpType = expType;
        this.context = context;
    }

    public void setList(List<Expense> recentExpenses, List<Expense_Type> expTypes) {
        this.recentExpenses = recentExpenses;
        Collections.reverse(this.recentExpenses);
        this.ExpType = expTypes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView ExpName, ExpDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ExpName = itemView.findViewById(R.id.RecentAmountTextView);
            ExpDate = itemView.findViewById(R.id.RecentDateTextView);
            imageView = itemView.findViewById(R.id.RecentImage);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        context = holder.imageView.getContext();

        holder.ExpName.setText("Rs: " + recentExpenses.get(position).getAmount());
        holder.ExpDate.setText(recentExpenses.get(position).getDate());


        for (Expense_Type ET : ExpType) {

            if (ET.getExpenseTypeId().equals(recentExpenses.get(position).getExpenseType())) {
                byte[] temp = ET.getExpenseImg();
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
                editText.setText(recentExpenses.get(position).getAmount());
                builder.setTitle("Edit Expense");
                builder.setView(editText);
                builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Expense expense = new Expense();
                        ExpenseRepo ExRepo = new ExpenseRepo();

                        if (editText.getText().toString().equals("")) {
                            Toast.makeText(context, "You Must Enter Amount", Toast.LENGTH_SHORT).show();
                        } else if (editText.getText().toString().equals("0")) {
                            Toast.makeText(context, "You Must Enter Amount greater than zero", Toast.LENGTH_SHORT).show();

                        } else if (editText.getText().toString().length() >= 11) {
                            Toast.makeText(context, "Amount cannot be that big", Toast.LENGTH_SHORT).show();

                        } else {
                            String amount = editText.getText().toString();
                            expense.setExpenseID(recentExpenses.get(position).getExpenseID());
                            expense.setAmount(amount);
                            expense.setDate(recentExpenses.get(position).getDate());
                            expense.setExpenseType(recentExpenses.get(position).getExpenseType());
                            ExRepo.updateInExpense(expense);
                            Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                            context.startActivity(i);

                            ((Activity) context).finish();
                        //    Toast.makeText(context, "Edited " + amount + "|||date " + expense.getDate() + "||||Type " + expense.getExpenseType(), Toast.LENGTH_SHORT).show();
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
        return recentExpenses.size();
    }


}
