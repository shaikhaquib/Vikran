package com.keights.vikran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.keights.vikran.Activity.ConsumerList;
import com.keights.vikran.Activity.MySurvey;
import com.keights.vikran.Activity.NewSurveyActivity;
import com.keights.vikran.Activity.Reports;
import com.keights.vikran.Extras.Constants;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.new_Survey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewSurveyActivity.class));
            }
        });
        findViewById(R.id.MyServey).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MySurvey.class));
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

        toolbar.setTitle(LoginActivity.USER.getUsername());
        Log.d(TAG, "onCreate: "+LoginActivity.USER.getUsername());


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
}
