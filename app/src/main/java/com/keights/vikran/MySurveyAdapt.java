package com.keights.vikran;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.keights.vikran.Activity.NewSurveyActivity;
import com.keights.vikran.Activity.SurveyDetailActivity;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.ResponseModel.MyWorkModel;
import com.keights.vikran.ResponseModel.SearchConsumerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class MySurveyAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    List<MyWorkModel> filterLists;
    private final int VIEW_ITEM = 1;
    private final int VIEW_SECTION = 0;
    int count = 0;

    public MySurveyAdapt(Activity activity, List<MyWorkModel> filterLists){
        this.filterLists=filterLists;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        if (viewType == VIEW_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_item, parent, false);
            vh = new ViewHolder(v);
        }else{
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_section, parent, false);
            vh = new SectionViewHolder(v);
        }
        vh.setIsRecyclable(false);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyWorkModel list = filterLists.get(position);
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder)holder;
            viewHolder.bind(list);

        }else {
            SectionViewHolder view = (SectionViewHolder) holder;
            view.title_section.setText(list.getSectionName());
        }


    }

    @Override
    public int getItemCount() {
        return filterLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (filterLists.get(position).getSection()){
            return VIEW_SECTION;
        }else {
            return VIEW_ITEM;
        }
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCount;
        TextView sName;
        TextView sConsumerNo;
        TextView sDivision;
        TextView sDate;
        TextView sAssignedTo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCount = itemView.findViewById(R.id.txtCount);
            sName = itemView.findViewById(R.id.sName);
            sConsumerNo = itemView.findViewById(R.id.sConsumerNo);
            sDivision = itemView.findViewById(R.id.sDivision);
            sDate = itemView.findViewById(R.id.sDate);
            sAssignedTo = itemView.findViewById(R.id.sAssignedTo);

        }

        public void bind(final MyWorkModel list) {
            sName.setText(list.getConsumerName());
            sConsumerNo.setText(list.getConsumerNo());
            sDivision.setText(list.getDivision());
            count++;
            txtCount.setText(String.valueOf(count));

            sConsumerNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, NewSurveyActivity.class).putExtra("ConsumerNo",list.getConsumerNo()));
                    }
            });
        }
    }

    class SectionViewHolder extends RecyclerView.ViewHolder {
        public TextView title_section;

        public SectionViewHolder(View v) {
            super(v);
            title_section = (TextView) v.findViewById(R.id.title_section);
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
