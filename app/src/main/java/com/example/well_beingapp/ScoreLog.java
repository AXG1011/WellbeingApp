package com.example.well_beingapp;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'ScoreLog' class contains the fields, constructor and getters for Mood Score objects. Each score
 * object includes a date, mood score, a journal entry and the current user's id. This class is used in the
 * 'MoodScoreAdapter' and 'MoodLogActivity' classes.
 */

public class ScoreLog {

    // The mood score object fields:
    private String date;
    private int moodScore;
    private String uid;
    private String journalEntry;

    public ScoreLog(){
        // Empty constructor needed for firebase to create score object
    }

    // Constructor for mood score object
    public ScoreLog(String date, int moodScore, String uid, String journalEntry){
        this.date = date;
        this.moodScore = moodScore;
        this.uid = uid;
        this.journalEntry = journalEntry;
    }

    public String getDate() { // Getter for mood-entry's date
        return date;
    }

    public int getMoodScore() { // Getter for mood-entry's score
        return moodScore;
    }

    public String getUid() { // Getter for mood-entry's user id
        return uid;
    }

    public String getJournalEntry(){ // Getter for mood-entry's journal entry
        return journalEntry;
    }

}