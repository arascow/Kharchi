package com.example.hassaan.kharchi.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hassaan.kharchi.MainActivity;
import com.example.hassaan.kharchi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpenseGetFragment extends Fragment {

private Context context;
    public ExpenseGetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =inflater.inflate(R.layout.fragment_expense_get, container, false);
        Button btn1,btn2,btn3,btn4,btn5,btn6;

        btn1 = view.findViewById(R.id.Btn1);
        btn2 = view.findViewById(R.id.Btn2);
        btn3 = view.findViewById(R.id.Btn3);
        btn4 = view.findViewById(R.id.Btn4);
        btn5 = view.findViewById(R.id.Btn5);
        btn6 = view.findViewById(R.id.Btn6);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.expense_dialog, null));
                EditText editText = (EditText) view.findViewById(R.id.ED_EditText);
                builder.setMessage("Enter Expense").setTitle("Enter Expense here");

                builder.setPositiveButton("Enter", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {

                    }
                });
                builder.setNegativeButton("Exit", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // User cancelled the dialog
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        return view;
    }

}
