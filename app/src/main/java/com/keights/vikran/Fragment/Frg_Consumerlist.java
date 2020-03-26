package com.keights.vikran.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keights.vikran.ConsumerAdapt;
import com.keights.vikran.Extras.MyItemDecoration;
import com.keights.vikran.MySurveyAdapt;
import com.keights.vikran.R;

public class Frg_Consumerlist extends Fragment {


    RecyclerView rvLearningSub;
    RecyclerView.Adapter adapter;


    public Frg_Consumerlist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mysurvey, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rvLearningSub = view.findViewById(R.id.rvMySurvey);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        adapter = new ConsumerAdapt(getActivity());
        rvLearningSub.setAdapter(adapter);
        //    getRecentQuestions();
    }


}
