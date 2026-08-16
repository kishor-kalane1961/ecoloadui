package com.super_x.dao.userdao;

import com.super_x.dao.userdao.LoadDao;
import com.super_x.model.usermodel.Load;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.super_x.config.FirebaseConfig;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoadDao {

    private Firestore db = FirebaseConfig.getFireStore();

    // Add Load
    public void addLoad(Load load) {

        try {

            String date = new SimpleDateFormat("yyyyMMdd")
                    .format(new Date());

            String loadId = "LD-" + date + "-" +
                    System.currentTimeMillis();

            load.setLoadId(loadId);

            db.collection("loads")
                    .document(loadId)
                    .create(load);

            System.out.println("Load Data Inserted");
            System.out.println("Generated Load ID: " + loadId);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // Fetch All Loads
    public List<Load> fetchAllLoads() {

        try {

            List<Load> loads = new ArrayList<>();

            for (QueryDocumentSnapshot document :
                    db.collection("loads")
                            .get()
                            .get()
                            .getDocuments()) {

                loads.add(
                        document.toObject(Load.class)
                );
            }

            return loads;

        } catch (Exception e) {

            e.printStackTrace();

            return new ArrayList<>();
        }
    }

    // Fetch Load By ID
    public Load getLoadById(String loadId) {

        try {

            QueryDocumentSnapshot document = null;

            var snapshot =
                    db.collection("loads")
                            .document(loadId)
                            .get()
                            .get();

            if (!snapshot.exists()) {
                return null;
            }

            return snapshot.toObject(Load.class);

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}