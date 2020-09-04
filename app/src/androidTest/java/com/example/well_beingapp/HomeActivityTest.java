package com.example.well_beingapp;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class HomeActivityTest {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityTestRule = new ActivityTestRule<HomeActivity>(HomeActivity.class);
    private HomeActivity homeActivity = null;

    @Before
    public void setUp() throws Exception {
        homeActivity = homeActivityTestRule.getActivity();
    }

    @Test
    public void testUiLaunch() {
        View view = homeActivity.findViewById(R.id.imageView3);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchTwo() {
        View view = homeActivity.findViewById(R.id.textView2);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchThree() {
        View view = homeActivity.findViewById(R.id.textView3);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFour() {
        View view = homeActivity.findViewById(R.id.imageView4);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchFive() {
        View view = homeActivity.findViewById(R.id.textView5);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchSix() {
        View view = homeActivity.findViewById(R.id.textView8);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchSeven() {
        View view = homeActivity.findViewById(R.id.imageView6);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchEight() {
        View view = homeActivity.findViewById(R.id.textView4);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchNine() {
        View view = homeActivity.findViewById(R.id.textView7);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchTen() {
        View view = homeActivity.findViewById(R.id.imageView5);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchEleven() {
        View view = homeActivity.findViewById(R.id.textView6);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchTwelve() {
        View view = homeActivity.findViewById(R.id.textView9);
        assertNotNull(view);
    }

    @Test
    public void testUiLaunchThirteen() {
        View view = homeActivity.findViewById(R.id.imageView2);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        homeActivity = null;
    }
}