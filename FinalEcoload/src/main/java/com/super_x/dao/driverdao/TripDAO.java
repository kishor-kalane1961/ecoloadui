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

    // =========================================================
    // SAVE NEW TRIP
    // =========================================================

    public void saveTrip(
            Trip trip
    ) throws Exception {

        db.collection("trips")
                .document(trip.getTripId())
                .set(trip)
                .get();

        System.out.println(
                "Trip created successfully: "
                        + trip.getTripId()
        );
    }

    // =========================================================
    // FIND ACTIVE TRIP FOR DRIVER
    // =========================================================

    public Trip getActiveTripByDriverId(
            String driverId
    ) throws Exception {

        List<Trip> trips =
                new ArrayList<>();

        for (QueryDocumentSnapshot document :
                db.collection("trips")
                        .whereEqualTo(
                                "driverId",
                                driverId
                        )
                        .whereEqualTo(
                                "status",
                                "ACTIVE"
                        )
                        .get()
                        .get()
                        .getDocuments()) {

            trips.add(
                    document.toObject(
                            Trip.class
                    )
            );
        }

        if (trips.isEmpty()) {

            return null;
        }

        Trip latestTrip =
                trips.get(0);

        for (Trip trip : trips) {

            if (trip.getTripId() != null
                    && latestTrip.getTripId() != null
                    && trip.getTripId()
                            .compareTo(
                                    latestTrip.getTripId()
                            ) > 0) {

                latestTrip = trip;
            }
        }

        return latestTrip;
    }

    // =========================================================
    // ADD LOAD TO EXISTING ACTIVE TRIP
    // =========================================================

    public void addLoadToExistingTrip(
            Trip trip,
            String loadId,
            double loadWeight,
            String pickupLocation
    ) throws Exception {

        List<String> loadIds =
                trip.getLoadIds();

        if (loadIds == null) {

            loadIds =
                    new ArrayList<>();
        }

        // Preserve old primary loadId
        if (trip.getLoadId() != null
                && !trip.getLoadId().trim().isEmpty()
                && !loadIds.contains(
                        trip.getLoadId()
                )) {

            loadIds.add(
                    trip.getLoadId()
            );
        }

        // Duplicate check
        if (loadIds.contains(loadId)) {

            System.out.println(
                    "Load already exists in trip: "
                            + loadId
            );

            return;
        }

        double availableCapacity =
                trip.getTotalCapacity()
                        - trip.getUsedCapacity();

        if (loadWeight > availableCapacity) {

            throw new Exception(
                    "Load cannot be accepted. "
                            + "Required: "
                            + loadWeight
                            + " Ton, Available: "
                            + availableCapacity
                            + " Ton"
            );
        }

        double newUsedCapacity =
                trip.getUsedCapacity()
                        + loadWeight;

        loadIds.add(loadId);

        trip.setLoadIds(
                loadIds
        );

        trip.setUsedCapacity(
                newUsedCapacity
        );

        db.collection("trips")
                .document(trip.getTripId())
                .update(
                        "loadIds",
                        loadIds,
                        "usedCapacity",
                        newUsedCapacity
                )
                .get();

        System.out.println(
                "================================="
        );

        System.out.println(
                "LOAD ADDED TO EXISTING TRIP"
        );

        System.out.println(
                "Trip ID: "
                        + trip.getTripId()
        );

        System.out.println(
                "All Load IDs in Trip: "
                        + loadIds
        );

        System.out.println(
                "New Load ID: "
                        + loadId
        );

        System.out.println(
                "Truck Capacity: "
                        + trip.getTotalCapacity()
                        + " Ton"
        );

        System.out.println(
                "Used Capacity: "
                        + newUsedCapacity
                        + " Ton"
        );

        System.out.println(
                "Available Capacity: "
                        + (
                                trip.getTotalCapacity()
                                        - newUsedCapacity
                        )
                        + " Ton"
        );

        System.out.println(
                "================================="
        );
    }


    // =========================================================
// FIND COMPLETED TRIP BY LOAD ID
// =========================================================

public Trip getCompletedTripByLoadId(
        String loadId
) throws Exception {

    List<Trip> trips = new ArrayList<>();

    for (QueryDocumentSnapshot document :
            db.collection("trips")
                    .whereArrayContains(
                            "loadIds",
                            loadId
                    )
                    .whereEqualTo(
                            "status",
                            "COMPLETED"
                    )
                    .get()
                    .get()
                    .getDocuments()) {

        trips.add(
                document.toObject(
                        Trip.class
                )
        );
    }

    if (trips.isEmpty()) {
        return null;
    }

    return trips.get(0);
}

    public Trip getCompletedTripByUserId(
        String userId
) throws Exception {

    List<Trip> trips = new ArrayList<>();

    for (QueryDocumentSnapshot document :
            db.collection("trips")
                    .whereEqualTo("userId", userId)
                    .whereEqualTo("status", "COMPLETED")
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

    Trip latestTrip = trips.get(0);

    for (Trip trip : trips) {

        if (trip.getCompletedTime() != null
                && latestTrip.getCompletedTime() != null
                && trip.getCompletedTime()
                        .compareTo(
                                latestTrip.getCompletedTime()
                        ) > 0) {

            latestTrip = trip;
        }
    }

    return latestTrip;
}

    // =========================================================
    // UPDATE TRIP STATUS
    // =========================================================

    public void updateTripStatus(
            String tripId,
            String status,
            String time
    ) throws Exception {

        if ("IN_TRANSIT".equalsIgnoreCase(status)) {

            db.collection("trips")
                    .document(tripId)
                    .update(
                            "status",
                            status,
                            "startTime",
                            time
                    )
                    .get();

        } else if ("COMPLETED".equalsIgnoreCase(status)) {

            db.collection("trips")
                    .document(tripId)
                    .update(
                            "status",
                            status,
                            "completedTime",
                            time
                    )
                    .get();

        } else {

            db.collection("trips")
                    .document(tripId)
                    .update(
                            "status",
                            status
                    )
                    .get();
        }

        System.out.println(
                "Trip status updated: "
                        + tripId
                        + " -> "
                        + status
        );
    }
}