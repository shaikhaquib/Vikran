package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.MyItemDecoration;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.MySurveyAdapt;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.PendingExecutionAdapt;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.PendingExecution;
import com.keights.vikran.ResponseModel.MyWorkModel;
import com.keights.vikran.ResponseModel.PendingExecution;
import com.keights.vikran.ResponseModel.PendingExecutionItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class PendingExecutionActivity extends AppCompatActivity {

    List<PendingExecutionItem> pendingExecutions = new ArrayList<>();
    RecyclerView rvPendingExecutions;
    PendingExecutionAdapt pendingExecutionAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_execution);

        rvPendingExecutions = findViewById(R.id.rvPendingExecution);
        rvPendingExecutions.setHasFixedSize(true);
        pendingExecutionAdapt = new PendingExecutionAdapt(this,pendingExecutions);
        rvPendingExecutions.setLayoutManager(new LinearLayoutManager(this));
        rvPendingExecutions.addItemDecoration(new MyItemDecoration());
        rvPendingExecutions.setAdapter(pendingExecutionAdapt);
       
       getData();


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
    private void getData() {
        final Progress progress = new Progress(this);
        progress.show();
        Call<PendingExecution> responseCall = RetrofitClient.getInstance().getApi().pending_execution_list(USER.getUserId(),USER.getLoginType());
        responseCall.enqueue(new Callback<PendingExecution>() {
            @Override
            public void onResponse(Call<PendingExecution> call, Response<PendingExecution> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        if (response.body().getPendingExecution()!=null)
                        pendingExecutions.addAll(response.body().getPendingExecution());
                        pendingExecutionAdapt.notifyDataSetChanged();
                    }else {
                        Constants.Alert(PendingExecutionActivity.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<PendingExecution> call, Throwable t) {
                progress.dismiss();
            }
        });

    }
}