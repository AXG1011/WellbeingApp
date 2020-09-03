package com.example.well_beingapp;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'GoalLog' class contains the fields, constructor and getters for Goal objects. Each goal
 * object includes a title, description, sub-goals, an end date and the current user's id. This class
 * is used in the 'GoalAdapter' and 'FutureActivity' classes.
 */

public class GoalLog {

    // The goal object fields, all of type string:
    private String goalTitle;
    private String goalDescription;
    private String subGoals;
    private String endDate;
    private String uid;

    public GoalLog(){
        // Empty constructor required for Google Firebase to create a goal object
    }

    // Constructor for goal object:
    public GoalLog(String goalTitle, String goalDescription, String subGoals, String endDate, String uid){
        this.goalTitle = goalTitle;
        this.goalDescription = goalDescription;
        this.subGoals = subGoals;
        this.endDate = endDate;
        this.uid = uid;
    }

    public String getGoalTitle() { // Getter for goal title
        return goalTitle;
    }

    public String getGoalDescription() { // Getter for goal description
        return goalDescription;
    }

    public String getSubGoals() { // Getter for sub-goals
        return subGoals;
    }

    public String getEndDate() { // Getter for end date
        return endDate;
    }

    public String getUid() { // Getter for user id
        return uid;
    }

}