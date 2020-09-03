package com.example.well_beingapp;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'VolunteeringItem' class contains the fields, constructor and getters for Volunteering Item objects.
 * Each volunteering item object includes an organisation's name, description of volunteering activities,
 * the date of completion and the current user's id. This class is used in the 'VolunteeringItemAdapter'
 * and 'VolunteeringActivity' classes.
 */

public class VolunteeringItem {

    // The volunteering item object fields:
    private String volunteeringOrgName;
    private String date;
    private String volunteeringDescription;
    private String uid;

    public VolunteeringItem(){
        // Empty constructor needed for firebase to create volunteering object
    }

    // Constructor for volunteering item object
    public VolunteeringItem(String volunteeringOrgName, String date, String volunteeringDescription, String uid) {
        this.volunteeringOrgName = volunteeringOrgName;
        this.date = date;
        this.volunteeringDescription = volunteeringDescription;
        this.uid = uid;
    }

    public String getVolunteeringOrgName() { // Getter for the volunteering organisation's name
        return volunteeringOrgName;
    }

    public String getDate() { // Getter for the completion date
        return date;
    }

    public String getVolunteeringDescription() { // Getter for the volunteering description
        return volunteeringDescription;
    }

    public String getUid() { // Getter for mood-entry's user id
        return uid;
    }

}
