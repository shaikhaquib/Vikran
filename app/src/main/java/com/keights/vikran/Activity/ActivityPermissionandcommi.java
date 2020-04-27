package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.PerComDetailsItem;
import com.keights.vikran.ResponseModel.PermcommResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.Extras.Constants.Date;
import static com.keights.vikran.LoginActivity.USER;

public class ActivityPermissionandcommi extends AppCompatActivity {

    PerComDetailsItem perComDetailsItem;
    ConsumerDetailsItem consumerDetailsItem;

    TextView txtAppliedE1,txtReceivedE1,txtAppliedComm,txtRemark,txtDate;
    RadioGroup E1Permission,E2Permission , Commission;
    TextInputEditText remark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissionandcommi);

        consumerDetailsItem = (ConsumerDetailsItem) getIntent().getSerializableExtra("Data");

        txtAppliedE1 = findViewById(R.id.txtAppliedE1);
        txtReceivedE1 = findViewById(R.id.txtReceivedE1);
        txtAppliedComm = findViewById(R.id.txtAppliedComm);
        txtRemark = findViewById(R.id.txtRemark);
        txtDate = findViewById(R.id.txtDate);

        E1Permission = findViewById(R.id.E1Permission);
        E2Permission = findViewById(R.id.E2Permission);
        Commission = findViewById(R.id.Commission);
        remark = findViewById(R.id.remark);


        if (getIntent().hasExtra("perComDetailsItem")){
            perComDetailsItem = (PerComDetailsItem) getIntent().getSerializableExtra("perComDetailsItem");
            findViewById(R.id.detailview).setVisibility(View.VISIBLE);
            findViewById(R.id.addView).setVisibility(View.GONE);

            if (perComDetailsItem.getAppliedE1Permission().equals("0"))
                txtAppliedE1.setText("No");
            else
                txtAppliedE1.setText("Yes");

            if (perComDetailsItem.getReceivedE1Permission().equals("0"))
                txtReceivedE1.setText("No");
            else
                txtReceivedE1.setText("Yes");

            if (perComDetailsItem.getAppliedCommission().equals("0"))
                txtAppliedComm.setText("No");
            else
                txtAppliedComm.setText("Yes");

            if (perComDetailsItem.getAppliedE1Permission().equals("0"))
                txtAppliedE1.setText("No");
            else
                txtAppliedE1.setText("Yes");

            txtRemark.setText(perComDetailsItem.getRemark());
            txtDate.setText(Date(perComDetailsItem.getCreatedDate()));


        }else {
            findViewById(R.id.detailview).setVisibility(View.GONE);
            findViewById(R.id.addView).setVisibility(View.VISIBLE);
        }

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_permission_commission();
            }
        });


    }


    private void add_permission_commission( ) {
        final Progress progress = new Progress(ActivityPermissionandcommi.this);
        progress.show();
        Call<PermcommResponse> responseCall = RetrofitClient.getInstance().getApi().add_permission_commission(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision()
                ,getRadioValue(E1Permission),getRadioValue(E2Permission),getRadioValue(Commission),remark.getText().toString());
        responseCall.enqueue(new Callback<PermcommResponse>() {
            @Override
            public void onResponse(Call<PermcommResponse> call, Response<PermcommResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        new MaterialAlertDialogBuilder(ActivityPermissionandcommi.this, R.style.AlertDiloge)
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
                        Constants.Alert(ActivityPermissionandcommi.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<PermcommResponse> call, Throwable t) {
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
