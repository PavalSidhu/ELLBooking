package com.example.ellbooking;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CalenderTest {
    @Rule
    public ActivityTestRule<LoginActivity> mBookingActivityActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void typeUserData() throws Exception {

        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@hotmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"));
        Thread.sleep(1500);
        onView(withId(R.id.Login)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.btnGoCalender)).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.hiddenDate)).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.booked)));

        Thread.sleep(1500);

        onView(withId(R.id.saveBookingData)).perform(click());
    }


}
