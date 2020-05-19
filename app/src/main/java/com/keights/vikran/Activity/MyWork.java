package com.keights.vikran.Activity;

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

import com.google.android.material.appbar.MaterialToolbar;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Fragment.MySurveyFragment;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.DaysFilterResponse;
import com.keights.vikran.ResponseModel.FilterList;
import com.keights.vikran.ResponseModel.MyBillingItem;
import com.keights.vikran.ResponseModel.MyExecutionItem;
import com.keights.vikran.ResponseModel.MyJmcAItem;
import com.keights.vikran.ResponseModel.MyJmcBItem;
import com.keights.vikran.ResponseModel.MyJmcCItem;
import com.keights.vikran.ResponseModel.MyJmcDItem;
import com.keights.vikran.ResponseModel.MyJmcEItem;
import com.keights.vikran.ResponseModel.MyPerComItem;
import com.keights.vikran.ResponseModel.MyRtcItem;
import com.keights.vikran.ResponseModel.MySurveyItem;
import com.keights.vikran.ResponseModel.MyWorkFilterResponse;
import com.keights.vikran.ResponseModel.MyWorkModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;


public class MyWork extends AppCompatActivity implements View.OnClickListener{

    Boolean isSelectedQuestions = true;
    TextView tabToday;
    TextView tabYesterday;
    TextView tabWeek;
    TextView tabMonth;
    TextView errorLlist;

    TextView selected;
    List<MyWorkModel> filterData = new ArrayList<>();
   /* RecentQuestionsFragment questionsFragment;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_work);

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
        MaterialToolbar toolbar =  findViewById(R.id.toolbar);
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
        final Progress progress = new Progress(MyWork.this);
        progress.show();
        Call<MyWorkFilterResponse> responseCall = RetrofitClient.getInstance().getApi().days_filter(USER.getReportingId(),USER.getUserId(),USER.getDivision(),filter);
        responseCall.enqueue(new Callback<MyWorkFilterResponse>() {
            @Override
            public void onResponse(Call<MyWorkFilterResponse> call, Response<MyWorkFilterResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        List<MySurveyItem> mySurveyItems = response.body().getFilterData().getMySurvey();
                        List<MyRtcItem> myRtcItems = response.body().getFilterData().getMyRtc();
                        List<MyExecutionItem> myExecutionItems = response.body().getFilterData().getMyExecution();
                        List<MyJmcAItem> myJmcAItems = response.body().getFilterData().getMyJmcA();
                        List<MyJmcBItem> myJmcBItems = response.body().getFilterData().getMyJmcB();
                        List<MyJmcCItem> myJmcCItems = response.body().getFilterData().getMyJmcC();
                        List<MyJmcDItem> myJmcDItems = response.body().getFilterData().getMyJmcD();
                        List<MyJmcEItem> myJmcEItems = response.body().getFilterData().getMyJmcE();
                        List<MyPerComItem> myPerComItems = response.body().getFilterData().getMyPerCom();
                        List<MyBillingItem> myBillingItems = response.body().getFilterData().getMyBilling();

                        if (!mySurveyItems.isEmpty()) {
                            
                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My Survey");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < mySurveyItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(mySurveyItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(mySurveyItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(mySurveyItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }
                        if (!myRtcItems.isEmpty()) {

                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My RTC");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < myRtcItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(myRtcItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(myRtcItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(myRtcItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }
                        if (!myExecutionItems.isEmpty()) {

                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My Execution");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < myExecutionItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(myExecutionItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(myExecutionItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(myExecutionItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }
                        if (!myJmcAItems.isEmpty()) {

                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My JMC Section A");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < myJmcAItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(myJmcAItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(myJmcAItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(myJmcAItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }
                        if (!myJmcBItems.isEmpty()) {

                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My JMC Section B");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < myJmcBItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(myJmcBItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(myJmcBItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(myJmcBItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }
                        if (!myJmcCItems.isEmpty()) {

                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My JMC Section C");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < myJmcCItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(myJmcCItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(myJmcCItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(myJmcCItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }
                        if (!myJmcDItems.isEmpty()) {

                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My JMC Section D");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < myJmcDItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(myJmcDItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(myJmcDItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(myJmcDItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }
                        if (!myJmcEItems.isEmpty()) {

                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My JMC Section E");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < myJmcEItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(myJmcDItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(myJmcDItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(myJmcDItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }
                        if (!myPerComItems.isEmpty()) {

                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My Permission and Commission");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < myPerComItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(myPerComItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(myPerComItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(myPerComItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }
                        if (!myBillingItems.isEmpty()) {

                            MyWorkModel myWorkModel = new MyWorkModel();
                            myWorkModel.setSection(true);
                            myWorkModel.setSectionName("My Billing");
                            filterData.add(myWorkModel);
                            for (int i = 0; i < myBillingItems.size(); i++) {
                                MyWorkModel myWorkModel1 = new MyWorkModel();
                                myWorkModel1.setSection(false);
                                myWorkModel1.setConsumerName(myBillingItems.get(i).getConsumerName());
                                myWorkModel1.setConsumerNo(myBillingItems.get(i).getConsumerNo());
                                myWorkModel1.setDivision(myBillingItems.get(i).getDivision());
                                filterData.add(myWorkModel1);
                            }

                        }

                        MySurveyFragment mySurveyFragment = new MySurveyFragment();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("filterList", (Serializable) filterData);
                        mySurveyFragment.setArguments(bundle);
                        frgTransaction(mySurveyFragment);

                        if (filterData.isEmpty()){
                            errorLlist.setVisibility(View.VISIBLE);
                            errorLlist.setText("No Data Available \n For "+Constants.capitalize(filter.split("_")[0])+"\n Try With Other Tab");
                        }



                    }else {
                        Constants.Alert(MyWork.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<MyWorkFilterResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

}
