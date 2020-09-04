package com.example.well_beingapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class SettingsActivityTest {

    @Rule
    public ActivityTestRule<SettingsActivity> settingsActivityTestRule = new ActivityTestRule<SettingsActivity>(SettingsActivity.class);
    private SettingsActivity settingsActivity = null;

    @Before
    public void setUp() throws Exception {
        settingsActivity = settingsActivityTestRule.getActivity();
    }

    @Test
    public void testUiLaunch() {
        View view = settingsActivity.findViewById(R.id.logout);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchTwo() {
        View view = settingsActivity.findViewById(R.id.about_button);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchThree() {
        View view = settingsActivity.findViewById(R.id.notifications_button);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFour() {
        View view = settingsActivity.findViewById(R.id.imageView9);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        settingsActivity = null;
    }
}