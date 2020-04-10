package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Fragment.Frg_DetailNewSurvey;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.ExecutionDetailsItem;
import com.keights.vikran.ResponseModel.SearchConsumerResponse;
import com.keights.vikran.ResponseModel.SurveyDetailsItem;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class NewSurveyActivity extends AppCompatActivity {
    Frg_DetailNewSurvey frg_detailNewSurvey;
    TextInputEditText edtconsumerNo;
    ConsumerDetailsItem consumerDetailsItem;
    SurveyDetailsItem surveyDetailsItem ;
    private TextView cConsumerNo,cName,cDivision,cTaluka,cSubDivision,cSection,cVillage,cVoltagelevel,cDTCCode,sanctionedLoad,resultMessage;
    MaterialButton addSurvey,Execute,Survey,JMC;
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

    private void VerifyData(SearchConsumerResponse body) {
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
            resultMessage.setText("Survey pending please add one ");
            addSurvey.setVisibility(View.VISIBLE);
        }else if(body.getConsumerDetails().getExecutionDetails().isEmpty()){
            resultMessage.setText("Execution is not taken");
            Survey.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);

        }else if(body.getConsumerDetails().getExecutionDetails().get(0).getExecutionStatus().equals("not_approved")){
            resultMessage.setText("Execution not approved");
            Survey.setVisibility(View.VISIBLE);
            Execute.setVisibility(View.VISIBLE);
        } else if(body.getConsumerDetails().getExecutionDetails().get(0).getExecutionStatus().equals("pending")){
            resultMessage.setText("Execution is under review");
            Survey.setVisibility(View.VISIBLE);
        } else if(body.getConsumerDetails().getExecutionDetails().get(0).getExecutionStatus().equals("approved")){
            resultMessage.setText("Execution is successfully approved");
            Survey.setVisibility(View.VISIBLE);
            JMC.setVisibility(View.VISIBLE);
        }
        addSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSurveyFragement();
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


        }

    private void SurveyDetails() {
        Intent intent = new Intent(getApplicationContext(),SurveyDetailActivity.class);
        intent.putExtra("Data", consumerDetailsItem);
        intent.putExtra("surveyDetailsItem", surveyDetailsItem);
        startActivity(intent);
        finish();
    }
    private void mJmc() {
        Intent intent = new Intent(getApplicationContext(),ActivityJMC.class);
        intent.putExtra("Data", consumerDetailsItem);
        intent.putExtra("surveyDetailsItem", surveyDetailsItem);
        startActivity(intent);
        finish();
    }

        private void openExecutePage(){
            Intent intent = new Intent(getApplicationContext(),ExecutionActivity.class);
            intent.putExtra("Data", consumerDetailsItem);
            intent.putExtra("surveyId", surveyDetailsItem.getSurveyId());
            startActivity(intent);
            finish();
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

}
