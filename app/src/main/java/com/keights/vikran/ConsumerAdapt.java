package com.keights.vikran;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ConsumerAdapt extends RecyclerView.Adapter<ConsumerAdapt.ViewHolder> {
    Activity activity;


    public ConsumerAdapt(Activity activity){
        this.activity = activity;
    }

    @NonNull
    @Override
    public ConsumerAdapt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConsumerAdapt.ViewHolder(LayoutInflater.from(activity).inflate(R.layout.consumerlist_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ConsumerAdapt.ViewHolder holder, int position) {
       /* AskedQuestionListItem questionsItem = questions.get(position);
        holder.bind(questionsItem);*/
        holder.txtCount.setText(String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtCount = itemView.findViewById(R.id.txtCount);

        }

    }
}
