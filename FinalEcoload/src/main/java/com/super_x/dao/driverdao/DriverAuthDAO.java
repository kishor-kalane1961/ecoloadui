package com.super_x.dao.driverdao;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DriverAuthDAO {

    /*
     * Get this from:
     * Firebase Console
     * → Project Settings
     * → General
     * → Your apps
     * → Web API Key
     */
    private static final String FIREBASE_API_KEY =
            "AIzaSyD1DR88oCmcr1mEaXI7ZWMqy7X8N4hosy8";

    private static final String AUTH_URL =
            "https://identitytoolkit.googleapis.com/v1/accounts:";

    private final HttpClient httpClient;

    public DriverAuthDAO() {
        httpClient = HttpClient.newHttpClient();
    }

    // =========================================================
    // DRIVER REGISTRATION
    // =========================================================

    public String registerDriver(String email, String password)
            throws Exception {

        JsonObject requestBody = new JsonObject();

        requestBody.addProperty("email", email);
        requestBody.addProperty("password", password);
        requestBody.addProperty("returnSecureToken", true);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        AUTH_URL +
                        "signUp?key=" +
                        FIREBASE_API_KEY
                ))
                .header("Content-Type", "application/json")
                .POST(
                        HttpRequest.BodyPublishers.ofString(
                                requestBody.toString()
                        )
                )
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() == 200) {

            JsonObject json =
                    JsonParser.parseString(response.body())
                            .getAsJsonObject();

            return json.get("localId").getAsString();
        }

        throw new Exception(
                getFirebaseError(response.body())
        );
    }


    // =========================================================
    // DRIVER LOGIN
    // =========================================================

    public String loginDriver(String email, String password)
            throws Exception {

        JsonObject requestBody = new JsonObject();

        requestBody.addProperty("email", email);
        requestBody.addProperty("password", password);
        requestBody.addProperty("returnSecureToken", true);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        AUTH_URL +
                        "signInWithPassword?key=" +
                        FIREBASE_API_KEY
                ))
                .header("Content-Type", "application/json")
                .POST(
                        HttpRequest.BodyPublishers.ofString(
                                requestBody.toString()
                        )
                )
                .build();

        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        if (response.statusCode() == 200) {

            JsonObject json =
                    JsonParser.parseString(response.body())
                            .getAsJsonObject();

            return json.get("localId").getAsString();
        }

        throw new Exception(
                getFirebaseError(response.body())
        );
    }


    // =========================================================
    // FIREBASE ERROR HANDLING
    // =========================================================

    private String getFirebaseError(String responseBody) {

        try {

            JsonObject json =
                    JsonParser.parseString(responseBody)
                            .getAsJsonObject();

            String message =
                    json.getAsJsonObject("error")
                            .get("message")
                            .getAsString();

            switch (message) {

                case "EMAIL_EXISTS":
                    return "Email is already registered.";

                case "INVALID_EMAIL":
                    return "Invalid email address.";

                case "WEAK_PASSWORD":
                    return "Password is too weak.";

                case "EMAIL_NOT_FOUND":
                    return "No account found with this email.";

                case "INVALID_PASSWORD":
                    return "Incorrect password.";

                case "USER_DISABLED":
                    return "This account has been disabled.";

                case "OPERATION_NOT_ALLOWED":
                    return "Email/password authentication is disabled.";

                case "TOO_MANY_ATTEMPTS_TRY_LATER":
                    return "Too many attempts. Try again later.";

                default:
                    return message;
            }

        } catch (Exception e) {

            return "Authentication failed.";
        }
    }
}