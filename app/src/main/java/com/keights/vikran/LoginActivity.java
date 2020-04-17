package com.keights.vikran;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.iid.FirebaseInstanceId;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Extras.SessionManager;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.Network.UserDatabase;
import com.keights.vikran.ResponseModel.UserInfo;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText loginUserName;
    TextInputEditText loginPassword;
    Progress progress;
    private static final String TAG = "LoginActivity";

    private UserDatabase userDatabase;
    SessionManager sessionManager;
    public static UserInfo USER = new UserInfo();
    TextView appVersion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progress = new Progress(this);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, Constants.DATABASE_NAME).
                fallbackToDestructiveMigration().build();

        Log.d(TAG, "onCreate: "+FirebaseInstanceId.getInstance().getToken());

        loginUserName = findViewById(R.id.loginUserName);
        appVersion = findViewById(R.id.appVersion);
        loginPassword = findViewById(R.id.loginPassword);
        sessionManager = new SessionManager(this);

        if (sessionManager.isLoggedIn())
            startActivity(new Intent(getApplicationContext(),MainActivity.class));


        findViewById(R.id.Reports).setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                //       startActivity(new Intent(getApplicationContext(), MainActivity.class));
                String strUserName = Objects.requireNonNull(loginUserName.getText()).toString();
                String strPassword = Objects.requireNonNull(loginPassword.getText()).toString();
                if (strUserName.isEmpty()){
                    Constants.Alert(LoginActivity.this,"Please Enter Registered Mobile Number or Email Id");
                }else if (strPassword.isEmpty()){
                    Constants.Alert(LoginActivity.this,"Please Enter Your Password");
                }
                else {
                    doLogin(strUserName,strPassword);
                }
            }
        });

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            appVersion.setText("Version "+version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }




    }

    private void doLogin(final String strUserName, final String strPassword) {

        String device_token = FirebaseInstanceId.getInstance().getToken();
        progress.show();
        Call<UserInfo> UserInfoCall = RetrofitClient.getInstance().getApi().user_login(strUserName, strPassword,device_token);
        UserInfoCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, final Response<UserInfo> response) {
                progress.dismiss();
                assert response.body() != null;
                if (!response.body().isLoggedIn()){
                    Constants.Alert(LoginActivity.this,response.body().getSuccessMsg());
                }else {

                        new AsyncTask<Void, Void, Void>() {
                            @Override
                            protected Void doInBackground(Void... voids) {
                                UserInfo userinfoItem = response.body();
                                userDatabase.dbAccess().insertUser(userinfoItem);
                                return null;
                            }

                            @Override
                            protected void onPostExecute(Void aVoid) {
                                super.onPostExecute(aVoid);
                                sessionManager.setLogin(true);
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                             //   new GetUsersAsyncTask().execute();
                            }
                        }.execute();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                progress.dismiss();
                Constants.Alert(LoginActivity.this,t.getMessage());
            }
        });
    }




}
