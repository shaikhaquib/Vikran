package com.keights.vikran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.ResponseModel.ConsumerDetailsItem;
import com.keights.vikran.ResponseModel.SearchConsumerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.keights.vikran.LoginActivity.USER;

public class NewSurveyActivity extends AppCompatActivity {
    Frg_DetailNewSurvey frg_detailNewSurvey;
    TextInputEditText edtconsumerNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_survey);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        edtconsumerNo = findViewById(R.id.edtconsumerNo);
        setSupportActionBar(toolbar);
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
        Call<SearchConsumerResponse> responseCall = RetrofitClient.getInstance().getApi().search_consumer(USER.getReportingId(),USER.getUserId(),consumerNo);
        responseCall.enqueue(new Callback<SearchConsumerResponse>() {
            @Override
            public void onResponse(Call<SearchConsumerResponse> call, Response<SearchConsumerResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        Bundle bundle = new Bundle();
                        if (response.body().getConsumerDetails().size()>0) {
                            ConsumerDetailsItem consumerDetailsItem = response.body().getConsumerDetails().get(0);
                            bundle.putSerializable("Data", consumerDetailsItem);
                            RelativeLayout relativeLayout = findViewById(R.id.fragemntContener);
                            LinearLayout consumerNoLayout = findViewById(R.id.consumerNoLayout);
                            relativeLayout.setVisibility(View.VISIBLE);
                            consumerNoLayout.setVisibility(View.GONE);
                            frgTransaction(bundle);
                        }else
                            Constants.Alert(NewSurveyActivity.this,"No data available for the entered Consumer Number\n\nYou have Entered Invalid Consumer Number ");
                    }else {
                        Constants.sessionExpired(NewSurveyActivity.this,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<SearchConsumerResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

    public void frgTransaction(Bundle bundle){
        frg_detailNewSurvey.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frgHolder, frg_detailNewSurvey);
        transaction.commit();
    }

}
