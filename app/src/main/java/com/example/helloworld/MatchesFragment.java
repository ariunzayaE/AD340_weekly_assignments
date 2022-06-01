package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {

    private MatchesViewModel vm;
    private LocationManager locationManager;
    private boolean isGettingLocationUpdates = false;
    private final List<Matches> matchesList = new ArrayList<>();
    private MatchesRecyclerViewAdapter adapter;
    private SettingsViewModel settingsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        locationManager = (LocationManager) view.getContext().getSystemService(Context.LOCATION_SERVICE);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        vm = new MatchesViewModel();

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        adapter = new MatchesRecyclerViewAdapter(matchesList, (match) -> {
            match.setLiked(!match.isLiked());
            vm.updateMatch(match);
        });
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.small_grid_spacing);
        recyclerView.addItemDecoration(new MatchesItemDecoration(largePadding, smallPadding));

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        vm.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        toggleLocationUpdates();
    }

    private boolean checkLocation() {
        if(!isLocationEnabled()) {
            showAlert();
        }
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setTitle(getString(R.string.enable_location))
                .setMessage(R.string.location_message)
                .setPositiveButton(R.string.location_settings, (paramDialogInterface, paramInt) -> {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                })
                .setNegativeButton(R.string.cancel, (paramDialogInterface, paramInt) -> {});
        dialog.show();
    }

    public void toggleLocationUpdates() {
        if(!checkLocation()) {
            return;
        }

        if(isGettingLocationUpdates) {
            locationManager.removeUpdates(locationListenerNetwork);
            isGettingLocationUpdates = false;
        }
        else {
            if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListenerNetwork);
                Toast.makeText(requireContext(), R.string.started_location_updates, Toast.LENGTH_LONG).show();
                isGettingLocationUpdates = true;
            } else {
                showAlert();
            }
        }
    }

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            Log.i(MatchesFragment.class.getSimpleName(), "Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude());
            final Observer<com.example.helloworld.Settings> getSettingsObserver = newSettings -> {
                float maxDistance = 0.0f;
                if (newSettings == null) {
                    Log.i(MatchesFragment.class.getSimpleName(), "Settings is null");
                    maxDistance = 10.0f;
                } else {
                    try {
                        Log.i(MatchesFragment.class.getSimpleName(), "Settings is : " + newSettings.getMaxDistance());
                        maxDistance = Float.parseFloat(newSettings.getMaxDistance());
                    } catch (NumberFormatException e) {
                        maxDistance = 10.0f;
                    }
                }

                vm.getMatches(location, maxDistance, matches -> {
                    Log.i(MatchesFragment.class.getSimpleName(), "Matches list is : " + matches.size());
                    matchesList.clear();
                    matchesList.addAll(matches);
                    adapter.notifyDataSetChanged();
                });
            };

            settingsViewModel.getSetting(getContext()).observe(getViewLifecycleOwner(), getSettingsObserver);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };
}