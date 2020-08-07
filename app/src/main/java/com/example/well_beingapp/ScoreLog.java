package com.example.well_beingapp;

public class ScoreLog {

    private String date;
    private int moodScore;
    private String uid;

    public ScoreLog(){
        // Empty constructor needed for firebase to create score object
    }

    public ScoreLog(String date, int moodScore, String uid){
        this.date = date;
        this.moodScore = moodScore;
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public int getMoodScore() {
        return moodScore;
    }

    public String getUid() {
        return uid;
    }
}
