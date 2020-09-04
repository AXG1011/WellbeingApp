package com.example.well_beingapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldLandingActivityTest {

    @Rule
    public ActivityTestRule<WorldLandingActivity> worldLandingActivityTestRule = new ActivityTestRule<WorldLandingActivity>(WorldLandingActivity.class);
    private WorldLandingActivity worldLandingActivity = null;

    @Before
    public void setUp() throws Exception {
        worldLandingActivity = worldLandingActivityTestRule.getActivity();
    }

    @Test
    public void testUiLaunch() {
        View view = worldLandingActivity.findViewById(R.id.imageView8);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchTwo() {
        View view = worldLandingActivity.findViewById(R.id.button_find_opps);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchThree() {
        View view = worldLandingActivity.findViewById(R.id.button_volunteering_log);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFour() {
        View view = worldLandingActivity.findViewById(R.id.mood_log_text);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFive() {
        View view = worldLandingActivity.findViewById(R.id.quiz_text);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        worldLandingActivity = null;
    }

}