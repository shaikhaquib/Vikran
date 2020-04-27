package com.keights.vikran;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.keights.vikran.Activity.NewSurveyActivity;
import com.keights.vikran.Activity.SurveyDetailActivity;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.ResponseModel.FilterList;
import com.keights.vikran.ResponseModel.SearchConsumerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class MySurveyAdapt extends RecyclerView.Adapter<MySurveyAdapt.ViewHolder> {
    Activity activity;
    List<FilterList> filterLists;
    

    public MySurveyAdapt(Activity activity, List<FilterList> filterLists){
        this.filterLists=filterLists;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.survey_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FilterList list = filterLists.get(position);
        holder.bind(list);

        holder.txtCount.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return filterLists.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCount;
        TextView sName;
        TextView sConsumerNo;
        TextView sStatus;
        TextView sDate;
        TextView sAssignedTo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCount = itemView.findViewById(R.id.txtCount);
            sName = itemView.findViewById(R.id.sName);
            sConsumerNo = itemView.findViewById(R.id.sConsumerNo);
            sStatus = itemView.findViewById(R.id.sStatus);
            sDate = itemView.findViewById(R.id.sDate);
            sAssignedTo = itemView.findViewById(R.id.sAssignedTo);

        }

        public void bind(final FilterList list) {
            sName.setText(list.getConsumerName());
            sConsumerNo.setText(list.getConsumerNo());
            sStatus.setText(list.getStatus());
            sDate.setText(Constants.Date(list.getCreatedDate()));

            sConsumerNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchConsumer(list.getConsumerNo());
                    }
            });
        }
    }

    private void searchConsumer(String consumerNo){
        final Progress progress = new Progress(activity);
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
                            Intent intent = new Intent(activity, SurveyDetailActivity.class);
                            intent.putExtra("Data", response.body().getConsumerDetails().getConsumerDetails().get(0));
                            intent.putExtra("surveyDetailsItem", response.body().getConsumerDetails().getSurveyDetails().get(0));
                            activity.startActivity(intent);
                        }else
                            Constants.Alert(activity,"No data available for the entered Consumer Number\n\nYou have Entered Invalid Consumer Number ");
                    }else {
                        Constants.Alert(activity,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<SearchConsumerResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }

}
