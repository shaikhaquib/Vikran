package com.keights.vikran.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keights.vikran.Extras.MyItemDecoration;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.MySurveyAdapt;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.FilterData;
import com.keights.vikran.ResponseModel.FilterList;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MySurveyFragment extends Fragment {


    RecyclerView rvLearningSub;
    RecyclerView.Adapter adapter;
    List<FilterList> filterLists;


    public MySurveyFragment() {
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
        FilterData filterData =(FilterData) bundle.getSerializable("filterList");
        if (filterLists==null)
            filterLists = filterData.getFilterList();
        else {
            filterLists.clear();
            filterLists = filterData.getFilterList();
        }

        rvLearningSub = view.findViewById(R.id.rvMySurvey);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        if (filterLists.size()>0)
         adapter = new MySurveyAdapt(getActivity(),filterLists);
        rvLearningSub.setAdapter(adapter);
    //    getRecentQuestions();
    }


}
