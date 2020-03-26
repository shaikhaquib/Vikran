package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.keights.vikran.Fragment.Frg_Consumerlist;
import com.keights.vikran.Fragment.MySurveyFragment;
import com.keights.vikran.R;

public class ConsumerList extends AppCompatActivity implements View.OnClickListener {

    Boolean isSelectedQuestions = true;
    TextView tabSurvey;
    TextView tabPending;
    TextView tabAll;
    TextView tabG1;
    TextView tabG2;
    TextView tabG3;
    TextView tabG4;
    TextView tabG5;
    TextView tabG6;
    TextView tabG7;
    TextView tabG8;
    TextView tabG9;
    TextView tabG10;
    TextView tabG11;
    TextView tabG12;


    TextView selected;
    Frg_Consumerlist mySurveyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_list);
        mySurveyFragment = new Frg_Consumerlist();
        /*  questionsFragment = new RecentQuestionsFragment();*/
        frgTransaction(mySurveyFragment);


        tabSurvey = findViewById(R.id.survey);
        tabPending = findViewById(R.id.pending);
        tabAll = findViewById(R.id.all);

       tabG1 = findViewById(R.id.G1);
       tabG2 = findViewById(R.id.G2);
       tabG3 = findViewById(R.id.G3);
       tabG4 = findViewById(R.id.G4);
       tabG5 = findViewById(R.id.G5);
       tabG6 = findViewById(R.id.G6);
       tabG7 = findViewById(R.id.G7);
       tabG8 = findViewById(R.id.G8);
       tabG9 = findViewById(R.id.G9);
       tabG10= findViewById(R.id.G10);
       tabG11= findViewById(R.id.G11);
       tabG12= findViewById(R.id.G12);

        tabSurvey.setOnClickListener(this);
        tabPending.setOnClickListener(this);
        tabAll.setOnClickListener(this);

        tabG1 .setOnClickListener(this);
        tabG2 .setOnClickListener(this);
        tabG3 .setOnClickListener(this);
        tabG4 .setOnClickListener(this);
        tabG5 .setOnClickListener(this);
        tabG6 .setOnClickListener(this);
        tabG7 .setOnClickListener(this);
        tabG8 .setOnClickListener(this);
        tabG9 .setOnClickListener(this);
        tabG10.setOnClickListener(this);
        tabG11.setOnClickListener(this);
        tabG12.setOnClickListener(this);

        selected = tabAll;

        initToolbar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void frgTransaction(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frgHolder, fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        if (selected.getId()!=v.getId()) {
            TextView textView = (TextView)v;
            textView.setBackgroundColor(getResources().getColor(R.color.blue_A700));
            textView.setTextColor(getResources().getColor(android.R.color.white));

            selected.setBackgroundColor(getResources().getColor(android.R.color.white));
            selected.setTextColor(getResources().getColor(android.R.color.black));

            //    frgTransaction(questionsFragment);
            selected = (TextView) v;
            isSelectedQuestions = true;
        }

    }
}
