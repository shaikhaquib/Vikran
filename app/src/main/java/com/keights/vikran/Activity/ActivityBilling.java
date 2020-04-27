package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.BillingDetailsItem;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.BillingResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class ActivityBilling extends AppCompatActivity {

    private ConsumerDetailsItem consumerDetailsItem;
    private BillingDetailsItem billingDetailsItem;
    TextView cConsumerNo, cName, cDivision, cTaluka, cSubDivision, cSection, cVillage, cVoltagelevel, cDTCCode, sanctionedLoad, txtLocation, txtSurveyby,billingStatus;
    RadioGroup billing;
    TextView date, remark;
    TextInputEditText edtremark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        consumerDetailsItem = (ConsumerDetailsItem) getIntent().getSerializableExtra("Data");

        billing = findViewById(R.id.billing);
        billingStatus = findViewById(R.id.billingStatus);
        date = findViewById(R.id.date);
        remark = findViewById(R.id.remark);
        edtremark = findViewById(R.id.edtremark);

        if (getIntent().hasExtra("billingDetailsItem")){
            billingDetailsItem = (BillingDetailsItem) getIntent().getSerializableExtra("billingDetailsItem");
            findViewById(R.id.detailsResponce).setVisibility(View.VISIBLE);
            findViewById(R.id.submit).setVisibility(View.GONE);
            billing.setVisibility(View.GONE);
            edtremark.setVisibility(View.GONE);

            if (billingDetailsItem.getBillingStatus().equals("0"))
                billingStatus.setText("No");
            else
                billingStatus.setText("Yes");

            date.setText(billingDetailsItem.getCreatedDate());
            remark.setText(billingDetailsItem.getRemark());


        }else {
            findViewById(R.id.detailsResponce).setVisibility(View.GONE);
            findViewById(R.id.submit).setVisibility(View.VISIBLE);
            billing.setVisibility(View.VISIBLE);
            edtremark.setVisibility(View.VISIBLE);

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
        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_permission_commission();
            }
        });
        initToolbar();

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

    private void add_permission_commission( ) {
        final Progress progress = new Progress(ActivityBilling.this);
        progress.show();
        Call<BillingResponse> responseCall = RetrofitClient.getInstance().getApi().add_billing(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision()
                ,getRadioValue(billing),edtremark.getText().toString());
        responseCall.enqueue(new Callback<BillingResponse>() {
            @Override
            public void onResponse(Call<BillingResponse> call, Response<BillingResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        new MaterialAlertDialogBuilder(ActivityBilling.this, R.style.AlertDiloge)
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
                        Constants.Alert(ActivityBilling.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<BillingResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

    public String getRadioValue(RadioGroup radioGroup) {
        int id = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(id);
        int radioId = radioGroup.indexOfChild(radioButton);
        RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);

        if (btn.getText().toString().equals("Yes")) {
            return "1";
        } else {
            return "0";
        }
    }


}
