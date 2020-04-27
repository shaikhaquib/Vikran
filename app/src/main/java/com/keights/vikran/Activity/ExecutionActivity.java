package com.keights.vikran.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.AddExecutionResponse;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class ExecutionActivity extends AppCompatActivity {

    // Consumer Data Card

    List<TextInputEditText> validationList = new ArrayList();
    private TextView cConsumerNo,cName,cDivision,cTaluka,cSubDivision,cSection,cVillage,cVoltagelevel,cDTCCode,sanctionedLoad;

    TextInputEditText transformer_make ,tf_sl_no,tf_capacity_in_kva,meter_make,meter_no;
    ElegantNumberButton PoleShifting,STRShiftinginnumbers,DTRErectioninnumbers,FinishingPerLocation,mtr9_psc,mtr9_rsj,mtr11_rsj;
    TextView PoleErection,StringPerLocation;

    private static final String TAG = "ExecutionActivity";

    // Activity Wise Quantity

    List<TextInputEditText> SecondStep = new ArrayList();


    ConsumerDetailsItem consumerDetailsItem;
    String surveyId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execution);
        consumerDetailsItem = (ConsumerDetailsItem) getIntent().getSerializableExtra("Data");
        surveyId =  getIntent().getStringExtra("surveyId");

        initToolbar();


        init();

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
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    void init() {

        cConsumerNo = findViewById(R.id.cNo);
        cName = findViewById(R.id.cName);
        cDivision = findViewById(R.id.cDivision);
        cTaluka = findViewById(R.id.cTaluka);
        cSubDivision = findViewById(R.id.cSubDivision);
        cSection = findViewById(R.id.cSection);
        cVillage = findViewById(R.id.cVillage);
        cVoltagelevel = findViewById(R.id.cVoltagelevel);
        cDTCCode = findViewById(R.id.cDTCCode);
        sanctionedLoad = findViewById(R.id.sanctionedLoad);


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

        StringPerLocation = findViewById(R.id.StringPerLocation);
        PoleErection = findViewById(R.id.PoleErection);

        transformer_make = findViewById(R.id.transformer_make);
        tf_sl_no = findViewById(R.id.tf_sl_no);
        tf_capacity_in_kva = findViewById(R.id.tf_capacity_in_kva);
        meter_make = findViewById(R.id.meter_make);
        meter_no = findViewById(R.id.meter_no);


        PoleShifting = findViewById(R.id.PoleShifting);
        STRShiftinginnumbers = findViewById(R.id.STRShiftinginnumbers);
        DTRErectioninnumbers = findViewById(R.id.DTRErectioninnumbers);
        FinishingPerLocation = findViewById(R.id.FinishingPerLocation);
        mtr9_psc = findViewById(R.id.mtr9_psc);
        mtr9_rsj = findViewById(R.id.mtr9_rsj);
        mtr11_rsj = findViewById(R.id.mtr11_rsj);

        StringPerLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((TextView) v, new String[]{"0", "0.5", "1"});
            }
        });

        PoleErection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((TextView) v, new String[]{"9 Mtr PSC", "9 Mtr RSJ", "11 Mtr RSJ", "9 Mtr PSC + 9 Mtr RSJ","9 Mtr PSC + 11 Mtr RSJ"});
            }
        });



        findViewById(R.id.secondStep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (validate(ExecutionActivity.this,validationList))
                { */
                    findViewById(R.id.secondStepView).setVisibility(View.VISIBLE);
                    findViewById(R.id.firstStepView).setVisibility(View.GONE);
                   // hideKeyboardFrom(ExecutionActivity.this, feederNote);
            //}
            }
        });


        findViewById(R.id.Execute).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   if (validate(ExecutionActivity.this,SecondStep)){
                    AddExection();
             //   }
            }
        });
    
    }

    private void AddExection(){
        final Progress progress = new Progress(ExecutionActivity.this);
        progress.show();
        Call<AddExecutionResponse> responseCall = RetrofitClient.getInstance().getApi().add_execution_details(USER.getReportingId(),
                USER.getUserId(),
                consumerDetailsItem.getConsumerNo(),
                USER.getDivision(),
                surveyId,PoleShifting.getNumber(),PoleErection.getText().toString(),STRShiftinginnumbers.getNumber(),StringPerLocation.getText().toString(),DTRErectioninnumbers.getNumber(),FinishingPerLocation.getNumber(),transformer_make.getText().toString(),
                tf_sl_no.getText().toString(),tf_capacity_in_kva.getText().toString(),meter_make.getText().toString(),meter_no.getText().toString(),mtr9_psc.getNumber(),mtr9_rsj.getNumber(),mtr11_rsj.getNumber()
                );
        responseCall.enqueue(new Callback<AddExecutionResponse>() {
            @Override
            public void onResponse(Call<AddExecutionResponse> call, Response<AddExecutionResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        new MaterialAlertDialogBuilder(ExecutionActivity.this, R.style.AlertDiloge)
                                .setTitle("Alert")
                                .setMessage(response.body().getMsg())
                                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ExecutionActivity.this.finish();
                                    }
                                })
                                .show();

                    }else {
                        Constants.Alert(ExecutionActivity.this,response.body().getMsg());
                    }
                else {
                    try {
                        /*JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jObjError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();*/
                        Log.d(TAG, "onResponse: "+response.errorBody().string());
                    } catch (Exception e) {
                       // Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }


            }

            @Override
            public void onFailure(Call<AddExecutionResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

    private void showChoiceDialog(final TextView v, final String[] strings) {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ExecutionActivity.this);
        builder.setCancelable(true);
        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                v.setText(strings[which]);
                if (v.getError() != null)
                    v.setError(null);


            }
        });
        builder.show();
    }


}
