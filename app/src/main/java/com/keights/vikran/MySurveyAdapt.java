package com.keights.vikran;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.keights.vikran.Extras.Constants;
import com.keights.vikran.ResponseModel.FilterList;

import java.util.List;

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

        public void bind(FilterList list) {
            sName.setText(list.getConsumerName());
            sConsumerNo.setText(list.getConsumerNo());
            sStatus.setText(list.getStatus());
            sDate.setText(Constants.Date(list.getCreatedDate()));
        }
    }
}
