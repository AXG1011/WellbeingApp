package com.example.well_beingapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testUiLaunch() {
        View view = mainActivity.findViewById(R.id.imageView);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchTwo() {
        View view = mainActivity.findViewById(R.id.button);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchThree() {
        View view = mainActivity.findViewById(R.id.textView);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFour() {
        View view = mainActivity.findViewById(R.id.editTextTextEmailAddress);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFive() {
        View view = mainActivity.findViewById(R.id.editTextName);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchSix() {
        View view = mainActivity.findViewById(R.id.editTextTextPassword);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

}