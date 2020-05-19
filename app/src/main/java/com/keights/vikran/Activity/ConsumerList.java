package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.keights.vikran.ConsumerAdapt;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.MyItemDecoration;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Fragment.Frg_Consumerlist;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.AssignConsumerItem;
import com.keights.vikran.ResponseModel.AssignExecutionResponse;
import com.keights.vikran.ResponseModel.BillingResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

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
    private SearchView searchView;

    RecyclerView rvLearningSub;
    ConsumerAdapt adapter;
    List<AssignConsumerItem> filterLists = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_list);



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

        assign_consumer_list();

        initToolbar();




        rvLearningSub = findViewById(R.id.rvMySurvey);
        rvLearningSub.setHasFixedSize(true);
        rvLearningSub.setLayoutManager(new LinearLayoutManager(this));
        rvLearningSub.addItemDecoration(new MyItemDecoration());
        adapter = new ConsumerAdapt(this,filterLists);
        rvLearningSub.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                adapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.action_search) {
            return true;
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


    private void assign_consumer_list( ) {
        final Progress progress = new Progress(ConsumerList.this);
        progress.show();
        Call<AssignExecutionResponse> responseCall = RetrofitClient.getInstance().getApi().assign_consumer_list(USER.getReportingId(),USER.getUserId(),USER.getDivision());
        responseCall.enqueue(new Callback<AssignExecutionResponse>() {
            @Override
            public void onResponse(Call<AssignExecutionResponse> call, Response<AssignExecutionResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        filterLists.addAll(response.body().getAssignConsumer());
                        adapter.notifyDataSetChanged();

                        /*mySurveyFragment = new Frg_Consumerlist();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("list", (Serializable) assignConsumerItems);
                        mySurveyFragment.setArguments(bundle);
                        frgTransaction(mySurveyFragment);*/
                        /*  questionsFragment = new RecentQuestionsFragment();*/
                    }else {
                        Constants.Alert(ConsumerList.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<AssignExecutionResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

}
