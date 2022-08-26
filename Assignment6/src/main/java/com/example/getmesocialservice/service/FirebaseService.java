package com.example.getmesocialservice.service;


import java.io.IOException;

import com.example.getmesocialservice.model.firebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {



    public firebaseUser authenticate (String idToken) throws IOException, FirebaseAuthException{

        String uid = FirebaseAuth.getInstance().verifyIdToken(idToken).getUid();
        String name = FirebaseAuth.getInstance().verifyIdToken(idToken).getName();
        String email = FirebaseAuth.getInstance().verifyIdToken(idToken).getEmail();

        return new firebaseUser(uid,name,email);

    }

}
