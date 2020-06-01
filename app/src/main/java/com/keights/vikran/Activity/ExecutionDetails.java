package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.ExecutionDetailsItem;

public class ExecutionDetails extends AppCompatActivity {

    private TextView cConsumerNo,cName,cDivision,cTaluka,cSubDivision,cSection,cVillage,cVoltagelevel,cDTCCode,sanctionedLoad,cSurveyTakenBy,surveyDate,transformer_make ,tf_sl_no,meter_make,meter_no;
    TextView num_of_pole,PoleShifting,StringPerLocation,STRShiftinginnumbers,DTRErectioninnumbers,FinishingPerLocation,mtr9_psc,mtr9_rsj,mtr11_rsj;
    TextView PoleErection,tf_capacity_in_kva;
    ConsumerDetailsItem consumerDetailsItem;
    ExecutionDetailsItem executionDetailsItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_execution_details);
        initToolbar();
        consumerDetailsItem = NewSurveyActivity.consumerDetails.getConsumerDetails().get(0);
        executionDetailsItem = NewSurveyActivity.consumerDetails.getExecutionDetails().get(0);

        init();
    }
    void init() {

        cConsumerNo = findViewById(R.id.cNo);
        cName = findViewById(R.id.cName);
        cDivision = findViewById(R.id.cDivision);
        cTaluka = findViewById(R.id.cTaluka);
        cSubDivision = findViewById(R.id.cSubDivision);
        cSection = findViewById(R.id.cSection);
        cSurveyTakenBy = findViewById(R.id.cSurveyTakenBy);
        cVillage = findViewById(R.id.cVillage);
        cVoltagelevel = findViewById(R.id.cVoltagelevel);
        cDTCCode = findViewById(R.id.cDTCCode);
        sanctionedLoad = findViewById(R.id.sanctionedLoad);
        surveyDate = findViewById(R.id.surveyDate);

        StringPerLocation   = findViewById(R.id.StringPerLocation);
        PoleErection        = findViewById(R.id.PoleErection);
        transformer_make    = findViewById(R.id.transformer_make);
        tf_sl_no            = findViewById(R.id.tf_sl_no);
        tf_capacity_in_kva  = findViewById(R.id.tf_capacity_in_kva);
        meter_make          = findViewById(R.id.meter_make);
        meter_no            = findViewById(R.id.meter_no);
        PoleShifting        = findViewById(R.id.PoleShifting);
        num_of_pole         = findViewById(R.id.num_of_pole);
        STRShiftinginnumbers = findViewById(R.id.STRShiftinginnumbers);
        DTRErectioninnumbers = findViewById(R.id.DTRErectioninnumbers);
        FinishingPerLocation = findViewById(R.id.FinishingPerLocation);
        mtr9_psc            = findViewById(R.id.mtr9_psc);
        mtr9_rsj            = findViewById(R.id.mtr9_rsj);
        mtr11_rsj           = findViewById(R.id.mtr11_rsj);



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
        cSurveyTakenBy.setText(consumerDetailsItem.getCreatedName());
        surveyDate.setText(Constants.Date(consumerDetailsItem.getCreatedDate()));

        StringPerLocation   .setText(executionDetailsItem.getStringPerLocation());
        PoleErection        .setText(executionDetailsItem.getPoleErection());
        transformer_make    .setText(executionDetailsItem.getTransformerMake());
        tf_sl_no            .setText(executionDetailsItem.getTfSlNo());
        tf_capacity_in_kva  .setText(executionDetailsItem.getTfCapacityInKva());
        meter_make          .setText(executionDetailsItem.getMeterMake());
        meter_no            .setText(executionDetailsItem.getMeterNo());
        PoleShifting        .setText(executionDetailsItem.getPoleShifting());
        num_of_pole         .setText(executionDetailsItem.getNumOfPole());
        STRShiftinginnumbers.setText(executionDetailsItem.getStrFitting());
        DTRErectioninnumbers.setText(executionDetailsItem.getDtrErection());
        FinishingPerLocation.setText(executionDetailsItem.getFinishingPerLocation());
        mtr9_psc            .setText(executionDetailsItem.getMtr9Psc());
        mtr9_rsj            .setText(executionDetailsItem.getMtr9Rsj());
        mtr11_rsj           .setText(executionDetailsItem.getMtr11Rsj());


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
}