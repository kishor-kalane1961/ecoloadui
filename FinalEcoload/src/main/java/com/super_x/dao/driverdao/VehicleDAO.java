package com.super_x.dao.driverdao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.api.core.ApiFuture;
//import com.google.cloud.firestore.WriteResult;
import com.super_x.model.drivermodel.VehicleModel;

public class VehicleDAO {

    private final Firestore firestore;

    public VehicleDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    public void saveVehicle(VehicleModel vehicle) throws Exception {

        firestore.collection("vehicles")
                .document(vehicle.getDriverEmail())
                .set(vehicle)
                .get();
    }

    public VehicleModel getVehicleByDriverEmail(
            String driverEmail) throws Exception {

        ApiFuture<DocumentSnapshot> future =
                firestore.collection("vehicles")
                        .document(driverEmail)
                        .get();

        DocumentSnapshot document = future.get();

        if (!document.exists()) {
            return null;
        }

        return document.toObject(VehicleModel.class);
    }
}