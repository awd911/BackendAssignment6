package com.example.getmesocialservice.service;

import java.io.FileInputStream;
import java.io.IOException;

import com.example.getmesocialservice.model.firebaseUser;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    public FirebaseApp initializeFirebase() throws IOException {

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource resource = resolver.getResource("classpath:gmh-backend-a6-firebase-adminsdk-g7ue4-4f0110bf30.json");

        FileInputStream serviceAccount = new FileInputStream(resource.getFile());

        FirebaseOptions options = ( new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)))
                .build();

       return FirebaseApp.initializeApp(options);
    }

    public firebaseUser authenticate (String idToken) throws IOException, FirebaseAuthException{


        String uid = FirebaseAuth.getInstance(initializeFirebase()).verifyIdToken(idToken).getUid();
        String name = FirebaseAuth.getInstance(initializeFirebase()).verifyIdToken(idToken).getName();
        String email = FirebaseAuth.getInstance(initializeFirebase()).verifyIdToken(idToken).getEmail();

        return new firebaseUser(uid,name,email);

    }

}
