package com.example.well_beingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class GoalAdapter extends FirestoreRecyclerAdapter<GoalLog, GoalAdapter.GoalHolder> {

    public GoalAdapter(@NonNull FirestoreRecyclerOptions<GoalLog> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull GoalHolder goalHolder, int i, @NonNull GoalLog goalLog) {
        goalHolder.goalTitle.setText(goalLog.getGoalTitle());
        goalHolder.goalDescription.setText(goalLog.getGoalDescription());
        goalHolder.endDate.setText(goalLog.getEndDate());
        goalHolder.uid.setText(goalLog.getUid());

    }

    @NonNull
    @Override
    public GoalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_item,
                parent, false);
        return new GoalHolder(v);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();

    }


    class GoalHolder extends RecyclerView.ViewHolder{

        TextView goalTitle;
        TextView goalDescription;
        TextView endDate;
        TextView uid;

        public GoalHolder(@NonNull View itemView) {
            super(itemView);
            goalTitle = itemView.findViewById(R.id.goal_title);
            goalDescription = itemView.findViewById(R.id.goal_description);
            endDate = itemView.findViewById(R.id.goal_end_date);
            uid = itemView.findViewById(R.id.goal_uid);

        }
    }

}
