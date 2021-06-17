package com.example.ellbooking;


import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class loginVerifyUser {
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
        onView(withId(R.id.btnGoUserInfo)).perform(click());
        Thread.sleep(3000);

        onView(withId(R.id.firstNameInput)).perform(click(), replaceText("First"), closeSoftKeyboard());;
        Thread.sleep(1000);
        onView(withId(R.id.SaveUserData)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.btnGoUserInfo)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.firstNameInput)).check(matches(withText("First")));
    }
}
