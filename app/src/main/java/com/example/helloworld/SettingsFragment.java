package com.example.helloworld;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.slider.RangeSlider;

import java.util.Calendar;

public class SettingsFragment extends Fragment {
    private String updatedTime;
    private EditText maxDistance;
    private String genderPreference;
    private String accountPrivacy;
    private int minAge;
    private int maxAge;
    private Boolean haveSetMin = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        Button timePickerButton = view.findViewById(R.id.select_matches_time_button);
        TextView selectedTime = view.findViewById(R.id.selected_time);
        timePickerButton.setOnClickListener((v) -> {
            Calendar currentTime = Calendar.getInstance();
            int hour = currentTime.get(Calendar.HOUR_OF_DAY);
            int minute = currentTime.get(Calendar.MINUTE);
            TimePickerDialog timePicker;
            timePicker = new TimePickerDialog(v.getContext(), (timePicker1, selectedHour, selectedMinute) -> {
                String realMinute = "" + selectedMinute;
                if (selectedMinute < 10) {
                    realMinute = "0" + selectedMinute;
                }
                selectedTime.setText(String.format(v.getContext().getString(R.string.selected_time_message),
                        selectedHour, realMinute));
                updatedTime = "" + selectedHour + ":" + realMinute;
            }, hour, minute, false);
            timePicker.setTitle(getString(R.string.select_reminder_time_title));
            timePicker.show();
        });

        maxDistance = view.findViewById(R.id.edit_text_distance_in_miles);

        RadioButton heHim = view.findViewById(R.id.radio_he_him);
        RadioButton sheHer = view.findViewById(R.id.radio_she_her);
        RadioButton theyThem = view.findViewById(R.id.radio_they_them);
        RadioButton noAnswer = view.findViewById(R.id.radio_no_answer);
        heHim.setOnClickListener(this::onGenderRadioButtonClicked);
        sheHer.setOnClickListener(this::onGenderRadioButtonClicked);
        theyThem.setOnClickListener(this::onGenderRadioButtonClicked);
        noAnswer.setOnClickListener(this::onGenderRadioButtonClicked);

        RadioButton privateButton = view.findViewById(R.id.radio_privacy);
        RadioButton publicButton = view.findViewById(R.id.radio_public);
        privateButton.setOnClickListener(this::onAccountPrivacyRadioButtonClicked);
        publicButton.setOnClickListener(this::onAccountPrivacyRadioButtonClicked);

        RangeSlider rangeSlider = view.findViewById(R.id.age_range_slider);
        rangeSlider.addOnChangeListener((slider, value, fromUser) -> {
            if (!haveSetMin) {
                minAge = (int) value;
                haveSetMin = true;
            } else {
                maxAge = (int) value;
                haveSetMin = false;
            }
        });

        Button saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener((v) -> {
            if (genderPreference.equals("") || updatedTime.equals("") || maxAge == 0 || minAge == 0
                    || maxDistance.getText().toString().equals("") || accountPrivacy.equals("")) {
                Toast.makeText(v.getContext(),
                        v.getContext().getString(R.string.invalid_settings), Toast.LENGTH_LONG).show();
                return;
            }
            Settings settings = new Settings();
            settings.setGenderPreference(genderPreference);
            settings.setMatchReminderTime(updatedTime);
            settings.setMaxAge(maxAge);
            settings.setMinAge(minAge);
            settings.setMaxDistance(maxDistance.getText().toString());
            settings.setAccountPrivacy(accountPrivacy);
            settingsViewModel.insertSettings(v.getContext(), settings);
        });

        // Create the observer which updates the UI.
        final Observer<Settings> getSettingsObserver = newSettings -> {
            if (newSettings == null) {
                return;
            }

            updatedTime = newSettings.getMatchReminderTime();
            int selectedHour = Integer.parseInt(updatedTime.substring(0, updatedTime.indexOf(':')));
            String realMinute = updatedTime.substring(updatedTime.indexOf(':') + 1);
            selectedTime.setText(String.format(requireContext().getString(R.string.selected_time_message),
                    selectedHour, realMinute));

            maxDistance.setText(newSettings.getMaxDistance());

            genderPreference = newSettings.getGenderPreference();
            if (genderPreference.equals(getString(R.string.he_him))) {
                heHim.toggle();
            } else if (genderPreference.equals(getString(R.string.she_her))) {
                sheHer.toggle();
            } else if (genderPreference.equals(getString(R.string.they_them))) {
                theyThem.toggle();
            } else {
                noAnswer.toggle();
            }

            accountPrivacy = newSettings.getAccountPrivacy();
            if (accountPrivacy.equals(getString(R.string.private_radio))) {
                privateButton.toggle();
            } else {
                publicButton.toggle();
            }

            minAge = newSettings.getMinAge();
            maxAge = newSettings.getMaxAge();
            rangeSlider.setValues((float) minAge, (float) maxAge);
        };

        settingsViewModel.getSetting(getContext()).observe(getViewLifecycleOwner(), getSettingsObserver);

        return view;
    }

    public void onGenderRadioButtonClicked(View v) {
        RadioButton button = (RadioButton) v;
        genderPreference = button.getText().toString();
    }

    public void onAccountPrivacyRadioButtonClicked(View v) {
        RadioButton button = (RadioButton) v;
        accountPrivacy = button.getText().toString();
    }
}