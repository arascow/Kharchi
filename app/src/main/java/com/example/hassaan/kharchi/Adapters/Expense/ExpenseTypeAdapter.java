package com.example.hassaan.kharchi.Adapters.Expense;

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

import com.example.hassaan.kharchi.Data.Repo.ExpenseRepo;
import com.example.hassaan.kharchi.MainActivity;
import com.example.hassaan.kharchi.Models.Expense;
import com.example.hassaan.kharchi.Models.Expense_Type;
import com.example.hassaan.kharchi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ExpenseTypeAdapter extends RecyclerView.Adapter<ExpenseTypeAdapter.ViewHolder> {

    private List<Expense_Type> expense_type;
    private Context context;
    public ExpenseTypeAdapter(List<Expense_Type> expense_type, Context context) {
        this.expense_type=expense_type;
        this.context=context;
    }

    public ExpenseTypeAdapter(List<Expense_Type> expense_type) {
        this.expense_type=expense_type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.types_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void setList(List<Expense_Type> expense_type) {
        this.expense_type=expense_type;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ExpType;
        TextView ExpNameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            ExpType= itemView.findViewById(R.id.ExpenseType);
            ExpNameTextView = itemView.findViewById(R.id.ExpNameTextView);
        }
    }

    @Override
    public void onBindViewHolder( final ViewHolder holder, final int position) {
        context= holder.ExpType.getContext();

        byte[] temp = expense_type.get(position).getExpenseImg();
        Bitmap decodedByte = BitmapFactory.decodeByteArray(temp, 0, temp.length);

        holder.ExpType.setImageBitmap(decodedByte);
        holder.ExpNameTextView.setText(expense_type.get(position).getExpenseName());
        
        holder.ExpType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date expenseDate = Calendar.getInstance().getTime();

                SimpleDateFormat Formatout = new SimpleDateFormat("EEE, dd/MM/yyyy");
                final String expenseDateinDB = Formatout.format(expenseDate);


                Toast.makeText(context, "ExpenseType "+expense_type.get(position).getExpenseTypeId(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.expense_dialog, null);
                builder.setView(view);
                final EditText editText = new EditText(context);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setMessage("Enter Expense").setTitle("Enter Expense here");
                builder.setView(editText);
                builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Expense expense = new Expense();
                        ExpenseRepo ExRepo = new ExpenseRepo();

                        if (editText.getText().toString().equals("")) {
                            Toast.makeText(context, "You Must Enter Amount", Toast.LENGTH_SHORT).show();
                        } else {
                            String amount = editText.getText().toString();
                            expense.setAmount(amount);
                            expense.setDate(expenseDateinDB);
                            expense.setExpenseType(expense_type.get(position).getExpenseTypeId());
                            ExRepo.insertInExpense(expense);
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
        return expense_type.size();
    }


}
