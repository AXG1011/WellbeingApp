package com.example.well_beingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'GoalAdapter' class bridges the gap between the adapter view and and the underlying data being
 * fetched from the Google Firestore database. The adapter provides access to the data items and is also
 * responsible for making a 'View' for each item in 'goals' collection stored in the database.
 */

public class GoalAdapter extends FirestoreRecyclerAdapter<GoalLog, GoalAdapter.GoalHolder> {

    public GoalAdapter(@NonNull FirestoreRecyclerOptions<GoalLog> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull GoalHolder goalHolder, int i, @NonNull GoalLog goalLog) {
        // Sets the fields to be included in each item within the recycler view from the GoalLog class:
        goalHolder.goalTitle.setText(goalLog.getGoalTitle());
        goalHolder.goalDescription.setText(goalLog.getGoalDescription());
        goalHolder.subGoals.setText(goalLog.getSubGoals());
        goalHolder.endDate.setText(goalLog.getEndDate());
        goalHolder.uid.setText(goalLog.getUid());
    }

    @NonNull
    @Override
    public GoalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Sets where each field should appear in an item by linking it to the goal_item.xml file:
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_item, parent, false);
        return new GoalHolder(v);
    }

    public void deleteItem(int position) { // Method to delete a goal from the recycler view
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    // Links data retrieved from database to the 'view' elements in the recycler view:
    public class GoalHolder extends RecyclerView.ViewHolder {

        TextView goalTitle;
        TextView goalDescription;
        TextView subGoals;
        TextView endDate;
        TextView uid;

        public GoalHolder(@NonNull View itemView) {
            super(itemView);
            // Linking each field to where it should be displayed in the goal_item.xml file:
            goalTitle = itemView.findViewById(R.id.goal_title);
            goalDescription = itemView.findViewById(R.id.goal_description);
            subGoals = itemView.findViewById(R.id.sub_goals);
            endDate = itemView.findViewById(R.id.goal_end_date);
            uid = itemView.findViewById(R.id.goal_uid);
        }
    }

}