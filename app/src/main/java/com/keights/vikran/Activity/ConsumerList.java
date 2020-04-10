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
import com.keights.vikran.R;

public class ConsumerList extends AppCompatActivity implements View.OnClickListener {

    Boolean isSelectedQuestions = true;
    TextView tabSurvey;
    TextView tabExeution;
    TextView tabAll;
    TextView tabJMC;
    TextView tabRTC;
    TextView tabBilling;


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
        tabExeution = findViewById(R.id.tabExeution);
        tabAll = findViewById(R.id.all);
       tabJMC = findViewById(R.id.tabJMC);
       tabRTC = findViewById(R.id.tabRTC);
       tabBilling = findViewById(R.id.tabBilling);


        tabSurvey.setOnClickListener(this);
        tabExeution.setOnClickListener(this);
        tabAll.setOnClickListener(this);

        tabJMC.setOnClickListener(this);
        tabRTC.setOnClickListener(this);
        tabBilling.setOnClickListener(this);

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
