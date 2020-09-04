package com.example.well_beingapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelfLandingActivityTest {

    @Rule
    public ActivityTestRule<SelfLandingActivity> selfLandingActivityActivityTestRule = new ActivityTestRule<SelfLandingActivity>(SelfLandingActivity.class);
    private SelfLandingActivity selfLandingActivity = null;

    @Before
    public void setUp() throws Exception {
        selfLandingActivity = selfLandingActivityActivityTestRule.getActivity();
    }

    @Test
    public void testUiLaunch() {
        View view = selfLandingActivity.findViewById(R.id.imageView8);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchTwo() {
        View view = selfLandingActivity.findViewById(R.id.button_take_quiz);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchThree() {
        View view = selfLandingActivity.findViewById(R.id.button_mood_log);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFour() {
        View view = selfLandingActivity.findViewById(R.id.mood_log_text);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFive() {
        View view = selfLandingActivity.findViewById(R.id.quiz_text);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        selfLandingActivity = null;
    }
}