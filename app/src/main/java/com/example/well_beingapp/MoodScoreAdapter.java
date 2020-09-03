package com.example.well_beingapp;

import android.graphics.Color;
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
 * The 'MoodScoreAdapter' class bridges the gap between the adapter view and and the underlying data being
 * fetched from the Google Firestore database. The adapter provides access to the data items and is also
 * responsible for making a 'View' for each item in 'moodScores' collection stored in the database. This
 * class also includes the logic for setting the text colour of the mood-scores depending on their value.
 */

public class MoodScoreAdapter extends FirestoreRecyclerAdapter<ScoreLog, MoodScoreAdapter.moodScoreHolder> {

    public MoodScoreAdapter(@NonNull FirestoreRecyclerOptions<ScoreLog> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull moodScoreHolder moodScoreHolder, int i, @NonNull ScoreLog scoreLog) {
        // Sets the fields to be included in each item within the recycler view from the ScoreLog class:
        moodScoreHolder.date.setText(scoreLog.getDate());
        moodScoreHolder.moodScore.setText(String.valueOf(scoreLog.getMoodScore()));
        moodScoreHolder.uid.setText(scoreLog.getUid());
        moodScoreHolder.journalEntry.setText(scoreLog.getJournalEntry());

        int x = Integer.parseInt((String)moodScoreHolder.moodScore.getText()); // Initialising 'x' as the score fetched from the database

        if (x <= 33) { // If the score is less than or equal to 33, set the text colour to red
            moodScoreHolder.moodScore.setTextColor(Color.parseColor("#E41C0B"));
        }
        if (x >= 34) { // If the score is greater than or equal to 34, set the text colour to orange
            moodScoreHolder.moodScore.setTextColor(Color.parseColor("#E1A400"));
        }
        if (x >= 66) { // If the score is greater than or equal to 66, set the text colour to green
            moodScoreHolder.moodScore.setTextColor(Color.parseColor("#20C203"));
        }

    }

    @NonNull
    @Override
    public moodScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Sets where each field should appear in an item by linking it to the mood_score_item.xml file:
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_score_item,
                parent, false);
        return new moodScoreHolder (v);
    }

    // Links data retrieved from database to the 'view' elements in the recycler view:
    class moodScoreHolder extends RecyclerView.ViewHolder{

        TextView date;
        TextView moodScore;
        TextView uid;
        TextView journalEntry;

        public moodScoreHolder(@NonNull View itemView) {
            super(itemView);
            // Linking each field to where it should be displayed in the mood_score_item.xml file
            date = itemView.findViewById(R.id.mood_date);
            moodScore = itemView.findViewById(R.id.mood_score);
            uid = itemView.findViewById(R.id.score_uid);
            journalEntry = itemView.findViewById(R.id.journal_entry);
        }
    }

}