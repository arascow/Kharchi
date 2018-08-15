package com.arascow.aras.kharchi.Fragment.Income;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arascow.aras.kharchi.Adapters.Income.RecentIncomeAdapter;
import com.arascow.aras.kharchi.Data.Repo.IncomeRepo;
import com.arascow.aras.kharchi.Data.Repo.IncomeTypeRepo;
import com.arascow.aras.kharchi.Models.Income;
import com.arascow.aras.kharchi.Models.Income_Type;
import com.arascow.aras.kharchi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IncomeGetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class IncomeGetFragment extends Fragment {
    private RecyclerView recyclerView;
    private Context context;

    private List<Income> recentIncome = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public IncomeGetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        IncomeTypeRepo incomeTypeRepo = new IncomeTypeRepo();
        IncomeRepo incomeRepo= new IncomeRepo();

        List<Income_Type> income_types= incomeTypeRepo.getIncomeTypeList();

        RecentIncomeAdapter recentIncomeAdapter = new RecentIncomeAdapter(recentIncome,income_types,context);
        View view= inflater.inflate(R.layout.fragment_income_get, container, false);
        recyclerView = view.findViewById(R.id.recyler_all_income);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recentIncomeAdapter);

        recentIncome=incomeRepo.getIncomeList();

        recentIncomeAdapter.setList(recentIncome,income_types);

        return view;
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
