package com.example.hassaan.kharchi.Adapters.Income;

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

import com.example.hassaan.kharchi.Data.Repo.IncomeRepo;
import com.example.hassaan.kharchi.MainActivity;
import com.example.hassaan.kharchi.Models.Income;
import com.example.hassaan.kharchi.Models.Income_Type;
import com.example.hassaan.kharchi.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class IncomeTypeAdapter extends RecyclerView.Adapter<IncomeTypeAdapter.ViewHolder> {

    private List<Income_Type> income_types;
    private Context context;

    public IncomeTypeAdapter(List<Income_Type> income_types, Context context) {
        this.income_types = income_types;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.types_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public void setList(List<Income_Type> income_type) {
        this.income_types = income_type;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView IncType;
        TextView IncNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            IncType = itemView.findViewById(R.id.ExpenseType);
            IncNameTextView = itemView.findViewById(R.id.ExpNameTextView);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        context = holder.IncType.getContext();

        holder.IncNameTextView.setText(income_types.get(position).getIncomeName());

        byte[] temp = income_types.get(position).getIncomeImg();
        Bitmap decodedByte = BitmapFactory.decodeByteArray(temp, 0, temp.length);

        holder.IncType.setImageBitmap(decodedByte);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Date incomeDate = Calendar.getInstance().getTime();

                SimpleDateFormat Formatout = new SimpleDateFormat("EEE, dd/MM/yyyy");
                final String incomeDateinDB = Formatout.format(incomeDate);

//                Toast.makeText(context, "IncomeType " + income_types.get(position).getIncomeTypeId(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.expense_dialog, null);
                builder.setView(view);
                final EditText editText = new EditText(context);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setMessage("Enter Income").setTitle("Enter Income here");
                builder.setView(editText);
                builder.setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Income income = new Income();
                        IncomeRepo incomeRepo = new IncomeRepo();
                        if (editText.getText().toString().equals("")) {
                            Toast.makeText(context, "You Must Enter Amount", Toast.LENGTH_SHORT).show();
                        } else {
                            String amount = editText.getText().toString();
                            income.setAmount(amount);
                            income.setDate(incomeDateinDB);
                            income.setIncomeType(income_types.get(position).IncomeTypeId);

                            incomeRepo.insertInIncome(income);
                            Toast.makeText(context, "IncomeType"+income.getIncomeType(), Toast.LENGTH_SHORT).show();

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
        return income_types.size();
    }
}
