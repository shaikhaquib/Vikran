package com.keights.vikran;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.keights.vikran.Activity.SurveyDetailActivity;
import com.keights.vikran.Extras.Constants;
import com.keights.vikran.Extras.Progress;
import com.keights.vikran.Network.RetrofitClient;
import com.keights.vikran.ResponseModel.AssignConsumerItem;
import com.keights.vikran.ResponseModel.SearchConsumerResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.keights.vikran.LoginActivity.USER;

public class ConsumerAdapt extends RecyclerView.Adapter<ConsumerAdapt.ViewHolder> implements Filterable {
    Activity activity;
    List<AssignConsumerItem> filterLists;
    List<AssignConsumerItem> tempList;

    public ConsumerAdapt(Activity activity, List<AssignConsumerItem> filterLists){
        this.activity = activity;
        this.filterLists = filterLists;
        this.tempList = filterLists;
    }

    @NonNull
    @Override
    public ConsumerAdapt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConsumerAdapt.ViewHolder(LayoutInflater.from(activity).inflate(R.layout.consumerlist_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ConsumerAdapt.ViewHolder holder, int position) {
        AssignConsumerItem consumerItem = filterLists.get(position);
        holder.bind(consumerItem);
        holder.txtCount.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return filterLists.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filterLists = tempList;
                } else {
                    List<AssignConsumerItem> filteredList = new ArrayList<>();
                    for (AssignConsumerItem row : tempList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getConsumerName().toLowerCase().contains(charString.toLowerCase()) || row.getConsumerNo().toLowerCase().contains(charString.toLowerCase()) || row.getTaluka().toLowerCase().contains(charString.toLowerCase()) || row.getSection().toLowerCase().contains(charString.toLowerCase()) || row.getVillage().toLowerCase().contains(charString.toLowerCase()) || row.getStatus().toLowerCase().contains(charString.toLowerCase())
                        ) {
                            filteredList.add(row);
                        }
                    }

                    filterLists = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterResults;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
              //  filterLists = (ArrayList<AssignConsumerItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCount,cStatus,cVillage,cSection,cDistrict,cTaluka,cNo,cName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCount   = itemView.findViewById(R.id.txtCount);
            cStatus   = itemView.findViewById(R.id.cStatus);
            cVillage  = itemView.findViewById(R.id.cVillage);
            cSection  = itemView.findViewById(R.id.cSection);
            cDistrict = itemView.findViewById(R.id.cDistrict);
            cTaluka   = itemView.findViewById(R.id.cTaluka);
            cNo       = itemView.findViewById(R.id.cNo);
            cName     = itemView.findViewById(R.id.cName);
        }

        public void bind(final AssignConsumerItem consumerItem) {
            cStatus  .setText(consumerItem.getStatus());
            cVillage .setText(consumerItem.getVillage());
            cSection .setText(consumerItem.getSection());
            cTaluka  .setText(consumerItem.getTaluka());
            cNo      .setText(consumerItem.getConsumerNo());
            cName    .setText(consumerItem.getConsumerName());

            cNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchConsumer(consumerItem.getConsumerNo());
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
                        if (response.body().getConsumerDetails().getSurveyDetails().size()  >0 ) {
                            Intent intent = new Intent(activity, SurveyDetailActivity.class);
                            intent.putExtra("Data", response.body().getConsumerDetails().getConsumerDetails().get(0));
                            intent.putExtra("surveyDetailsItem", response.body().getConsumerDetails().getSurveyDetails().get(0));
                            activity.startActivity(intent);
                        }else
                            Constants.Alert(activity,"No data available for the Consumer Number");
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
