package com.super_x.config;

import java.io.FileInputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class FirebaseConfig {

    static{
        getFirebaseConfig();
    }

    public static void getFirebaseConfig() {

        try{
            FileInputStream serviceAccount =
            new FileInputStream("src\\main\\resources\\ecoload.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

            FirebaseApp.initializeApp(options);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Firebase Config");
    }
    public static Firestore getFireStore() {
        return FirestoreClient.getFirestore();
    }
    
    
}
 
