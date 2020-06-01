package com.keights.vikran.Activity;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.BuildConfig;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.FileDownloader;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Fragment.Frg_DetailNewSurvey;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.ConsumerDetails;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.SearchConsumerResponse;
import com.keights.vikran.ResponseModel.SurveyDetailsItem;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class NewSurveyActivity extends AppCompatActivity {
    Frg_DetailNewSurvey frg_detailNewSurvey;
    TextInputEditText edtconsumerNo;
    ConsumerDetailsItem consumerDetailsItem;
    public static ConsumerDetails consumerDetails;
    SurveyDetailsItem surveyDetailsItem ;
    private TextView cConsumerNo,cName,cDivision,cTaluka,cSubDivision,cSection,cVillage,cVoltagelevel,cDTCCode,sanctionedLoad,resultMessage;
    MaterialButton addSurvey,Execute,Survey,JMC,ExecutionPdf,Rtc,PermissionandCommission,Billing;
    private static final String TAG = "NewSurveyActivity";
    private static final String[] PERMISSIONS = {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static final int PERMISSION_REQUEST_CODE = 1001;
    int notificationId;
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_survey);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        edtconsumerNo = findViewById(R.id.edtconsumerNo);
        resultMessage = findViewById(R.id.resultMessage);
        addSurvey = findViewById(R.id.addSurvey);
        Execute = findViewById(R.id.Execute);
        Survey = findViewById(R.id.Survey);
        JMC = findViewById(R.id.JMC);
        ExecutionPdf = findViewById(R.id.ExecutionPdf);
        Rtc = findViewById(R.id.RTC);
        Billing = findViewById(R.id.Billing);
        PermissionandCommission = findViewById(R.id.PermissionandCommission);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Division : "+USER.getDivision());

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if (edtconsumerNo.getText().toString().isEmpty())
                  edtconsumerNo.setError("Field Required");
              else
                  searchConsumer(edtconsumerNo.getText().toString().trim());
            }
        });

        frg_detailNewSurvey = new Frg_DetailNewSurvey();

        initView();
        findViewById(R.id.BackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getIntent().hasExtra("ConsumerNo")){
            edtconsumerNo.setText(getIntent().getStringExtra("ConsumerNo"));
            findViewById(R.id.next).performClick();
        }

    }

    private void initView() {
        cConsumerNo = findViewById(R.id.cConsumerNo);
        cName = findViewById(R.id.cName);
        cDivision = findViewById(R.id.cDivision);
        cTaluka = findViewById(R.id.cTaluka);
        cSubDivision = findViewById(R.id.cSubDivision);
        cSection = findViewById(R.id.cSection);
        cVillage = findViewById(R.id.cVillage);
        cVoltagelevel = findViewById(R.id.cVoltagelevel);
        cDTCCode = findViewById(R.id.cDTCCode);
        sanctionedLoad = findViewById(R.id.sanctionedLoad);
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

    private void searchConsumer(String consumerNo){
        final Progress progress = new Progress(NewSurveyActivity.this);
        progress.show();
        Call<SearchConsumerResponse> responseCall = RetrofitClient.getInstance().getApi().search_consumer(USER.getReportingId(),USER.getUserId(),consumerNo,USER.getDivision());
        responseCall.enqueue(new Callback<SearchConsumerResponse>() {
            @Override
            public void onResponse(Call<SearchConsumerResponse> call, Response<SearchConsumerResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        if (response.body().getConsumerDetails().getConsumerDetails().size()>0) {
                            consumerDetailsItem = response.body().getConsumerDetails().getConsumerDetails().get(0);
                            consumerDetails = response.body().getConsumerDetails();
                            if (response.body().getConsumerDetails().getSurveyDetails().size()>0)
                                surveyDetailsItem = response.body().getConsumerDetails().getSurveyDetails().get(0);
                            VerifyData(response.body());
                        }else
                            Constants.Alert(NewSurveyActivity.this,"No data available for the entered Consumer Number\n\nYou have Entered Invalid Consumer Number ");
                    }else {
                        Constants.Alert(NewSurveyActivity.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<SearchConsumerResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

    private void VerifyData(final SearchConsumerResponse body) {
        findViewById(R.id.consumerNoLayout).setVisibility(View.GONE);
        findViewById(R.id.ConsumerDetailsView).setVisibility(View.VISIBLE);



        cConsumerNo.setText(consumerDetailsItem.getConsumerNo());
        cName.setText(consumerDetailsItem.getConsumerName());
        cDivision.setText(consumerDetailsItem.getDivision());
        cTaluka.setText(consumerDetailsItem.getTaluka());
        cSubDivision.setText(consumerDetailsItem.getSubDivision());
        cSection.setText(consumerDetailsItem.getSection());
        cVillage.setText(consumerDetailsItem.getVillage());
        cVoltagelevel.setText(consumerDetailsItem.getVoltageLevel());
        cDTCCode.setText(consumerDetailsItem.getDtcCode());
        sanctionedLoad.setText(consumerDetailsItem.getSanctionLoad());


        if(body.getConsumerDetails().getSurveyDetails().isEmpty()){
            resultMessage.setText("Status : Survey pending please add one");
            addSurvey.setVisibility(View.VISIBLE);
        }else if(body.getConsumerDetails().getRtcDetails().isEmpty()){
            resultMessage.setText("Status : Rtc is not completed");
            Survey.setVisibility(View.VISIBLE);
            Rtc.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);
            addSurvey.setVisibility(View.VISIBLE);

        }else if(body.getConsumerDetails().getExecutionDetails().isEmpty()){
            resultMessage.setText("Status : Execution is not taken");
            Survey.setVisibility(View.VISIBLE);
            Rtc.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);

        }else if(body.getConsumerDetails().getExecutionDetails().get(0).getExecutionStatus().equals("not_approved")){
            resultMessage.setText("Status : Execution not approved");
            Survey.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);
            Rtc.setVisibility(View.VISIBLE);
        } else if(body.getConsumerDetails().getExecutionDetails().get(0).getExecutionStatus().equals("pending")){
            resultMessage.setText("Status : Execution is under review");
            Rtc.setVisibility(View.VISIBLE);
            Survey.setVisibility(View.VISIBLE);
        } else if(body.getConsumerDetails().getExecutionDetails().get(0).getExecutionStatus().equals("approved")  && body.getConsumerDetails().getJmcSectionADetails().isEmpty()){
            resultMessage.setText("Status : Execution is successfully approved");
            Survey.setVisibility(View.VISIBLE);
            JMC.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);
            //ExecutionPdf.setVisibility(View.VISIBLE);
            Rtc.setVisibility(View.VISIBLE);
        }else if(body.getConsumerDetails().getJmcSectionBDetails().isEmpty()){
            resultMessage.setText("Status : JMC Section A is completed .");
            Survey.setVisibility(View.VISIBLE);
            JMC.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);
            //ExecutionPdf.setVisibility(View.VISIBLE);
            Rtc.setVisibility(View.VISIBLE);
        }else if(body.getConsumerDetails().getJmcSectionCDetails().isEmpty()){
            resultMessage.setText("Status : JMC Section B is completed.");
            Survey.setVisibility(View.VISIBLE);
            JMC.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);
            //ExecutionPdf.setVisibility(View.VISIBLE);
            Rtc.setVisibility(View.VISIBLE);
        }else if(body.getConsumerDetails().getJmcSectionCDetails().isEmpty()){
            resultMessage.setText("Status : JMC Section C is completed.");
            Survey.setVisibility(View.VISIBLE);
            JMC.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);
            //ExecutionPdf.setVisibility(View.VISIBLE);
            Rtc.setVisibility(View.VISIBLE);
        }else if(body.getConsumerDetails().getJmcSectionDDetails().isEmpty()){
            resultMessage.setText("Status : JMC Section D is completed.");
            Survey.setVisibility(View.VISIBLE);
            JMC.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);
            //ExecutionPdf.setVisibility(View.VISIBLE);
            Rtc.setVisibility(View.VISIBLE);
        }else{
            resultMessage.setText("Status : JMC Completed ");
            Survey.setVisibility(View.VISIBLE);
            JMC.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);
            ExecutionPdf.setVisibility(View.VISIBLE);
            Rtc.setVisibility(View.VISIBLE);
            PermissionandCommission.setVisibility(View.VISIBLE);
            Billing.setVisibility(View.VISIBLE);

            if (!body.getConsumerDetails().getPerComDetails().isEmpty()){
                resultMessage.setText("Status : Permission and Commission is  Completed ");
            }
            if (!body.getConsumerDetails().getBillingDetails().isEmpty()){
                resultMessage.setText("Status : Billing is Completed ");
            }

        }
        addSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSurveyFragement();
            }
        });
        Rtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRTC(body);
            }
        });
        Execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openExecutePage();
            }
        });
        Survey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SurveyDetails();
            }
        });
        JMC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJmc();
            }
        });
        Billing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBilling(body);
            }
        });
        PermissionandCommission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerComm(body);
            }
        });
        ExecutionPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PermissionCheck();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        }

    private void PermissionCheck() throws IOException {
        Log.d(TAG, "openN: ");
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkPermission()) {
                new DownloadFile().execute("https://vikrangroup.com/portal/download_jobcard/"+consumerDetailsItem.getConsumerNo(),"datacard_"+consumerDetailsItem.getConsumerNo()+".pdf");
            } else {
                requestPermission();
            }
        }else {
            new DownloadFile().execute("https://vikrangroup.com/portal/download_jobcard/"+consumerDetailsItem.getConsumerNo(),"datacard_"+consumerDetailsItem.getConsumerNo()+".pdf");
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(NewSurveyActivity.this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(NewSurveyActivity.this,new String[]{ Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
    }




    private void SurveyDetails() {
        Intent intent = new Intent(getApplicationContext(),SurveyDetailActivity.class);
        intent.putExtra("Data", consumerDetailsItem);
        intent.putExtra("surveyDetailsItem", surveyDetailsItem);
        startActivity(intent);
    }
    private void addRTC(SearchConsumerResponse body) {
        Intent intent = new Intent(getApplicationContext(), ActivityRTC.class);
        intent.putExtra("Data", consumerDetailsItem);
        if (!body.getConsumerDetails().getRtcDetails().isEmpty())
         intent.putExtra("RTC", body.getConsumerDetails().getRtcDetails().get(0));
        startActivity(intent);
    }
    private void addPerComm(SearchConsumerResponse body) {
        Intent intent = new Intent(getApplicationContext(), ActivityPermissionandcommi.class);
        intent.putExtra("Data", consumerDetailsItem);
        if (!body.getConsumerDetails().getPerComDetails().isEmpty())
         intent.putExtra("perComDetailsItem", body.getConsumerDetails().getPerComDetails().get(0));
        startActivity(intent);
    }
    private void addBilling(SearchConsumerResponse body) {
        Intent intent = new Intent(getApplicationContext(), ActivityBilling.class);
        intent.putExtra("Data", consumerDetailsItem);
        if (!body.getConsumerDetails().getBillingDetails().isEmpty())
         intent.putExtra("billingDetailsItem", body.getConsumerDetails().getBillingDetails().get(0));
        startActivity(intent);
    }
    private void mJmc() {
        Intent intent = new Intent(NewSurveyActivity.this,ActivityJMC.class);
        startActivity(intent);
    }

    private void openExecutePage(){
        if (consumerDetails.getExecutionDetails().isEmpty()) {
            Intent intent = new Intent(getApplicationContext(), ExecutionActivity.class);
            intent.putExtra("Data", consumerDetailsItem);
            intent.putExtra("surveyId", surveyDetailsItem.getSurveyId());
            startActivity(intent);
        }else {
            Intent intent = new Intent(getApplicationContext(), ExecutionDetails.class);
            startActivity(intent);
        }
    }

    private void openAddSurveyFragement(){
        findViewById(R.id.ConsumerDetailsView).setVisibility(View.GONE);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Data", consumerDetailsItem);
        RelativeLayout relativeLayout = findViewById(R.id.fragemntContener);
        LinearLayout consumerNoLayout = findViewById(R.id.consumerNoLayout);
        relativeLayout.setVisibility(View.VISIBLE);
        frgTransaction(bundle);
    }

    public void frgTransaction(Bundle bundle){
        frg_detailNewSurvey.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frgHolder, frg_detailNewSurvey);
        transaction.commit();
    }


    private class DownloadFile extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showNotification("Download in process...","Vikran is downloading");
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.v(TAG, "doInBackground() Method invoked ");

            String fileUrl = strings[0];
            String fileName = strings[1];
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            File pdfFile = new File(folder, fileName);
            Log.v(TAG, "doInBackground() pdfFile invoked " + pdfFile.getAbsolutePath());
            Log.v(TAG, "doInBackground() pdfFile invoked " + pdfFile.getAbsoluteFile());

            try {
                pdfFile.createNewFile();
                Log.v(TAG, "doInBackground() file created" + pdfFile);

            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "doInBackground() error" + e.getMessage());
                Log.e(TAG, "doInBackground() error" + e.getStackTrace());


            }
            Log.v(TAG, "doInBackground() file download completed");

            if (FileDownloader.downloadFile(fileUrl, pdfFile))
                return pdfFile.getPath();
            else
                return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            removeNotification();
            if (s == null){
                Constants.Alert(NewSurveyActivity.this,"Some thing went wrong while downloading file.\n Please Check your Internet connection or contact admin");
            }else {
                new MaterialAlertDialogBuilder(NewSurveyActivity.this, R.style.AlertDiloge)
                        .setTitle("Download Complete")
                        .setMessage("File has been downloaded successfully in download folder do you want to preview.")
                        .setPositiveButton("Preview", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                File d = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);  // -> filename = maven.pdf
                                File pdfFile = new File(d, "datacard_"+consumerDetailsItem.getConsumerNo()+".pdf");

                                Log.v(TAG, "view() Method pdfFile " + pdfFile.getAbsolutePath());

                                Uri path = FileProvider.getUriForFile(NewSurveyActivity.this, BuildConfig.APPLICATION_ID + ".fileprovider", pdfFile);


                                Log.v(TAG, "view() Method path " + path);

                                Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                                pdfIntent.setDataAndType(path, "application/pdf");
                                pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                                try {
                                    startActivity(pdfIntent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(NewSurveyActivity.this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNeutralButton("Later",null)
                        .show();

            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new DownloadFile().execute("https://vikrangroup.com/portal/download_jobcard/"+consumerDetailsItem.getConsumerNo(),"datacard_"+consumerDetailsItem.getConsumerNo()+".pdf");
                } else {
                    new MaterialAlertDialogBuilder(NewSurveyActivity.this, R.style.AlertDiloge)
                            .setTitle("Storage Permission Required")
                            .setMessage("To download file Storage Permission Required")
                            .setPositiveButton("Give Permission", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(NewSurveyActivity.this)) {
                                        Intent mSettingsIntent = mSettingsIntent = new Intent(Intent.ACTION_MAIN)
                                                .setAction(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                                        try {
                                            startActivity(mSettingsIntent);
                                        } catch (Exception ex) {
                                            Log.w("ErrorLog", "Unable to launch app draw overlay settings " + mSettingsIntent, ex);
                                        }
                                    }
                                    else{
                                        //Device does not support app overlay
                                    }
                                }
                            })
                            .setNeutralButton("Close",null)
                            .setCancelable(false)
                            .show();
                }
                break;
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification(String message , String Title) {

        Random random = new Random();
        notificationId = random.nextInt(9999 - 1000) + 1000;

        notificationManager = (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "vikran";
        String channelName = "vdownload";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.BigTextStyle inboxStyle = new NotificationCompat.BigTextStyle();
        inboxStyle.bigText(message);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getBaseContext(), channelId)
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setLargeIcon(BitmapFactory.decodeResource(NewSurveyActivity.this.getResources(),
                        R.drawable.ic_vikran_app_login_logo_01))
                .setContentTitle(Title)
                .setSound(alarmSound)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message);



        notificationManager.notify(notificationId, mBuilder.build());

    }

    public void removeNotification () {
        if ( notificationId != 0 )
            notificationManager.cancel( notificationId ) ;
    }

}
