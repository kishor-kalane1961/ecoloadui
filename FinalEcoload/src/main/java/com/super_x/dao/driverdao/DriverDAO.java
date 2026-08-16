package com.super_x.dao.driverdao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.super_x.model.drivermodel.DriverModel;

public class DriverDAO {

    private final Firestore firestore;

    public DriverDAO(Firestore firestore) {
        this.firestore = firestore;
    }

    public void saveDriver(DriverModel driver) throws Exception {

        ApiFuture<WriteResult> future =
                firestore.collection("drivers")
                        .document(driver.getEmail())
                        .set(driver);

        future.get();
    }

    public DriverModel getDriverByEmail(String email) throws Exception {

        ApiFuture<DocumentSnapshot> future =
                firestore.collection("drivers")
                        .document(email)
                        .get();

        DocumentSnapshot document = future.get();

        if (!document.exists()) {
            return null;
        }

        return document.toObject(DriverModel.class);
    }

    public void updateDriverStatus(String email,
                                   String status) throws Exception {

        firestore.collection("drivers")
                .document(email)
                .update(
                        "status", status,
                        "updatedAt",
                        java.time.Instant.now().toString()
                )
                .get();
    }
}