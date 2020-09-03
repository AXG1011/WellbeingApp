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
 * The 'VolunteeringItemAdapter' class bridges the gap between the adapter view and and the underlying data being
 * fetched from the Google Firestore database. The adapter provides access to the data items and is also
 * responsible for making a 'View' for each item in 'volunteeringJobs' collection stored in the database.
 */

public class VolunteeringItemAdapter extends FirestoreRecyclerAdapter<VolunteeringItem, VolunteeringItemAdapter.VolunteeringItemHolder> {

    public VolunteeringItemAdapter(@NonNull FirestoreRecyclerOptions<VolunteeringItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VolunteeringItemHolder volunteeringItemHolder, int i, @NonNull VolunteeringItem volunteeringItem) {
        // Sets the fields to be included in each item within the recycler view from the VolunteeringItem class:
        volunteeringItemHolder.volunteeringOrgName.setText(volunteeringItem.getVolunteeringOrgName());
        volunteeringItemHolder.date.setText(volunteeringItem.getDate());
        volunteeringItemHolder.volunteeringDescription.setText(volunteeringItem.getVolunteeringDescription());
        volunteeringItemHolder.uid.setText(volunteeringItem.getUid());
    }

    @NonNull
    @Override
    public VolunteeringItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Sets where each field should appear in an item by linking it to the volunteering_item.xml file:
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.volunteering_item,
                parent, false);
        return new VolunteeringItemHolder(v);

    }

    public void deleteItem(int position) { // Method to delete a goal from the recycler view
        getSnapshots().getSnapshot(position).getReference().delete();

    }

    // Links data retrieved from database to the 'view' elements in the recycler view:
    class VolunteeringItemHolder extends RecyclerView.ViewHolder {

        TextView volunteeringOrgName;
        TextView date;
        TextView volunteeringDescription;
        TextView uid;

        public VolunteeringItemHolder(@NonNull View itemView) {
            super(itemView);
            // Linking each field to where it should be displayed in the volunteering_item.xml file:
            date = itemView.findViewById(R.id.volunteering_date);
            uid = itemView.findViewById(R.id.volunteer_uid);
            volunteeringDescription = itemView.findViewById(R.id.volunteering_description);
            volunteeringOrgName = itemView.findViewById(R.id.volunteer_org_name);
        }
    }
}