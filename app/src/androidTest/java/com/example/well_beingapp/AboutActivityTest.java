package com.example.well_beingapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AboutActivityTest {

    @Rule
    public ActivityTestRule<AboutActivity> aboutActivityTestRule = new ActivityTestRule<AboutActivity>(AboutActivity.class);
    private AboutActivity aboutActivity = null;

    @Before
    public void setUp() throws Exception {
        aboutActivity = aboutActivityTestRule.getActivity();
    }

    @Test
    public void testUiLaunch() {
        View view = aboutActivity.findViewById(R.id.imageView10);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchTwo() {
        View view = aboutActivity.findViewById(R.id.textView12);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchThree() {
        View view = aboutActivity.findViewById(R.id.textView13);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFour() {
        View view = aboutActivity.findViewById(R.id.learn_more);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        aboutActivity = null;
    }
}