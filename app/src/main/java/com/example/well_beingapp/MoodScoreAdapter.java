package com.example.well_beingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MoodScoreAdapter extends FirestoreRecyclerAdapter<ScoreLog, MoodScoreAdapter.moodScoreHolder> {

    public MoodScoreAdapter(@NonNull FirestoreRecyclerOptions<ScoreLog> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull moodScoreHolder moodScoreHolder, int i, @NonNull ScoreLog scoreLog) {
        moodScoreHolder.date.setText(scoreLog.getDate());
        moodScoreHolder.moodScore.setText(String.valueOf(scoreLog.getMoodScore()));
        moodScoreHolder.uid.setText(scoreLog.getUid());
    }

    @NonNull
    @Override
    public moodScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mood_score_item,
                parent, false);
        return new moodScoreHolder (v);
    }

    class moodScoreHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView moodScore;
        TextView uid;

        public moodScoreHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.mood_date);
            moodScore = itemView.findViewById(R.id.mood_score);
            uid = itemView.findViewById(R.id.score_uid);

        }
    }

}