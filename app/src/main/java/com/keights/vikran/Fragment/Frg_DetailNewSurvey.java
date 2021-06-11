package com.keights.vikran.Fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.R;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.AddSurveyDetailsResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frg_DetailNewSurvey extends Fragment {


    private ConsumerDetailsItem consumerDetailsItem;
    private TextView cConsumerNo, cName, cDivision, cTaluka, cSubDivision, cSection, cVillage, cVoltagelevel, cDTCCode, sanctionedLoad, txtLocation, txtSurveyby;

    private TextInputEditText edtHTline;
    private TextInputEditText edtRSJPole;
    private TextInputEditText edtSurveyBy;
    private TextView edtSoilStrata;

    ElegantNumberButton no_of_crossing;
    ElegantNumberButton no_of_gaurding;

    private RadioGroup edtAproachRoad;
    private RadioGroup edtSurveyPairing;
    private RadioGroup edtRow;
    private RadioGroup edtTreeCutting;

    private MaterialButton logInDetails;

    private String strLocation;
    private static final String TAG = "Frg_DetailNewSurvey";


    private static final int PERMISSION_ID = 44;
    private FusedLocationProviderClient mFusedLocationClient;


    public Frg_DetailNewSurvey() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frg_detail_new_survey, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        consumerDetailsItem = (ConsumerDetailsItem) bundle.getSerializable("Data");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        getLastLocation();

        view.findViewById(R.id.nextedtRSJPole).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtRSJPole.requestFocus();
            }
        });
        view.findViewById(R.id.nextAproachRoad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAproachRoad.requestFocus();
            }
        });


        cConsumerNo = view.findViewById(R.id.cConsumerNo);
        cName = view.findViewById(R.id.cName);
        cDivision = view.findViewById(R.id.cDivision);
        cTaluka = view.findViewById(R.id.cTaluka);
        cSubDivision = view.findViewById(R.id.cSubDivision);
        cSection = view.findViewById(R.id.cSection);
        cVillage = view.findViewById(R.id.cVillage);
        cVoltagelevel = view.findViewById(R.id.cVoltagelevel);
        cDTCCode = view.findViewById(R.id.cDTCCode);
        sanctionedLoad = view.findViewById(R.id.sanctionedLoad);
        txtLocation = view.findViewById(R.id.txtLocation);
        txtSurveyby = view.findViewById(R.id.txtSurveyby);


        edtSurveyPairing = view.findViewById(R.id.edtSurveyPairing);
        edtHTline = view.findViewById(R.id.edtHTline);
        edtRSJPole = view.findViewById(R.id.edtRSJPole);
        edtSurveyBy = view.findViewById(R.id.edtSurveyBy);
        edtAproachRoad = view.findViewById(R.id.edtAproachRoad);
        edtSoilStrata = view.findViewById(R.id.edtSoilStrata);
        edtRow = view.findViewById(R.id.edtRow);
        edtTreeCutting = view.findViewById(R.id.edtTreeCutting);

        logInDetails = view.findViewById(R.id.logInDetails);
        no_of_gaurding = view.findViewById(R.id.no_of_gaurding);
        no_of_crossing = view.findViewById(R.id.no_of_crossing);


        edtSurveyBy.setText(USER.getUsername());

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


        edtSoilStrata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog((TextView) v, new String[]{"Soft Soil", "Murrum", "Mix", "Hard Rock "});
            }
        });

        txtSurveyby.setText(USER.getUsername());

        logInDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidForm()) {
                    if (strLocation.isEmpty()) {
                        new MaterialAlertDialogBuilder(getActivity(), R.style.AlertDiloge)
                                .setTitle("Failed To get Location")
                                .setMessage("Please try again to get location")
                                .setCancelable(false)
                                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getLastLocation();
                                    }
                                })
                                .show();

                    }
                    addSurveyDetails();
                }

            }
        });


    }

    private void showChoiceDialog(final TextView v, final String[] strings) {
        final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setCancelable(true);
        builder.setItems(strings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                v.setText(strings[which]);
                if (v.getError() != null)
                    v.setError(null);


            }
        });
        builder.show();
    }

    private boolean isValidForm() {

        if (edtHTline.getText().toString().isEmpty()) {
            Constants.showFormValidationError(getActivity(), edtHTline, "HT Line Field is Empty please enter valid value");
            return false;
        } else if (edtRSJPole.getText().toString().isEmpty()) {
            Constants.showFormValidationError(getActivity(), edtRSJPole, "RSJ Pole Field is Empty please enter valid value");
            return false;
        } else if (edtSurveyBy.getText().toString().isEmpty()) {
            Constants.showFormValidationError(getActivity(), edtSurveyBy, "Survey By Field is Empty please enter valid value");
            return false;
        }else
            return true;
    }


    private boolean checkPermissions() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestPermissions() {
        requestPermissions(
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Granted. Start getting the location information
                getLastLocation();
            }
        }
    }

    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {
                                    strLocation = location.getLatitude() + "," + location.getLongitude();
                                    txtLocation.setText(strLocation);
                                }
                            }
                        }
                );
            } else {
                new MaterialAlertDialogBuilder(getActivity(), R.style.AlertDiloge)
                        .setTitle("Turn on Location")
                        .setMessage("Turn on location service to allow Vikran App to determine your location")
                        .setCancelable(false)
                        .setPositiveButton("Turn On", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "Turn on location", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivityForResult(intent, 2011);
                            }
                        })
                        .show();

            }
        } else {
            requestPermissions();
        }
    }

    private void requestNewLocationData() {

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            strLocation = mLastLocation.getLatitude() + "," + mLastLocation.getLongitude();
        }
    };

    private void addSurveyDetails() {
        final Progress progress = new Progress(getActivity());
        progress.show();
        Call<AddSurveyDetailsResponse> responseCall = RetrofitClient.getInstance().getApi().add_survey_details(USER.getReportingId(), USER.getUserId(), consumerDetailsItem.getConsumerNo(),
                getRadioValue(edtSurveyPairing), edtHTline.getText().toString(), edtRSJPole.getText().toString(), edtSurveyBy.getText().toString(),
                getRadioValue(edtAproachRoad), edtSoilStrata.getText().toString(), getRadioValue(edtRow), getRadioValue(edtTreeCutting),
                "Remark", strLocation,no_of_gaurding.getNumber(),no_of_crossing.getNumber(), USER.getDivision());
        responseCall.enqueue(new Callback<AddSurveyDetailsResponse>() {
            @Override
            public void onResponse(Call<AddSurveyDetailsResponse> call, Response<AddSurveyDetailsResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn()) {
                        new MaterialAlertDialogBuilder(getActivity(), R.style.AlertDiloge)
                                .setTitle("Alert")
                                .setMessage(response.body().getMsg())
                                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getActivity().finish();
                                    }
                                })
                                .show();

                    } else {
                        Constants.Alert(getActivity(), response.body().getMsg());
                    }
                else {
                    try {
                        /*JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getContext(), jObjError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();*/
                        Log.d(TAG, "onResponse: " + response.errorBody().string());
                    } catch (Exception e) {
                        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<AddSurveyDetailsResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2011) {
            getLastLocation();
        }
    }
    public String getRadioValue(RadioGroup radioGroup) {
        int id = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(id);
        int radioId = radioGroup.indexOfChild(radioButton);
        RadioButton btn = (RadioButton) radioGroup.getChildAt(radioId);

        return (String) btn.getText();

    }


}
