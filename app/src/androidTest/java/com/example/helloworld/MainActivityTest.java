package com.example.helloworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import static java.util.regex.Pattern.matches;

import android.view.View;
import android.widget.DatePicker;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    /* Week 1 assignment test
    @Test
    public void displayHelloWorld(){
        onView(withId(R.id.hello_world)).check(matches(withText("Hello World!")));
    }
     */

    @Test
    public void checkingSubmitButton(){
        onView(withId(R.id.nameField)).perform(replaceText("Zaya Erdenebileg"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("zayaerde"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000,1,2));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
    }

    @Test
    public void checkingBackButtonAndReset() {
        onView(withId(R.id.nameField)).perform(replaceText("zaya"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("zayaerdenebileg"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 4, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.nameField)).check(matches(withText("")));
        onView(withId(R.id.userName)).check(matches(withText("")));
        onView(withId(R.id.emailAddress)).check(matches(withText("")));

    }

    @Test
    public void checkingValidEmail() {
        onView(withId(R.id.nameField)).perform(replaceText("zaya erdenebileg"));
        onView(withId(R.id.userName)).perform(replaceText("zayaUserName"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("ariunzaya.eb@gmail.com")).check(doesNotExist());
    }
}