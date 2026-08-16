package com.super_x.controller.drivercontroller;



import com.super_x.dao.driverdao.TripDAO;
import com.super_x.model.drivermodel.Trip;

public class TripController {

    private final TripDAO tripDAO =
            new TripDAO();

    public void createTrip(
        String loadId,
        String userId,
        String driverId,
        String driverName,
        String pickupLocation,
        String destination,
        double totalCapacity,
        double loadWeight
) throws Exception { 

        Trip trip = new Trip();

        trip.setTripId(
                "TRIP-" + System.currentTimeMillis()
        );

        trip.setLoadId(loadId);
        trip.setUserId(userId);
        trip.setDriverId(driverId);
        trip.setDriverName(driverName);
        trip.setPickupLocation(pickupLocation);
        trip.setDestination(destination);

        trip.setTotalCapacity(totalCapacity);
        trip.setUsedCapacity(loadWeight);
        trip.setDistanceKm(0);
        trip.setEta("Calculating...");
        trip.setStatus("ACTIVE");

        tripDAO.saveTrip(trip);
    }
}