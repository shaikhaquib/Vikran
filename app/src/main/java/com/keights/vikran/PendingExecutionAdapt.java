package com.keights.vikran;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keights.vikran.Activity.NewSurveyActivity;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.ResponseModel.BillingResponse;
import com.keights.vikran.ResponseModel.PendingExecutionItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class PendingExecutionAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Activity activity;
    List<PendingExecutionItem> executionItems;
    int count = 0;

    public PendingExecutionAdapt(Activity activity, List<PendingExecutionItem> executionItems){
        this.executionItems=executionItems;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pendingexecutionitem, parent, false);
            vh = new PendingExecutionAdapt.ViewHolder(v);
        vh.setIsRecyclable(false);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PendingExecutionItem list = executionItems.get(position);
            PendingExecutionAdapt.ViewHolder viewHolder = (PendingExecutionAdapt.ViewHolder)holder;
            viewHolder.bind(list,position);

    }

    @Override
    public int getItemCount() {
        return executionItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCount;
        TextView sName;
        TextView sConsumerNo;
        TextView sDivision;
        TextView sDate;
        TextView sAssignedTo;
        Button btnApprove,disApprove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCount = itemView.findViewById(R.id.txtCount);
            sName = itemView.findViewById(R.id.sName);
            sConsumerNo = itemView.findViewById(R.id.sConsumerNo);
            sDivision = itemView.findViewById(R.id.sDivision);
            sDate = itemView.findViewById(R.id.sDate);
            sAssignedTo = itemView.findViewById(R.id.sAssignedTo);
            btnApprove = itemView.findViewById(R.id.approved);
            disApprove = itemView.findViewById(R.id.not_approved);

        }

        public void bind(final PendingExecutionItem list, final int position) {
            sName.setText(list.getConsumerName());
            sConsumerNo.setText(list.getConsumerNo());
            sDivision.setText(list.getDivision());
            count = position+1;
            txtCount.setText(String.valueOf(count));

            sConsumerNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, NewSurveyActivity.class).putExtra("ConsumerNo",list.getConsumerNo()));
                }
            });

            btnApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateExecution(btnApprove.getTag().toString(),list.getConsumerNo(),position);
                }
            });
            disApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateExecution(disApprove.getTag().toString(),list.getConsumerNo(),position);
                }
            });
        }
    }

    private void updateExecution(String tag, String consumerNo, final int position) {
        final Progress progress = new Progress(activity);
        progress.show();
        Call<BillingResponse> responseCall = RetrofitClient.getInstance().getApi().approved_execution(USER.getUserId(),USER.getLoginType(),consumerNo,tag);
        responseCall.enqueue(new Callback<BillingResponse>() {
            @Override
            public void onResponse(Call<BillingResponse> call, Response<BillingResponse> response) {
                progress.dismiss();
                if (response.isSuccessful())
                    if (response.body().isLoggedIn())
                    {
                        Constants.Alert(activity,response.body().getMsg());
                        executionItems.remove(position);
                        notifyDataSetChanged();
                    }else {
                        Constants.Alert(activity,response.body().getMsg());
                    }

            }

            @Override
            public void onFailure(Call<BillingResponse> call, Throwable t) {
                progress.dismiss();
            }
        });

    }
}
