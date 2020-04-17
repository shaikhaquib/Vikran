package com.keights.vikran.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import static com.keights.vikran.Extras.Constants.hideKeyboard;
import static com.keights.vikran.Extras.Constants.hideKeyboardFrom;
import static com.keights.vikran.Extras.Constants.validate;
import static com.keights.vikran.LoginActivity.USER;

public class ExecutionActivity extends AppCompatActivity {

    // Consumer Data Card

    List<TextInputEditText> validationList = new ArrayList();
    private TextView cConsumerNo,cName,cDivision,cTaluka,cSubDivision,cSection,cVillage,cVoltagelevel,cDTCCode,sanctionedLoad;

    TextInputEditText CNmFeeder;
    TextInputEditText cSIno;
    TextInputEditText cCapacityKVA;
    TextInputEditText cDTRCode;
    TextInputEditText cMeterNo;
    TextInputEditText gpsDtr;
    TextInputEditText gpsTappingPoll;
    TextInputEditText gpsConsumerNorth;
    TextInputEditText gpsConsumerEast;
    TextInputEditText feederNote;


    private static final String TAG = "ExecutionActivity";

    // Activity Wise Quantity

    List<TextInputEditText> SecondStep = new ArrayList();

    TextInputEditText tenDTR;
    TextInputEditText SixteenDTC;
    TextInputEditText twentyfiveDTC;
    TextInputEditText SringingInMtr;
    TextInputEditText inlinePole;
    TextInputEditText CutpointpoleS1;
    TextInputEditText CutpointpoleS2;
    TextInputEditText CutpointpoleSingle;
    TextInputEditText CutpointpoleDouble;
    TextInputEditText tappingPoll;
    TextInputEditText Guarding;
    TextInputEditText GurdingLengthMtr; 
    TextInputEditText staySet;
    TextInputEditText elevenMtrRSJPole;
    TextInputEditText nineMtrRSJPole;
    TextInputEditText ltABCableinmeter;
    TextInputEditText elevenKVABSwitch; 
    TextInputEditText twelveKVABSwitch;
    ConsumerDetailsItem consumerDetailsItem;
    String surveyId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execution);
        consumerDetailsItem = (ConsumerDetailsItem) getIntent().getSerializableExtra("Data");
        surveyId =  getIntent().getStringExtra("surveyId");

        init();

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

        CNmFeeder = findViewById(R.id.CNmFeeder);
        validationList.add(CNmFeeder);
        cSIno = findViewById(R.id.cSIno);
        validationList.add(cSIno);
        cCapacityKVA = findViewById(R.id.cCapacityKVA);
        validationList.add(cCapacityKVA);
        cDTRCode = findViewById(R.id.cDTRCode);
        validationList.add(cDTRCode);
        cMeterNo = findViewById(R.id.cMeterNo);
        validationList.add(cMeterNo);
        gpsDtr = findViewById(R.id.gpsDtr);
        validationList.add(gpsDtr);
        gpsTappingPoll = findViewById(R.id.gpsTappingPoll);
        validationList.add(gpsTappingPoll);
        gpsConsumerNorth = findViewById(R.id.gpsConsumerNorth);
        validationList.add(gpsConsumerNorth);
        gpsConsumerEast = findViewById(R.id.gpsConsumerEast);
        validationList.add(gpsConsumerEast);
        feederNote = findViewById(R.id.feederNote);
        validationList.add(feederNote);


        findViewById(R.id.secondStep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (validate(ExecutionActivity.this,validationList))
                { */
                    findViewById(R.id.secondStepView).setVisibility(View.VISIBLE);
                    findViewById(R.id.firstStepView).setVisibility(View.GONE);
                    hideKeyboardFrom(ExecutionActivity.this, feederNote);
            //}
            }
        });



        tenDTR = findViewById(R.id.tenDTR);
        SecondStep.add(tenDTR);

        SixteenDTC = findViewById(R.id.SixteenDTC);
        SecondStep.add(SixteenDTC);

        twentyfiveDTC = findViewById(R.id.twentyfiveDTC);
        SecondStep.add(twentyfiveDTC);

        SringingInMtr = findViewById(R.id.SringingInMtr);
        SecondStep.add(SringingInMtr);

        inlinePole = findViewById(R.id.inlinePole);
        SecondStep.add(inlinePole);

        CutpointpoleS1 = findViewById(R.id.CutpointpoleS1);
        SecondStep.add(CutpointpoleS1);

        CutpointpoleS2 = findViewById(R.id.CutpointpoleS2);
        SecondStep.add(CutpointpoleS2);

        CutpointpoleSingle = findViewById(R.id.CutpointpoleSingle);
        SecondStep.add(CutpointpoleSingle);

        CutpointpoleDouble = findViewById(R.id.CutpointpoleDouble);
        SecondStep.add(CutpointpoleDouble);

        tappingPoll = findViewById(R.id.tappingPoll);
        SecondStep.add(tappingPoll);

        Guarding = findViewById(R.id.Guarding);
        SecondStep.add(Guarding);

        GurdingLengthMtr = findViewById(R.id.GurdingLengthMtr);
        SecondStep.add(GurdingLengthMtr);

        staySet = findViewById(R.id.staySet);
        SecondStep.add(staySet);

        elevenMtrRSJPole = findViewById(R.id.elevenMtrRSJPole);
        SecondStep.add(elevenMtrRSJPole);

        nineMtrRSJPole = findViewById(R.id.nineMtrRSJPole);
        SecondStep.add(nineMtrRSJPole);

        ltABCableinmeter = findViewById(R.id.ltABCableinmeter);
        SecondStep.add(ltABCableinmeter);

        elevenKVABSwitch = findViewById(R.id.elevenKVABSwitch);
        SecondStep.add(elevenKVABSwitch);
        
        twelveKVABSwitch = findViewById(R.id.twelveKVABSwitch);
        SecondStep.add(twelveKVABSwitch);

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
                surveyId,
                CNmFeeder.getText().toString(),
                cSIno.getText().toString(),
                cCapacityKVA.getText().toString(),
                cDTRCode.getText().toString(),
                cMeterNo.getText().toString(),
                gpsConsumerNorth.getText().toString(),
                gpsConsumerEast.getText().toString(),
                gpsDtr.getText().toString(),
                tappingPoll.getText().toString(),
                feederNote.getText().toString(),
                tenDTR.getText().toString(),
                SixteenDTC.getText().toString(),
                twentyfiveDTC.getText().toString(),
                SringingInMtr.getText().toString(),
                inlinePole.getText().toString(),
                CutpointpoleS1.getText().toString(),
                CutpointpoleS2.getText().toString(),
                CutpointpoleSingle.getText().toString(),
                CutpointpoleDouble.getText().toString(),
                Guarding.getText().toString(),
                GurdingLengthMtr.getText().toString(),
                staySet.getText().toString(),
                elevenMtrRSJPole.getText().toString(),
                nineMtrRSJPole.getText().toString(),
                ltABCableinmeter.getText().toString(),
                "-",
                gpsConsumerEast.getText().toString(),
                gpsConsumerNorth.getText().toString(),
                elevenKVABSwitch.getText().toString(),
                twelveKVABSwitch.getText().toString()
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

}
