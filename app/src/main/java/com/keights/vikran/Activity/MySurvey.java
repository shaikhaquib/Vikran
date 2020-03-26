package com.keights.vikran.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Fragment.MySurveyFragment;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.DaysFilterResponse;
import com.keights.vikran.ResponseModel.DaysFilterResponse;
import com.keights.vikran.ResponseModel.FilterData;
import com.keights.vikran.ResponseModel.FilterList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;


public class MySurvey extends AppCompatActivity implements View.OnClickListener{

    Boolean isSelectedQuestions = true;
    TextView tabToday;
    TextView tabYesterday;
    TextView tabWeek;
    TextView tabMonth;
    TextView errorLlist;

    TextView selected;
    MySurveyFragment mySurveyFragment;
    List<FilterList> filterData;
   /* RecentQuestionsFragment questionsFragment;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_survey);

        mySurveyFragment = new MySurveyFragment();
       /*  questionsFragment = new RecentQuestionsFragment();*/


        tabToday = findViewById(R.id.tabToday);
        errorLlist = findViewById(R.id.errorList);
        tabYesterday = findViewById(R.id.tabYesterday);
        tabWeek = findViewById(R.id.tabWeek);
        tabMonth = findViewById(R.id.tabMonth);

        tabToday.setOnClickListener(this);
        tabYesterday.setOnClickListener(this);
        tabWeek.setOnClickListener(this);
        tabMonth.setOnClickListener(this);

        selected = tabToday;
        setFilter(selected.getTag().toString());

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
            errorLlist.setVisibility(View.GONE);
            TextView textView = (TextView)v;
            textView.setBackgroundColor(getResources().getColor(R.color.blue_A700));
            textView.setTextColor(getResources().getColor(android.R.color.white));

            selected.setBackgroundColor(getResources().getColor(android.R.color.white));
            selected.setTextColor(getResources().getColor(android.R.color.black));

            //    frgTransaction(questionsFragment);
            selected = (TextView) v;
            isSelectedQuestions = true;

            setFilter(selected.getTag().toString());

        }

    }

    private void setFilter(final String filter){
        final Progress progress = new Progress(MySurvey.this);
        progress.show();
        Call<DaysFilterResponse> responseCall = RetrofitClient.getInstance().getApi().days_filter(USER.getReportingId(),USER.getUserId(),USER.getDivision(),filter);
        responseCall.enqueue(new Callback<DaysFilterResponse>() {
            @Override
            public void onResponse(Call<DaysFilterResponse> call, Response<DaysFilterResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        if (response.body().getFilterData().getFilterList().size() > 0) {
                            filterData = response.body().getFilterData().getFilterList();
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("filterList",response.body().getFilterData());
                            mySurveyFragment.setArguments(bundle);
                            frgTransaction(mySurveyFragment);
                        }else {
                            errorLlist.setVisibility(View.VISIBLE);
                            errorLlist.setText("No Data Available \n For "+Constants.capitalize(filter.split("_")[0])+"\n Try With Other Tab");
                        }



                    }else {
                        Constants.sessionExpired(MySurvey.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<DaysFilterResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

}
