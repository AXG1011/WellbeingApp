package com.example.well_beingapp;

public class GoalLog {
    private String goalTitle;
    private String goalDescription;
    private String endDate;
    private String uid;

    public GoalLog(){
        // Empty constructor needed for firebase to create goal object
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getUid() {
        return uid;
    }

    public GoalLog(String goalTitle, String goalDescription, String endDate, String uid){
        this.goalTitle = goalTitle;
        this.goalDescription = goalDescription;
        this.endDate = endDate;
        this.uid = uid;
    }

}