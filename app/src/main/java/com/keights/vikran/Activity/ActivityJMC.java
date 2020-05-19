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

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
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
import com.keights.vikran.ResponseModel.ConsumerDetails;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.JMCResponse;
import com.keights.vikran.ResponseModel.JmcSectionADetailsItem;
import com.keights.vikran.ResponseModel.JmcSectionBDetailsItem;
import com.keights.vikran.ResponseModel.JmcSectionCDetailsItem;
import com.keights.vikran.ResponseModel.JmcSectionDDetailsItem;
import com.keights.vikran.ResponseModel.JmcSectionEDetailsItem;

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
    ConsumerDetails consumerDetails;

    String strVoltage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jmc);

        consumerDetails = NewSurveyActivity.consumerDetails;
        consumerDetailsItem = consumerDetails.getConsumerDetails().get(0);


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

        findViewById(R.id.sectionE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetSectionE("Substation & Feeder Details");
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

        final ElegantNumberButton _10_kva_dtr = view.findViewById(R.id._10_kva_dtr);
        final ElegantNumberButton _16_kva_dtr = view.findViewById(R.id._16_kva_dtr);
        final ElegantNumberButton _25_kva_dtr = view.findViewById(R.id._25_kva_dtr);

        final TextView til_10_kva_dtr = view.findViewById(R.id.til_10_kva_dtr);
        final TextView til_16_kva_dtr = view.findViewById(R.id.til_16_kva_dtr);
        final TextView til_25_kva_dtr = view.findViewById(R.id.til_25_kva_dtr);
        
        final TextView txt_10_kva_dtr = view.findViewById(R.id.txt_10_kva_dtr);
        final TextView txt_16_kva_dtr = view.findViewById(R.id.txt_16_kva_dtr);
        final TextView txt_25_kva_dtr = view.findViewById(R.id.txt_25_kva_dtr);

        til_10_kva_dtr.setText(strVoltage+" "+til_10_kva_dtr.getText().toString());
        til_16_kva_dtr.setText(strVoltage+" "+til_16_kva_dtr.getText().toString());
        til_25_kva_dtr.setText(strVoltage+" "+til_25_kva_dtr.getText().toString());



        if (!consumerDetails.getJmcSectionBDetails().isEmpty()){
            JmcSectionBDetailsItem jmcSectionBDetailsItem = consumerDetails.getJmcSectionBDetails().get(0);
            _10_kva_dtr.setVisibility(View.GONE);
            _16_kva_dtr.setVisibility(View.GONE);
            _25_kva_dtr.setVisibility(View.GONE);

            txt_10_kva_dtr.setVisibility(View.VISIBLE);
            txt_16_kva_dtr.setVisibility(View.VISIBLE);
            txt_25_kva_dtr.setVisibility(View.VISIBLE);

            txt_10_kva_dtr.setText(jmcSectionBDetailsItem.getTenKvaDtr());
            txt_16_kva_dtr.setText(jmcSectionBDetailsItem.getSixteenKvaDtr());
            txt_25_kva_dtr.setText(jmcSectionBDetailsItem.getTwentyfiveKvaDtr());

            
            view.findViewById(R.id.btnSave).setVisibility(View.GONE);
            
        }

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_jmc_b_details(_10_kva_dtr.getNumber(),
                        _16_kva_dtr.getNumber(),
                        _25_kva_dtr.getNumber());
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

        final ElegantNumberButton new_pole = view.findViewById(R.id.new_pole);
        final ElegantNumberButton existing_pole = view.findViewById(R.id.existing_pole);
        final ElegantNumberButton stay = view.findViewById(R.id.stay);

        final TextView txtnew_pole = view.findViewById(R.id.txtnew_pole);
        final TextView txtexisting_pole = view.findViewById(R.id.txtexisting_pole);
        final TextView txtstay = view.findViewById(R.id.txtstay);
        final TextInputEditText span_in_mtr = view.findViewById(R.id.span_in_mtr);

        if (!consumerDetails.getJmcSectionCDetails().isEmpty()){
            JmcSectionCDetailsItem jmcSectionCDetailsItem = consumerDetails.getJmcSectionCDetails().get(0);
            new_pole.setVisibility(View.GONE);
            existing_pole.setVisibility(View.GONE);
            stay.setVisibility(View.GONE);

            txtnew_pole.setVisibility(View.VISIBLE);
            txtexisting_pole.setVisibility(View.VISIBLE);
            txtstay.setVisibility(View.VISIBLE);

            txtnew_pole.setText(jmcSectionCDetailsItem.getNewPole());
            txtexisting_pole.setText(jmcSectionCDetailsItem.getExistingPole());
            txtstay.setText(jmcSectionCDetailsItem.getStay());
            span_in_mtr.setText(jmcSectionCDetailsItem.getSpanInMtr());
            span_in_mtr.setEnabled(false);

            
            view.findViewById(R.id.btnSave).setVisibility(View.GONE);

        }



        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_jmc_c_details(new_pole.getNumber(),
                        existing_pole.getNumber(),
                        stay.getNumber(),
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

        final ElegantNumberButton service_connection   = view.findViewById(R.id.service_connection);
        final TextView txtservice_connection = view.findViewById(R.id.txtservice_connection);

        if (!consumerDetails.getJmcSectionDDetails().isEmpty()){
            JmcSectionDDetailsItem jmcSectionDDetailsItem = consumerDetails.getJmcSectionDDetails().get(0);
            service_connection.setVisibility(View.GONE);

            txtservice_connection.setVisibility(View.VISIBLE);


            txtservice_connection.setText(jmcSectionDDetailsItem.getServiceConnection());

            
            view.findViewById(R.id.btnSave).setVisibility(View.GONE);

        }


        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_jmc_d_details(service_connection.getNumber());
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
    private void showBottomSheetSectionE(String title) {
        if (mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view ;

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setCancelable(false);

        view =  getLayoutInflater().inflate(R.layout.jmc_sectione, null);

        mBottomSheetDialog.setContentView(view);

        TextView txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText(title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mBottomSheetDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        final TextInputEditText LiveConsumerNo = view.findViewById(R.id.LiveConsumerNo);
        final TextInputEditText NameofSubStation = view.findViewById(R.id.NameofSubStation);
        final TextInputEditText Nameoffeeder = view.findViewById(R.id.Nameoffeeder);
        final TextInputEditText GPScoordinatesofDTRinDecimalNorth = view.findViewById(R.id.GPScoordinatesofDTRinDecimalNorth);
        final TextInputEditText GPScoordinatesofDTRinDecimalEast = view.findViewById(R.id.GPScoordinatesofDTRinDecimalEast);
        final TextInputEditText GPScoordinatesofTappingpoleinDecimalNorth = view.findViewById(R.id.GPScoordinatesofTappingpoleinDecimalNorth);
        final TextInputEditText GPScoordinatesofTappingpoleinDecimalEast = view.findViewById(R.id.GPScoordinatesofTappingpoleinDecimalEast);
        final TextInputEditText GPScoordinatesofLiveConsumerinDecimalNorth = view.findViewById(R.id.GPScoordinatesofLiveConsumerinDecimalNorth);
        final TextInputEditText GPScoordinatesofLiveConsumerinDecimalEast = view.findViewById(R.id.GPScoordinatesofLiveConsumerinDecimalEast);
        final TextInputEditText Feedernote = view.findViewById(R.id.Feedernote);
        final TextInputEditText DTRCode=view.findViewById(R.id.DTRCode);

        if (!consumerDetails.getJmcSectionEDetails().isEmpty()){

            JmcSectionEDetailsItem  jmcSectionEDetailsItem =consumerDetails.getJmcSectionEDetails().get(0);

            LiveConsumerNo.setEnabled(false);
            NameofSubStation.setEnabled(false);
            Nameoffeeder.setEnabled(false);
            GPScoordinatesofDTRinDecimalNorth.setEnabled(false);
            GPScoordinatesofDTRinDecimalEast.setEnabled(false);
            GPScoordinatesofTappingpoleinDecimalNorth.setEnabled(false);
            GPScoordinatesofTappingpoleinDecimalEast.setEnabled(false);
            GPScoordinatesofLiveConsumerinDecimalNorth.setEnabled(false);
            GPScoordinatesofLiveConsumerinDecimalEast.setEnabled(false);
            Feedernote.setEnabled(false);
            DTRCode.setEnabled(false);


            LiveConsumerNo.setText("--");
            NameofSubStation.setText("--");
            Nameoffeeder.setText("--");
            GPScoordinatesofDTRinDecimalNorth.setText(jmcSectionEDetailsItem.getGpsDtrNorth());
            GPScoordinatesofDTRinDecimalEast.setText(jmcSectionEDetailsItem.getGpsDtrEast());
            GPScoordinatesofTappingpoleinDecimalNorth.setText(jmcSectionEDetailsItem.getGpsTappingNorth());
            GPScoordinatesofTappingpoleinDecimalEast.setText(jmcSectionEDetailsItem.getGpsTappingEast());
            GPScoordinatesofLiveConsumerinDecimalNorth.setText(jmcSectionEDetailsItem.getGpsLiveNorth());
            GPScoordinatesofLiveConsumerinDecimalEast.setText(jmcSectionEDetailsItem.getGpsLiveEast());
            Feedernote.setText(jmcSectionEDetailsItem.getFeederNote());
            DTRCode.setText(jmcSectionEDetailsItem.getDtrCode());



            
            view.findViewById(R.id.btnSave).setVisibility(View.GONE);

        }


        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_jmc_e_details(LiveConsumerNo.getText().toString(),NameofSubStation.getText().toString(),Nameoffeeder.getText().toString(),
                        GPScoordinatesofDTRinDecimalNorth.getText().toString(),GPScoordinatesofDTRinDecimalEast.getText().toString(),
                        GPScoordinatesofTappingpoleinDecimalNorth.getText().toString(),GPScoordinatesofTappingpoleinDecimalEast.getText().toString(),
                        GPScoordinatesofLiveConsumerinDecimalNorth.getText().toString(),GPScoordinatesofLiveConsumerinDecimalEast.getText().toString(),
                        Feedernote.getText().toString(),DTRCode.getText().toString());
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

    private void add_jmc_e_details(String LiveConsumerNo, String NameofSubStation, String Nameoffeeder, String GPScoordinatesofDTRinDecimalNorth,String GPScoordinatesofDTRinDecimalEast, String GPScoordinatesofTappingpoleinDecimalNorth,String GPScoordinatesofTappingpoleinDecimalEast,
                                   String GPScoordinatesofLiveConsumerinDecimalNorth, String GPScoordinatesofLiveConsumerinDecimalEast, String Feedernote, String DTRCode) {
        final Progress progress = new Progress(ActivityJMC.this);
        progress.show();
        Call<JMCResponse> responseCall = RetrofitClient.getInstance().getApi().add_jmc_e_details(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision()
                ,LiveConsumerNo,Nameoffeeder,NameofSubStation,GPScoordinatesofDTRinDecimalNorth,
                GPScoordinatesofDTRinDecimalEast,GPScoordinatesofTappingpoleinDecimalNorth,GPScoordinatesofTappingpoleinDecimalEast,
                GPScoordinatesofLiveConsumerinDecimalNorth,GPScoordinatesofLiveConsumerinDecimalEast,
                Feedernote,DTRCode);
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


    private void bottomsheet_9mtr(View view) {

        final ElegantNumberButton inlinePole     = view.findViewById(R.id.inlinePole);
        final ElegantNumberButton SingleCutPoint = view.findViewById(R.id.SingleCutPoint);
        final ElegantNumberButton doubleCutPoint = view.findViewById(R.id.doubleCutPoint);
        final ElegantNumberButton newTappingPole = view.findViewById(R.id.newTappingPole);
        final ElegantNumberButton ExistingTappingPole =view.findViewById(R.id.ExistingTappingPole);
        final ElegantNumberButton TappingFromDtc =view.findViewById(R.id.TappingFromDtc);
        final ElegantNumberButton studPole       = view.findViewById(R.id.studPole);
        final ElegantNumberButton Guarding       = view.findViewById(R.id.Guarding);
        final ElegantNumberButton GourdingSpan   = view.findViewById(R.id.GourdingSpan);
        final ElegantNumberButton staySet        = view.findViewById(R.id.staySet);
        final ElegantNumberButton ItypeDTC       = view.findViewById(R.id.ItypeDTC);
        final ElegantNumberButton i_type_dtc_inline = view.findViewById(R.id.i_type_dtc_inline);

        final TextView txtinlinePole = view.findViewById(R.id.txtinlinePole);
        final TextView txtSingleCutPoint = view.findViewById(R.id.txtSingleCutPoint);
        final TextView txtdoubleCutPoint = view.findViewById(R.id.txtdoubleCutPoint);
        final TextView txtnewTappingPole = view.findViewById(R.id.txtnewTappingPole);
        final TextView txtExistingTappingPole =view.findViewById(R.id.txtExistingTappingPole);
        final TextView txtTappingFromDtc =view.findViewById(R.id.txtTappingFromDtc);
        final TextView txtstudPole = view.findViewById(R.id.txtstudPole);
        final TextView txtGuarding = view.findViewById(R.id.txtGuarding);
        final TextView txtGourdingSpan = view.findViewById(R.id.txtGourdingSpan);
        final TextView txtstaySet = view.findViewById(R.id.txtstaySet);
        final TextView txtItypeDTC = view.findViewById(R.id.txtItypeDTC);
        final TextView txti_type_dtc_inline = view.findViewById(R.id.txti_type_dtc_inline);



        final TextInputEditText spanInMtr = view.findViewById(R.id.spanInMtr);
        final TextInputEditText remark = view.findViewById(R.id.remark);



        if (!consumerDetails.getJmcSectionADetails().isEmpty()){
            inlinePole.setVisibility(View.GONE);
            SingleCutPoint.setVisibility(View.GONE);
            doubleCutPoint.setVisibility(View.GONE);
            newTappingPole.setVisibility(View.GONE);
            ExistingTappingPole.setVisibility(View.GONE);
            TappingFromDtc.setVisibility(View.GONE);
            studPole.setVisibility(View.GONE);
            Guarding.setVisibility(View.GONE);
            GourdingSpan.setVisibility(View.GONE);
            staySet.setVisibility(View.GONE);
            ItypeDTC.setVisibility(View.GONE);
            i_type_dtc_inline.setVisibility(View.GONE);

            txtinlinePole.setVisibility(View.VISIBLE);
            txtSingleCutPoint.setVisibility(View.VISIBLE);
            txtdoubleCutPoint.setVisibility(View.VISIBLE);
            txtnewTappingPole.setVisibility(View.VISIBLE);
            txtExistingTappingPole.setVisibility(View.VISIBLE);
            txtTappingFromDtc.setVisibility(View.VISIBLE);
            txtstudPole.setVisibility(View.VISIBLE);
            txtGuarding.setVisibility(View.VISIBLE);
            txtGourdingSpan.setVisibility(View.VISIBLE);
            txtstaySet.setVisibility(View.VISIBLE);
            txtItypeDTC.setVisibility(View.VISIBLE);
            txti_type_dtc_inline.setVisibility(View.VISIBLE);
            JmcSectionADetailsItem jmcSectionADetailsItem  = consumerDetails.getJmcSectionADetails().get(0);

            txtinlinePole.setVisibility(View.VISIBLE);
            txtSingleCutPoint.setVisibility(View.VISIBLE);
            txtdoubleCutPoint.setVisibility(View.VISIBLE);

            txtinlinePole.setText(jmcSectionADetailsItem.getStudPole());
            txtSingleCutPoint.setText(jmcSectionADetailsItem.getSingleCutpointPole());
            txtdoubleCutPoint.setText(jmcSectionADetailsItem.getDoubleCutpointPole());
            txtnewTappingPole.setText(jmcSectionADetailsItem.getNewTapping());
            txtExistingTappingPole.setText(jmcSectionADetailsItem.getExistingTapping());
            txtTappingFromDtc.setText(jmcSectionADetailsItem.getTappingFromExistingDtc());
            txtstudPole.setText(jmcSectionADetailsItem.getStudPole());
            txtGuarding.setText(jmcSectionADetailsItem.getGaurdingSpan());
            txtGourdingSpan.setText(jmcSectionADetailsItem.getGaurdingSpan());
            txtstaySet.setText(jmcSectionADetailsItem.getStaySet());
            txtItypeDTC.setText(jmcSectionADetailsItem.getITypeDtc());
            txti_type_dtc_inline.setText(jmcSectionADetailsItem.getITypeDtcInline());
            spanInMtr.setText(jmcSectionADetailsItem.getSpanInMtr());
            spanInMtr.setEnabled(false);
            remark.setVisibility(View.GONE);
            
            view.findViewById(R.id.btnSave).setVisibility(View.GONE);
        }

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJmcDetails("9",inlinePole.getNumber() , SingleCutPoint.getNumber() ,
                        doubleCutPoint.getNumber(),newTappingPole.getNumber() , ExistingTappingPole.getNumber() , TappingFromDtc.getNumber(),
                        studPole.getNumber(),Guarding.getNumber(),GourdingSpan.getNumber(),
                        staySet.getNumber(),ItypeDTC.getNumber(),i_type_dtc_inline.getNumber(),spanInMtr.getText().toString(),remark.getText().toString());
            }
        });
    }
    private void bottomsheet_11mtr(View view) {
       final ElegantNumberButton inlinePole = view.findViewById(R.id.inlinePole);
       final ElegantNumberButton SingleCutPoint = view.findViewById(R.id.SingleCutPoint);
       final ElegantNumberButton doubleCutPoint = view.findViewById(R.id.doubleCutPoint);

       final TextView txtinlinePole     = view.findViewById(R.id.txtinlinePole);
       final TextView txtSingleCutPoint = view.findViewById(R.id.txtSingleCutPoint);
       final TextView txtdoubleCutPoint = view.findViewById(R.id.txtdoubleCutPoint);
       final TextInputEditText remark   = view.findViewById(R.id.remark);

       if (!consumerDetails.getJmcSectionADetails().isEmpty()){
           inlinePole.setVisibility(View.GONE);
           SingleCutPoint.setVisibility(View.GONE);
           doubleCutPoint.setVisibility(View.GONE);
           JmcSectionADetailsItem jmcSectionADetailsItem  = consumerDetails.getJmcSectionADetails().get(0);

           txtinlinePole.setVisibility(View.VISIBLE);
           txtSingleCutPoint.setVisibility(View.VISIBLE);
           txtdoubleCutPoint.setVisibility(View.VISIBLE);

           txtinlinePole.setText(jmcSectionADetailsItem.getStudPole());
           txtSingleCutPoint.setText(jmcSectionADetailsItem.getSingleCutpointPole());
           txtdoubleCutPoint.setText(jmcSectionADetailsItem.getDoubleCutpointPole());
           remark.setVisibility(View.GONE);
           
           view.findViewById(R.id.btnSave).setVisibility(View.GONE);
       }

        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJmcDetails("11" , inlinePole.getNumber() , SingleCutPoint.getNumber() , doubleCutPoint.getNumber(),"0","0","0","0","0","0","0","0","0","0",remark.getText().toString());
            }

        });

    }
    private void addJmcDetails(String pollType ,String inlinePole ,String SingleCutPoint ,String doubleCutPoint,String newTappingPole ,String ExistingTappingPole ,String TappingFromDtc,
                               String studPole,String Guarding,String GourdingSpan,String staySet,String  ItypeDTC,String i_type_dtc_inline,String spanInMtr,String remark) {
        final Progress progress = new Progress(ActivityJMC.this);
        progress.show();
        Call<JMCResponse> responseCall = RetrofitClient.getInstance().getApi().add_jmc_details(USER.getReportingId(),USER.getUserId(),consumerDetailsItem.getConsumerNo(),USER.getDivision(),strVoltage.replace("KV","").trim(),pollType,
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
