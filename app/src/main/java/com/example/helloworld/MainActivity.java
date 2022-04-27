package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.SyncStateContract;

import android.util.Patterns;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener {

    private EditText nameField;
    private EditText emailAddressField;
    private EditText userNameField;
    private EditText occupation;
    private EditText description;
    private TextView dobTextView;
    private int dobYear = 0;
    private int dobMonth = 0;
    private int dobDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.nameField);
        emailAddressField = findViewById(R.id.emailAddress);
        userNameField = findViewById(R.id.userName);
        occupation = findViewById(R.id.occupation);
        description = findViewById(R.id.description);
        dobTextView = findViewById(R.id.selectedDateOfBirth);
    }

    public void onSubmit(View view){
        String name = nameField.getText().toString();
        String emailAddress = emailAddressField.getText().toString();
        String username = userNameField.getText().toString();

        if(name.equals("") || emailAddress.equals("") || occupation.equals("") || description.equals("") || username.equals("") || dobYear == 0 || dobMonth == 0 || dobDay == 0) {
            Toast.makeText(getApplicationContext(), getString(R.string.forgot_data_error), Toast.LENGTH_LONG).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            Toast.makeText(getApplicationContext(), getString(R.string.email_address_error), Toast.LENGTH_LONG).show();
            return;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(dobYear, dobMonth, dobDay);
        int years = Period.between(dateOfBirth, currentDate).getYears();

        if (years < 18) {
            Toast.makeText(getApplicationContext(), getString(R.string.eighteen_error), Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
        intent.putExtra(Constants.USERNAME_KEY, name);
        intent.putExtra(Constants.AGE_KEY, years);
        intent.putExtra(Constants.DESCRIPTION_KEY, description.getText().toString());
        intent.putExtra(Constants.OCCUPATION_KEY, occupation.getText().toString());
        startActivity(intent);

    }
    public void onDobClick(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.SELECTED_DOB, dobTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.SELECTED_DOB)) {
            dobTextView.setText(savedInstanceState.getString(Constants.SELECTED_DOB));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        nameField.setText("");
        userNameField.setText("");
        emailAddressField.setText("");
        occupation.setText("");
        description.setText("");
        dobTextView.setText("");
        dobYear = 0;
        dobDay = 0;
        dobMonth = 0;
    }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            month = month + 1;
            dobYear = year;
            dobMonth = month;
            dobDay = day;
            dobTextView.setText(month + getString(R.string.slash) + day + getString(R.string.slash) + year);
        }

        public static class DatePickerFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),(MainActivity) getActivity(), year, month, day);
        }
    }
}
