package com.example.helloworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void checkingSubmitButton(){
        onView(withId(R.id.nameField)).perform(replaceText("Zaya Erdenebileg"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("zayaerde"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000,1,2));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        //onView(withId(R.id.welcomeText)).check(matches(withText("Thank you for signing up zayaerde!")));
    }

    @Test
    public void checkingSubmissionPass(){
        onView(withId(R.id.nameField)).perform(replaceText("Zaya Erdenebileg"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("zayaerde"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2000,1,2));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).check(matches(withText("Zaya Erdenebileg")));
    }

    @Test
    public void checkingBackButtonAndReset() {
        onView(withId(R.id.nameField)).perform(replaceText("zaya"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("zayaerdenebileg"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2000, 4, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.backButton)).perform(click());
        onView(withId(R.id.nameField)).check(matches(withText("")));
        onView(withId(R.id.userName)).check(matches(withText("")));
        onView(withId(R.id.emailAddress)).check(matches(withText("")));
        onView(withId(R.id.occupation)).check(matches(withText("")));
        onView(withId(R.id.description)).check(matches(withText("")));
    }

    @Test
    public void checkingMustHaveValidEmail() {
        onView(withId(R.id.nameField)).perform(replaceText("zaya erdenebileg"));
        onView(withId(R.id.userName)).perform(replaceText("zayaUserName"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(1980, 12, 7));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("ariunzaya.eb@gmail.com")).check(doesNotExist());
    }

    @Test
    public void checkingMustHaveName() {
        onView(withId(R.id.userName)).perform(replaceText("zayaUserName"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("Zaya Erdenebileg")).check(doesNotExist());
    }

    @Test
    public void checkingMustHaveUserName() {
        onView(withId(R.id.nameField)).perform(replaceText("zaya erdenebileg"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("zayaUserName")).check(doesNotExist());
    }

    @Test
    public void checkingMustHaveDescription() {
        onView(withId(R.id.nameField)).perform(replaceText("Melvin"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("zayaUserName"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("I am a student")).check(doesNotExist());
    }

    @Test
    public void checkingMustHaveOccupation() {
        onView(withId(R.id.nameField)).perform(replaceText("Zaya Erdenebileg"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("zayaUserName"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2001, 4, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("Student")).check(doesNotExist());
    }

    @Test
    public void checkingOldEnoughTo18() {
        onView(withId(R.id.nameField)).perform(replaceText("Melvin"));
        onView(withId(R.id.emailAddress)).perform(replaceText("ariunzaya.eb@gmail.com"));
        onView(withId(R.id.userName)).perform(replaceText("zayaUserName"));
        onView(withId(R.id.description)).perform(replaceText("I am a student"));
        onView(withId(R.id.occupation)).perform(replaceText("Student"));
        onView(withId(R.id.dobButton)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2022, 4, 10));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withText("4/22/2020")).check(doesNotExist());
    }


}