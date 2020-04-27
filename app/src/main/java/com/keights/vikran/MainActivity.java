package com.keights.vikran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.keights.vikran.Activity.ConsumerList;
import com.keights.vikran.Activity.ExecutionActivity;
import com.keights.vikran.Activity.MyWork;
import com.keights.vikran.Activity.NewSurveyActivity;
import com.keights.vikran.Activity.Reports;
import com.keights.vikran.Extras.AppExecutor;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.UserDatabase;
import com.keights.vikran.ResponseModel.UserInfo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private UserDatabase userDatabase;
    MaterialToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, Constants.DATABASE_NAME).
                fallbackToDestructiveMigration().build();
        GetUsersAsyncTask();
        //Log.d(TAG, "onCreate: "+LoginActivity.USER.getUsername());


    }

    private void initData(MaterialToolbar toolbar) {
        findViewById(R.id.new_Survey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewSurveyActivity.class));
            }
        });
        findViewById(R.id.rtc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewSurveyActivity.class));
            }
        });
        findViewById(R.id.PermissionandCommission).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewSurveyActivity.class));
            }
        });
        findViewById(R.id.billing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewSurveyActivity.class));
            }
        });
        findViewById(R.id.MyWork).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyWork.class));
            }
        });
        findViewById(R.id.Reports).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Reports.class));
            }
        });
        findViewById(R.id.Consumerlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ConsumerList.class));
            }
        });
        findViewById(R.id.Execution).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewSurveyActivity.class));
            }
        });
        findViewById(R.id.rtc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewSurveyActivity.class));
            }
        });
        findViewById(R.id.jmc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewSurveyActivity.class));
            }
        });

        toolbar.setTitle(LoginActivity.USER.getUsername());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Constants.deleteUser(MainActivity.this);
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetUsersAsyncTask(){

        AppExecutor.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                ///Your data access task////
                LoginActivity.USER  = userDatabase.dbAccess().getUserDetail();
                initData(toolbar);

            }
        });
    }




}
