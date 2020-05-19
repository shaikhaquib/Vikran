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
import com.keights.vikran.ResponseModel.AssignConsumerItem;
import com.keights.vikran.ResponseModel.MyWorkModel;

import java.util.List;

public class Frg_Consumerlist extends Fragment {


    RecyclerView rvLearningSub;
    RecyclerView.Adapter adapter;
    List<AssignConsumerItem> filterLists;

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


        Bundle bundle = getArguments();
        //  FilterData filterData =(FilterData) bundle.getSerializable("filterList");
        if (filterLists==null)
            filterLists = (List<AssignConsumerItem>) bundle.getSerializable("list");
        else {
            filterLists.clear();
            filterLists = (List<AssignConsumerItem>)bundle.getSerializable("list");
        }

        rvLearningSub = view.findViewById(R.id.rvMySurvey);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        adapter = new ConsumerAdapt(getActivity(),filterLists);
        rvLearningSub.setAdapter(adapter);
        //    getRecentQuestions();
    }


}
