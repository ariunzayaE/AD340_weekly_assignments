package com.example.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        List<Matches> matchesList = new ArrayList<>();
        matchesList.add(new Matches("Melvin", "Such a sweet boy", true, "https://i.imgur.com/zYyha9n.jpg"));
        matchesList.add(new Matches("August", "Super cowboy men", false, "https://i.imgur.com/WhT36QV.jpg"));
        matchesList.add(new Matches("Jackson", "Best protective guy", false, "https://i.imgur.com/cUln8wC.jpg"));

        // Set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        MatchesRecyclerViewAdapter adapter = new MatchesRecyclerViewAdapter(matchesList);
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.small_grid_spacing);
        recyclerView.addItemDecoration(new MatchesItemDecoration(largePadding, smallPadding));
        return view;
    }
}