package com.keights.vikran;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.keights.vikran.Activity.ConsumerList;
import com.keights.vikran.Activity.MyWork;
import com.keights.vikran.Activity.NewSurveyActivity;
import com.keights.vikran.Activity.PendingExecutionActivity;
import com.keights.vikran.Activity.Reports;
import com.keights.vikran.Extras.AppExecutor;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Network.UserDatabase;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private UserDatabase userDatabase;
    MaterialToolbar toolbar;
    private static final int PERMISSION_ID = 44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLastLocation();

        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, Constants.DATABASE_NAME).
                fallbackToDestructiveMigration().build();
        GetUsersAsyncTask();
        //Log.d(TAG, "onCreate: "+LoginActivity.USER.getUsername());

        findViewById(R.id.PendingExecution).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PendingExecutionActivity.class));
            }
        });



    }

    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
            } else {
                new MaterialAlertDialogBuilder(MainActivity.this, R.style.AlertDiloge)
                        .setTitle("Turn on Location")
                        .setMessage("Turn on location service to allow Vikran App to determine your location")
                        .setCancelable(false)
                        .setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Turn on location", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivityForResult(intent, 2011);
                            }
                        })
                        .show();

            }
        } else {
            requestPermissions();
        }
    }
    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_ID
            );
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Granted. Start getting the location information
               // getLastLocation();
            }
        }
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

                if (LoginActivity.USER.getLoginType().equals("Cluster Incharge") || LoginActivity.USER.getLoginType().equals("Manager")){
                    findViewById(R.id.PendingExecution).setVisibility(View.VISIBLE);
                }else {
                    findViewById(R.id.PendingExecution).setVisibility(View.GONE);
                }

            }
        });
    }




}
