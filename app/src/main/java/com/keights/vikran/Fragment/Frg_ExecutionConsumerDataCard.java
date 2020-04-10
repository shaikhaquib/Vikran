package com.keights.vikran.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.R;

import java.util.ArrayList;
import java.util.List;

import static com.keights.vikran.Extras.Constants.validate;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frg_ExecutionConsumerDataCard extends Fragment {


    public Frg_ExecutionConsumerDataCard() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frg_execution_consumer_data_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }




}
