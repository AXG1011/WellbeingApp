package com.example.well_beingapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);
    private LoginActivity loginActivity = null;

    @Before
    public void setUp() throws Exception {
        loginActivity = loginActivityTestRule.getActivity();
    }

    @Test
    public void testUiLaunch() {
        View view = loginActivity.findViewById(R.id.imageView);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchTwo() {
        View view = loginActivity.findViewById(R.id.editTextTextEmailAddress);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchThree() {
        View view = loginActivity.findViewById(R.id.editTextTextPassword);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFour() {
        View view = loginActivity.findViewById(R.id.button);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFive() {
        View view = loginActivity.findViewById(R.id.textView);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }

}