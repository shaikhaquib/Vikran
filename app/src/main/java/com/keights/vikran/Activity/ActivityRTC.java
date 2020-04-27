package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.RTCResponse;
import com.keights.vikran.ResponseModel.RtcDetailsItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class ActivityRTC extends AppCompatActivity {

    TextView cConsumerNo, cName, cDivision, cTaluka, cSubDivision, cSection, cVillage, cVoltagelevel, cDTCCode, sanctionedLoad, txtLocation, txtSurveyby;
    private ConsumerDetailsItem consumerDetailsItem;
    private RtcDetailsItem rtcDetailsItem;
    RadioGroup RTCcompleted;
    Button AddRtcStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtc);

        consumerDetailsItem = (ConsumerDetailsItem) getIntent().getSerializableExtra("Data");


        if (getIntent().hasExtra("RTC")){
            rtcDetailsItem = (RtcDetailsItem) getIntent().getSerializableExtra("RTC");

            findViewById(R.id.RTCDetails).setVisibility(View.VISIBLE);
            TextView RTCStatus =findViewById(R.id.RTCStatus);
            RTCStatus.setText(rtcDetailsItem.getRtcStatus());

            TextView RTCDate =findViewById(R.id.RTCDate);
            RTCDate.setText(Constants.Date(rtcDetailsItem.getCreatedDate()));
        }else {
            findViewById(R.id.addRtc).setVisibility(View.VISIBLE);
        }

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
        txtLocation = findViewById(R.id.txtLocation);
        txtSurveyby = findViewById(R.id.txtSurveyby);

        RTCcompleted = findViewById(R.id.RTCcompleted);
        AddRtcStatus = findViewById(R.id.AddRtcStatus);



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

        AddRtcStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_rtc_details();
            }
        });
    }


    private void add_rtc_details( ) {
        final Progress progress = new Progress(ActivityRTC.this);
        progress.show();
        Call<RTCResponse> responseCall = RetrofitClient.getInstance().getApi().add_rtc_details(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision()
                ,getStatus());
        responseCall.enqueue(new Callback<RTCResponse>() {
            @Override
            public void onResponse(Call<RTCResponse> call, Response<RTCResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        new MaterialAlertDialogBuilder(ActivityRTC.this, R.style.AlertDiloge)
                                .setTitle("Alert")
                                .setMessage(response.body().getMsg())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .show();

                    }else {
                        Constants.Alert(ActivityRTC.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<RTCResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }



    public String getStatus() {
        int id = RTCcompleted.getCheckedRadioButtonId();
        View radioButton = RTCcompleted.findViewById(id);
        int radioId = RTCcompleted.indexOfChild(radioButton);
        RadioButton btn = (RadioButton) RTCcompleted.getChildAt(radioId);

        if (btn.getText().toString().equals("Yes")) {
            return "1";
        } else {
            return "0";
        }
    }


}
