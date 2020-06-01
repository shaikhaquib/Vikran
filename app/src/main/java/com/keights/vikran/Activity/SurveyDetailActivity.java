package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.SurveyDetailsItem;

public class SurveyDetailActivity extends AppCompatActivity {
    private ConsumerDetailsItem consumerDetailsItem;
    private SurveyDetailsItem surveyDetailsItem;
    private TextView cConsumerNo,cName,cDivision,cTaluka,cSubDivision,cSection,cVillage,cVoltagelevel,cDTCCode,sanctionedLoad;

    private TextView edtSurveyPairing;
    private TextView edtHTline       ;
    private TextView edtRSJPole      ;
    private TextView edtSurveyBy     ;
    private TextView edtAproachRoad  ;
    private TextView edtSoilStrata   ;
    private TextView edtRow          ;
    private TextView edtTreeCutting  ;
    private TextView no_of_gaurding  ;
    private TextView no_of_crossing  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_detail);
        consumerDetailsItem = (ConsumerDetailsItem) getIntent().getSerializableExtra("Data");
        surveyDetailsItem = (SurveyDetailsItem) getIntent().getSerializableExtra("surveyDetailsItem");
        initView();
        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


        edtSurveyPairing = findViewById(R.id.edtSurveyPairing);
        edtHTline = findViewById(R.id.edtHTline);
        edtRSJPole = findViewById(R.id.edtRSJPole);
        edtSurveyBy = findViewById(R.id.edtSurveyBy);
        edtAproachRoad = findViewById(R.id.edtAproachRoad);
        edtSoilStrata = findViewById(R.id.edtSoilStrata);
        edtRow = findViewById(R.id.edtRow);
        edtTreeCutting = findViewById(R.id.edtTreeCutting);
        no_of_crossing = findViewById(R.id.no_of_crossing);
        no_of_gaurding = findViewById(R.id.no_of_gaurding);




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

        edtSurveyPairing.setText(surveyDetailsItem.getSurveyPairing());
        edtHTline.setText(surveyDetailsItem.getHtLineInKm());
        edtRSJPole.setText(surveyDetailsItem.getRjsPoleOldFreezing());
        edtSurveyBy.setText(surveyDetailsItem.getSurveyBy());
        edtAproachRoad.setText(surveyDetailsItem.getApproachRoad());
        edtSoilStrata.setText(surveyDetailsItem.getSoilStrata());
        edtRow.setText(surveyDetailsItem.getRow());
        edtTreeCutting.setText(surveyDetailsItem.getTreeCutting());
        no_of_crossing.setText(surveyDetailsItem.getNoOfCrossing());
        no_of_gaurding.setText(surveyDetailsItem.getNoOfGaurding());
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

}
