package com.example.demojar.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Scanner;

@RestController
@RequestMapping("/verify")
public class UtilController {
//    @RequestMapping(value= "/api/**", method= RequestMethod.OPTIONS)
//    public void corsHeaders(HttpServletResponse response) {
//        response.addHeader("Access-Control-Allow-Origin", "*");
//        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
//        response.addHeader("Access-Control-Max-Age", "3600");
//
//    }
    @GetMapping("/hello")
    public String method()
    {
        return "Hello ujjman!!";
    }
    @PostMapping("/verifyToken")
    public ResponseEntity<Boolean> verifyToken(@RequestBody String token) {
        // Call the method to verify the token
        int info=0;
        try {
            info=getUserInfo(token);
            if(info==1) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(false, HttpStatus.OK);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }

    }



    public static int getUserInfo(String accessToken) throws IOException {
        String endpoint = "https://www.googleapis.com/oauth2/v2/userinfo"; // Google UserInfo endpoint
        URL url = new URL(endpoint + "?access_token=" + accessToken);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner scanner = new Scanner(url.openStream());
            String response = scanner.useDelimiter("\\Z").next();
            scanner.close();

            // Here you would parse the JSON response to extract the user details.
            return 1;
        } else {
            // Handle non-200 response
            return 0;
        }
    }

}
