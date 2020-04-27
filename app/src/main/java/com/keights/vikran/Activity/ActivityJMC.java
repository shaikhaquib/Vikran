package com.keights.vikran.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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

    String strVoltage;
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
        strVoltage= Voltage_Level[0];
     //   editTextFilledExposedDropdown.setText(Voltage_Level[0]);

        editTextFilledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position,
                                    long id) {
                DTR_Section.setText(Voltage_Level[position]+" DTR Section ");
                Line_Section.setText(Voltage_Level[position]+" Line Section ");
                strVoltage = Voltage_Level[position];
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

        findViewById(R.id.sectionB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetSectionB(DTR_Section.getText().toString());
            }
        });
        findViewById(R.id.sectionC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetSectionC("LT Line Section");
            }
        });
        findViewById(R.id.sectionD).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetSectionD("Meter Board");
            }
        });


        Division.setText(consumerDetailsItem.getDivision());
        cSubDivision.setText(consumerDetailsItem.getSubDivision());
        cSection.setText(consumerDetailsItem.getSection());
        Consumer.setText(consumerDetailsItem.getConsumerNo());
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


    private void showBottomSheetSectionB(String title) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view ;

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setCancelable(false);

        view =  getLayoutInflater().inflate(R.layout.jmc_sectionb, null);

        mBottomSheetDialog.setContentView(view);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        final TextInputEditText _10_kva_dtr = view.findViewById(R.id._10_kva_dtr);
        final TextInputEditText _16_kva_dtr = view.findViewById(R.id._16_kva_dtr);
        final TextInputEditText _25_kva_dtr = view.findViewById(R.id._25_kva_dtr);

        final TextInputLayout til_10_kva_dtr = view.findViewById(R.id.til_10_kva_dtr);
        final TextInputLayout til_16_kva_dtr = view.findViewById(R.id.til_16_kva_dtr);
        final TextInputLayout til_25_kva_dtr = view.findViewById(R.id.til_25_kva_dtr);

        til_10_kva_dtr.setHint(strVoltage+" "+_10_kva_dtr.getHint().toString());
        til_16_kva_dtr.setHint(strVoltage+" "+_16_kva_dtr.getHint().toString());
        til_25_kva_dtr.setHint(strVoltage+" "+_25_kva_dtr.getHint().toString());

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_jmc_b_details(_10_kva_dtr.getText().toString(),
                        _16_kva_dtr.getText().toString(),
                        _25_kva_dtr.getText().toString());
            }
        });


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
    private void showBottomSheetSectionC(String title) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view ;

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setCancelable(false);

        view =  getLayoutInflater().inflate(R.layout.jmc_sectionc, null);

        mBottomSheetDialog.setContentView(view);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        final TextInputEditText new_pole = view.findViewById(R.id.new_pole);
        final TextInputEditText existing_pole = view.findViewById(R.id.existing_pole);
        final TextInputEditText stay = view.findViewById(R.id.stay);
        final TextInputEditText span_in_mtr = view.findViewById(R.id.span_in_mtr);


        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_jmc_c_details(new_pole.getText().toString(),
                        existing_pole.getText().toString(),
                        stay.getText().toString(),
                        span_in_mtr.getText().toString());
            }
        });


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
    private void showBottomSheetSectionD(String title) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view ;

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setCancelable(false);

        view =  getLayoutInflater().inflate(R.layout.jmc_sectiond, null);

        mBottomSheetDialog.setContentView(view);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        final TextInputEditText service_connection = view.findViewById(R.id.service_connection);


        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_jmc_d_details(service_connection.getText().toString());
            }
        });


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
        final TextInputEditText i_type_dtc_inline = view.findViewById(R.id.i_type_dtc_inline);
        final TextInputEditText spanInMtr = view.findViewById(R.id.spanInMtr);
        final TextInputEditText remark = view.findViewById(R.id.remark);

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJmcDetails("9",inlinePole.getText().toString() , SingleCutPoint.getText().toString() ,
                        doubleCutPoint.getText().toString(),newTappingPole.getText().toString() , ExistingTappingPole.getText().toString() , TappingFromDtc.getText().toString(),
                        studPole.getText().toString(),Guarding.getText().toString(),GourdingSpan.getText().toString(),
                        staySet.getText().toString(),ItypeDTC.getText().toString(),i_type_dtc_inline.getText().toString(),spanInMtr.getText().toString(),remark.getText().toString());
            }
        });
    }
    private void bottomsheet_11mtr(View view) {
       final TextInputEditText inlinePole = view.findViewById(R.id.inlinePole);
       final TextInputEditText SingleCutPoint = view.findViewById(R.id.SingleCutPoint);
       final TextInputEditText doubleCutPoint = view.findViewById(R.id.doubleCutPoint);
       final TextInputEditText remark = view.findViewById(R.id.remark);

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJmcDetails("11" , inlinePole.getText().toString() , SingleCutPoint.getText().toString() , doubleCutPoint.getText().toString(),"0","0","0","0","0","0","0","0","0","0",remark.getText().toString());
            }

        });

    }
    private void addJmcDetails(String pollType ,String inlinePole ,String SingleCutPoint ,String doubleCutPoint,String newTappingPole ,String ExistingTappingPole ,String TappingFromDtc,
                               String studPole,String Guarding,String GourdingSpan,String staySet,String  ItypeDTC,String i_type_dtc_inline,String spanInMtr,String remark) {
        final Progress progress = new Progress(ActivityJMC.this);
        progress.show();
        Call<JMCResponse> responseCall = RetrofitClient.getInstance().getApi().add_jmc_details(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision(),strVoltage.replace(" KV+",""),pollType,
                inlinePole,SingleCutPoint,doubleCutPoint,newTappingPole,ExistingTappingPole,studPole,Guarding,GourdingSpan,staySet,ItypeDTC,i_type_dtc_inline,spanInMtr,TappingFromDtc,remark);
        responseCall.enqueue(new Callback<JMCResponse>() {
            @Override
            public void onResponse(Call<JMCResponse> call, Response<JMCResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        new MaterialAlertDialogBuilder(ActivityJMC.this, R.style.AlertDiloge)
                                .setCancelable(false)
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
                else
                    try {
                        /*JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jObjError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();*/
                        Log.d("Error", "onResponse: "+response.errorBody().string());
                    } catch (Exception e) {
                        // Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }


            }

            @Override
            public void onFailure(Call<JMCResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }
    private void add_jmc_b_details(String _10_kva_dtr ,String _16_kva_dtr ,String _25_kva_dtr ) {
        final Progress progress = new Progress(ActivityJMC.this);
        progress.show();
        Call<JMCResponse> responseCall = RetrofitClient.getInstance().getApi().add_jmc_b_details(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision(),editTextFilledExposedDropdown.getText().toString().replace(" KV",""),
                _10_kva_dtr,_16_kva_dtr,_25_kva_dtr);
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
    private void add_jmc_c_details(String new_pole, String existing_pole, String stay, String span_in_mtr) {
        final Progress progress = new Progress(ActivityJMC.this);
        progress.show();
        Call<JMCResponse> responseCall = RetrofitClient.getInstance().getApi().add_jmc_c_details(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision()
                ,new_pole,existing_pole,stay,span_in_mtr);
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
    private void add_jmc_d_details( String service_connection) {
        final Progress progress = new Progress(ActivityJMC.this);
        progress.show();
        Call<JMCResponse> responseCall = RetrofitClient.getInstance().getApi().add_jmc_d_details(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision()
                ,service_connection);
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
