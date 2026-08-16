package com.super_x.dao.driverdao;


import java.util.ArrayList;
import java.util.List;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.super_x.config.FirebaseConfig;
import com.super_x.model.drivermodel.Trip;

public class TripDAO {

    private final Firestore db =
            FirebaseConfig.getFireStore();

    public void saveTrip(Trip trip) throws Exception {

        db.collection("trips")
                .document(trip.getTripId())
                .set(trip)
                .get();

        System.out.println(
                "Trip created successfully: "
                        + trip.getTripId()
        );
    }
   public Trip getActiveTripByDriverId(String driverId) throws Exception {

    List<Trip> trips = new ArrayList<>();

    for (QueryDocumentSnapshot document :
            db.collection("trips")
                    .whereEqualTo("driverId", driverId)
                    .whereEqualTo("status", "ACTIVE")
                    .get()
                    .get()
                    .getDocuments()) {

        trips.add(
                document.toObject(Trip.class)
        );
    }

    if (trips.isEmpty()) {
        return null;
    }

    // Latest Trip ID contains the latest timestamp
    Trip latestTrip = trips.get(0);

    for (Trip trip : trips) {

        if (trip.getTripId() != null
                && latestTrip.getTripId() != null
                && trip.getTripId()
                        .compareTo(latestTrip.getTripId()) > 0) {

            latestTrip = trip;
        }
    }

    return latestTrip;
}
}