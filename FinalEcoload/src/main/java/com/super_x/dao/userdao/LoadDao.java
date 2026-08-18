package com.super_x.dao.userdao;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.super_x.config.FirebaseConfig;
import com.super_x.model.usermodel.Load;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoadDao {

    private final Firestore db =
            FirebaseConfig.getFireStore();

    // =========================================================
    // ADD LOAD
    // =========================================================

    public void addLoad(Load load) {

        try {

            String date =
                    new SimpleDateFormat("yyyyMMdd")
                            .format(new Date());

            String loadId =
                    "LD-"
                            + date
                            + "-"
                            + System.currentTimeMillis();

            load.setLoadId(
                    loadId
            );

            if (load.getStatus() == null
                    || load.getStatus().trim().isEmpty()) {

                load.setStatus(
                        "PENDING"
                );
            }

            db.collection("loads")
                    .document(loadId)
                    .create(load)
                    .get();

            System.out.println(
                    "Load Data Inserted"
            );

            System.out.println(
                    "Generated Load ID: "
                            + loadId
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =========================================================
    // FETCH ALL LOADS
    // =========================================================

    public List<Load> fetchAllLoads() {

        try {

            List<Load> loads =
                    new ArrayList<>();

            for (QueryDocumentSnapshot document :
                    db.collection("loads")
                            .get()
                            .get()
                            .getDocuments()) {

                loads.add(
                        document.toObject(
                                Load.class
                        )
                );
            }

            return loads;

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // =========================================================
// FETCH COMPLETED LOADS FOR USER
// =========================================================

public List<Load> getCompletedLoadsByUserId(
        String userId
) {

    try {

        List<Load> completedLoads =
                new ArrayList<>();

        for (QueryDocumentSnapshot document :
                db.collection("loads")
                        .whereEqualTo(
                                "userId",
                                userId
                        )
                        .whereEqualTo(
                                "status",
                                "COMPLETED"
                        )
                        .get()
                        .get()
                        .getDocuments()) {

            completedLoads.add(
                    document.toObject(
                            Load.class
                    )
            );
        }

        return completedLoads;

    } catch (Exception e) {

        e.printStackTrace();

        return new ArrayList<>();
    }
}

    // =========================================================
    // FETCH LOAD BY ID
    // =========================================================

    public Load getLoadById(
            String loadId
    ) {

        try {

            var snapshot =
                    db.collection("loads")
                            .document(loadId)
                            .get()
                            .get();

            if (!snapshot.exists()) {

                return null;
            }

            return snapshot.toObject(
                    Load.class
            );

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // UPDATE LOAD STATUS + DRIVER
    // =========================================================

    public void updateLoadStatus(
            String loadId,
            String status,
            String driverId,
            String driverName,
            String acceptedAt
    ) throws Exception {

        db.collection("loads")
                .document(loadId)
                .update(
                        "status",
                        status,
                        "driverId",
                        driverId,
                        "driverName",
                        driverName,
                        "acceptedAt",
                        acceptedAt
                )
                .get();

        System.out.println(
                "Load status updated: "
                        + loadId
                        + " -> "
                        + status
        );
    }

    // =========================================================
    // UPDATE LOAD STATUS ONLY
    // Used when completing the trip
    // =========================================================

    public void updateLoadStatusOnly(
            String loadId,
            String status
    ) throws Exception {

        db.collection("loads")
                .document(loadId)
                .update(
                        "status",
                        status
                )
                .get();

        System.out.println(
                "Load status updated: "
                        + loadId
                        + " -> "
                        + status
        );
    }
}