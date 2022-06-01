package com.example.helloworld;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
public class SettingsScreenTest {
    @Rule
    public ActivityScenarioRule<WelcomeScreen> welcomeScreenActivity =
            new ActivityScenarioRule<>(WelcomeScreen.class);

    @Test
    public void checkingSaveSettings() {
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.select_matches_time_button)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(6, 45));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).perform(replaceText("50"));
        onView(withId(R.id.radio_he_him)).perform(click());
        onView(withId(R.id.radio_privacy)).perform(click());
        onView(withId(R.id.age_range_slider)).perform(HelpersViewMatcher.setValue(20.0F, 70.0F));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.profile_menu_item)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).check(matches(withText("50")));
    }
    @Test
    public void checkingGenderPreferenceHeHim() {
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.select_matches_time_button)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(7, 13));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).perform(replaceText("500"));
        onView(withId(R.id.radio_he_him)).perform(click());
        onView(withId(R.id.radio_privacy)).perform(click());
        onView(withId(R.id.age_range_slider)).perform(HelpersViewMatcher.setValue(26.0F, 78.0F));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.profile_menu_item)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).check(matches(withText("500")));
    }

    @Test
    public void checkingGenderPreferenceSheHer() {
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.select_matches_time_button)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10, 19));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).perform(replaceText("15"));
        onView(withId(R.id.radio_she_her)).perform(click());
        onView(withId(R.id.radio_privacy)).perform(click());
        onView(withId(R.id.age_range_slider)).perform(HelpersViewMatcher.setValue(34.0F, 89.0F));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.profile_menu_item)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).check(matches(withText("15")));
    }

    @Test
    public void canSaveWithTheyThem() {
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.select_matches_time_button)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(10, 19));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).perform(replaceText("500"));
        onView(withId(R.id.radio_they_them)).perform(click());
        onView(withId(R.id.radio_public)).perform(click());
        onView(withId(R.id.age_range_slider)).perform(HelpersViewMatcher.setValue(34.0F, 89.0F));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.profile_menu_item)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).check(matches(withText("500")));
    }


    @Test
    public void checkingWithoutAnswer() {
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.select_matches_time_button)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(15, 0));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).perform(replaceText("10"));
        onView(withId(R.id.radio_no_answer)).perform(click());
        onView(withId(R.id.radio_privacy)).perform(click());
        onView(withId(R.id.age_range_slider)).perform(HelpersViewMatcher.setValue(35.0F, 55.0F));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.profile_menu_item)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).check(matches(withText("10")));
    }


    @Test
    public void checkingPublicSettings() {
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.select_matches_time_button)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(7, 12));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).perform(replaceText("45"));
        onView(withId(R.id.radio_she_her)).perform(click());
        onView(withId(R.id.radio_privacy)).perform(click());
        onView(withId(R.id.radio_public)).perform(click());
        onView(withId(R.id.age_range_slider)).perform(HelpersViewMatcher.setValue(22.0F, 34.0F));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.profile_menu_item)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        //onView(withId(R.id.edit_text_distance_in_miles)).check(matches(withText("45")));
        onView(withId(R.id.radio_public)).check(matches(withText("Public")));
    }

    @Test
    public void checkingPrivacySettings() {
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.select_matches_time_button)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName())))
                .perform(PickerActions.setTime(7, 12));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).perform(replaceText("45"));
        onView(withId(R.id.radio_she_her)).perform(click());
        onView(withId(R.id.radio_public)).perform(click());
        onView(withId(R.id.radio_privacy)).perform(click());
        onView(withId(R.id.age_range_slider)).perform(HelpersViewMatcher.setValue(22.0F, 34.0F));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.profile_menu_item)).perform(click());
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        //onView(withId(R.id.edit_text_distance_in_miles)).check(matches(withText("45")));
        onView(withId(R.id.radio_privacy)).check(matches(withText("Private")));
    }

    @Test
    public void checkingSaveWithMissingTime() {
        onView(withContentDescription("drawer_open")).perform(click());
        onView(withId(R.id.settings_menu_item)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).perform(replaceText(""));
        onView(withId(R.id.saveButton)).perform(click());
        onView(withId(R.id.edit_text_distance_in_miles)).check(matches(withText("")));
    }
}