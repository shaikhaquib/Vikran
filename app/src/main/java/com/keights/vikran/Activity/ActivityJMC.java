package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.JMCResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class ActivityJMC extends AppCompatActivity {

    String[] Voltage_Level = new String[] {"11 KV", "22 KV"};
    TextView Line_Section;
    TextView DTR_Section;
    TextView Division;
    TextView cSubDivision;
    TextView cSection;
    TextView Consumer;
    boolean isVisibleA;

    private BottomSheetBehavior mBehavior;
    private BottomSheetDialog mBottomSheetDialog;
    private View bottom_sheet;
    AutoCompleteTextView editTextFilledExposedDropdown;
    ConsumerDetailsItem consumerDetailsItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jmc);

        consumerDetailsItem = (ConsumerDetailsItem) getIntent().getSerializableExtra("Data");


        Division     = findViewById(R.id.Division);
        cSubDivision = findViewById(R.id.cSubDivision);
        cSection     = findViewById(R.id.cSection);
        Consumer     = findViewById(R.id.Consumer);

        DTR_Section = findViewById(R.id.DTR_Section);
        Line_Section = findViewById(R.id.Line_Section);
        editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);

        bottom_sheet = findViewById(R.id.bottom_sheet);
        mBehavior = BottomSheetBehavior.from(bottom_sheet);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        ActivityJMC.this,
                        R.layout.textview,
                        Voltage_Level);


        editTextFilledExposedDropdown.setAdapter(adapter);


        DTR_Section.setText(Voltage_Level[0]+" DTR Section ");
        Line_Section.setText(Voltage_Level[0]+" Line Section ");
        editTextFilledExposedDropdown.setText(Voltage_Level[0]);

        editTextFilledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position,
                                    long id) {
                DTR_Section.setText(Voltage_Level[position]+" DTR Section ");
                Line_Section.setText(Voltage_Level[position]+" Line Section ");
            }
        });

        findViewById(R.id.sectionA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVisibleA){
                    isVisibleA = true ;
                    findViewById(R.id.sectionAView).setVisibility(View.VISIBLE);
                }else {
                    isVisibleA = false ;
                    findViewById(R.id.sectionAView).setVisibility(View.GONE);
                }

            }
        });

        findViewById(R.id.btn9Mtr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("9 Mtr Pole PSC","9",editTextFilledExposedDropdown.getText().toString());
            }
        });
        findViewById(R.id.btn11Mtr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog("11 Mtr RS Joist","11",editTextFilledExposedDropdown.getText().toString());
            }
        });


        Division.setText(consumerDetailsItem.getDivision());
        cSubDivision.setText(consumerDetailsItem.getSubDivision());
        cSection.setText(consumerDetailsItem.getSection());
        Consumer.setText(consumerDetailsItem.getConsumerNo());

    }


    private void showBottomSheetDialog(String title, String mtr,String voltagelevel) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view ;

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setCancelable(false);

        if (mtr.equals("11")){
            view =  getLayoutInflater().inflate(R.layout.bottomsheet_11mtr, null);
            bottomsheet_11mtr(view);
        }
        else {
           view = getLayoutInflater().inflate(R.layout.bottomsheet_9mtr, null);
            bottomsheet_9mtr(view);
        }
        mBottomSheetDialog.setContentView(view);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });

        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });
    }

    private void bottomsheet_9mtr(View view) {

        final TextInputEditText inlinePole = view.findViewById(R.id.inlinePole);
        final TextInputEditText SingleCutPoint = view.findViewById(R.id.SingleCutPoint);
        final TextInputEditText doubleCutPoint = view.findViewById(R.id.doubleCutPoint);
        final TextInputEditText newTappingPole = view.findViewById(R.id.newTappingPole);
        final TextInputEditText ExistingTappingPole =view.findViewById(R.id.ExistingTappingPole);
        final TextInputEditText TappingFromDtc =view.findViewById(R.id.TappingFromDtc);
        final TextInputEditText studPole = view.findViewById(R.id.studPole);
        final TextInputEditText Guarding = view.findViewById(R.id.Guarding);
        final TextInputEditText GourdingSpan = view.findViewById(R.id.GourdingSpan);
        final TextInputEditText staySet = view.findViewById(R.id.staySet);
        final TextInputEditText ItypeDTC = view.findViewById(R.id.ItypeDTC);
        final TextInputEditText spanInMtr = view.findViewById(R.id.spanInMtr);

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJmcDetails("9 Mtr",inlinePole.getText().toString() , SingleCutPoint.getText().toString() ,
                        doubleCutPoint.getText().toString(),newTappingPole.getText().toString() , ExistingTappingPole.getText().toString() , TappingFromDtc.getText().toString(),
                        studPole.getText().toString(),Guarding.getText().toString(),GourdingSpan.getText().toString(),
                        staySet.getText().toString(),ItypeDTC.getText().toString(),spanInMtr.getText().toString());
            }
        });
    }
    private void bottomsheet_11mtr(View view) {
       final TextInputEditText inlinePole = view.findViewById(R.id.inlinePole);
       final TextInputEditText SingleCutPoint = view.findViewById(R.id.SingleCutPoint);
       final TextInputEditText doubleCutPoint = view.findViewById(R.id.doubleCutPoint);

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJmcDetails("11 Mtr" , inlinePole.getText().toString() , SingleCutPoint.getText().toString() , doubleCutPoint.getText().toString(),"0","0","0","0","0","0","0","0","0");
            }

        });

    }
    private void addJmcDetails(String pollType ,String inlinePole ,String SingleCutPoint ,String doubleCutPoint,String newTappingPole ,String ExistingTappingPole ,String TappingFromDtc,
                               String studPole,String Guarding,String GourdingSpan,String staySet,String  ItypeDTC,String spanInMtr) {
        final Progress progress = new Progress(ActivityJMC.this);
        progress.show();
        Call<JMCResponse> responseCall = RetrofitClient.getInstance().getApi().add_jmc_details(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision(),editTextFilledExposedDropdown.getText().toString(),pollType,
                inlinePole,SingleCutPoint,doubleCutPoint,newTappingPole,ExistingTappingPole,studPole,Guarding,GourdingSpan,staySet,ItypeDTC,spanInMtr,TappingFromDtc,"--");
        responseCall.enqueue(new Callback<JMCResponse>() {
            @Override
            public void onResponse(Call<JMCResponse> call, Response<JMCResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        new MaterialAlertDialogBuilder(ActivityJMC.this, R.style.AlertDiloge)
                                .setTitle("Alert")
                                .setMessage(response.body().getMsg())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                       mBottomSheetDialog.dismiss();
                                    }
                                })
                                .show();

                    }else {
                        Constants.Alert(ActivityJMC.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<JMCResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

}
