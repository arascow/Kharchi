package com.example.hassaan.kharchi.Fragment.Income;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hassaan.kharchi.Adapters.Income.MonthlyIncomeAdapter;
import com.example.hassaan.kharchi.Data.Repo.IncomeRepo;
import com.example.hassaan.kharchi.Data.Repo.IncomeTypeRepo;
import com.example.hassaan.kharchi.Models.Income;
import com.example.hassaan.kharchi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IncomeGetMonthlyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IncomeGetMonthlyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeGetMonthlyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private Context context = getContext();

    public IncomeGetMonthlyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IncomeGetMonthlyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IncomeGetMonthlyFragment newInstance(String param1, String param2) {
        IncomeGetMonthlyFragment fragment = new IncomeGetMonthlyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        IncomeTypeRepo incomeTypeRepo = new IncomeTypeRepo();
        IncomeRepo incomeRepo = new IncomeRepo();

        List<Income> incomeList = incomeRepo.getIncomeByMonth();

        View view = inflater.inflate(R.layout.fragment_income_get_monthly, container, false);
        MonthlyIncomeAdapter monthlyIncomeAdapter = new MonthlyIncomeAdapter(incomeList, context);
        recyclerView = view.findViewById(R.id.recycler_monthly_income);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(monthlyIncomeAdapter);


        return view;
    }

    public List<Income> filter(List<Income> incomeList) {
        List<Income> filtered = new ArrayList<>();
        Income GetIncome;
        int Sum_of_one_month = 0;
        List<Integer> SUMINCOMEBYMONTH = new ArrayList<>();

        for (Income item : incomeList) {
            String DBDate = item.getDate();
            Date date;
            String month = "";
            SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat Formatout = new SimpleDateFormat("/MM/");

            try {
                date = Format.parse(DBDate);
                month = Formatout.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            GetIncome = new Income();
            if (DBDate.contains(month)) {

                Sum_of_one_month += Integer.parseInt(GetIncome.Amount);

            } else {


                GetIncome.setDate(DBDate);
                GetIncome.setAmount(getString(Sum_of_one_month));
                DBDate = item.getDate();
                try {
                    date = Format.parse(DBDate);
                    month = Formatout.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


        }

        return filtered;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
