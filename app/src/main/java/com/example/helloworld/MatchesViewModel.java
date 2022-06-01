package com.example.helloworld;

import android.location.Location;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MatchesViewModel {
    private MatchesDataModel matchesDataModel;
    public MatchesViewModel() {
        matchesDataModel = new MatchesDataModel();
    }

    public void getMatches(Location location, float maxDistance, Consumer<ArrayList<Matches>> responseCallback) {
        matchesDataModel.getMatches(
                (QuerySnapshot querySnapshot) -> {
                    if (querySnapshot != null) {
                        ArrayList<Matches> matches = new ArrayList<>();
                        for (DocumentSnapshot todoSnapshot : querySnapshot.getDocuments()) {
                            Matches item = todoSnapshot.toObject(Matches.class);
                            assert item != null;
                            item.setUid(todoSnapshot.getId());
                            matches.add(item);
                        }

                        ArrayList<Matches> filteredMatches = new ArrayList<>();
                        for (Matches match : matches) {
                            Location targetLocation = new Location("");
                            targetLocation.setLatitude(Double.parseDouble(match.getLat()));
                            targetLocation.setLongitude(Double.parseDouble(match.getLongitude()));

                            float distance = location.distanceTo(targetLocation);
                            float miles = distance / 1609.344f;

                            if (miles <= maxDistance) {
                                filteredMatches.add(match);
                            }
                        }

                        responseCallback.accept(filteredMatches);
                    }
                },
                (databaseError -> System.out.println("Error reading Todo Items: " + databaseError))
        );
    }
    public void updateMatch(Matches match) {
        matchesDataModel.updateMatchById(match);
    }
    public void clear() {
        matchesDataModel.clear();
    }
}