package com.example.helloworld;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    ProfileData profileData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView age = view.findViewById(R.id.selectedDateOfBirth);
        TextView name = view.findViewById(R.id.nameField);
        TextView occupation = view.findViewById(R.id.occupation);
        TextView description = view.findViewById(R.id.description);

        if (this.profileData != null) {
            age.setText("" + this.profileData.getAge());
            name.setText(this.profileData.getName());
            occupation.setText(this.profileData.getOccupation());
            description.setText(this.profileData.getDescription());
        }
        return view;
    }

    public void setProfileData(ProfileData profileData) {
        this.profileData = profileData;
    }
}