package com.example.ellbooking;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class CancelBookingTest {
    @Rule
    public ActivityTestRule<LoginActivity> mLoginActivityActivityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void typeUserData() throws Exception {

        onView(withId(R.id.editTextTextEmailAddress)).perform(typeText("test@hotmail.com"));
        onView(withId(R.id.editTextTextPassword)).perform(typeText("password"));
        Thread.sleep(1500);
        onView(withId(R.id.Login)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.btnGoAppointment)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.cancelBooking)).perform(click());
        Thread.sleep(2000);
        onView(withText("No Booking exists")).perform(pressBack());
        Thread.sleep(1000);
//        onView(withId(R.id.btnGoAppointment)).perform(click());
//        Thread.sleep(1000);
    }
}
