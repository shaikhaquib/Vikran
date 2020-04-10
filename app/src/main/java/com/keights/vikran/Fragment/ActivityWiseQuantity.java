package com.keights.vikran.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keights.vikran.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityWiseQuantity extends Fragment {


    public ActivityWiseQuantity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frg_activity_wise_quantity, container, false);
    }

}
