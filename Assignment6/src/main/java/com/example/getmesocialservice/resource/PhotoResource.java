package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.Exception.exception;
import com.example.getmesocialservice.model.Photo;
import com.example.getmesocialservice.model.User;
import com.example.getmesocialservice.model.firebaseUser;
import com.example.getmesocialservice.service.FirebaseService;
import com.example.getmesocialservice.service.PhotoService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PhotoResource {

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/photo")
    public Photo getPhoto() {
        return photoService.getPhoto();
    }

    @GetMapping("/photos")
    public List<Photo> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/photo/{id}")
    public Photo getPhotoById(@PathVariable("id") int id) {
        return photoService.getPhotoById(id);
    }

    @PostMapping("/photo")
    public Photo savePhoto(@RequestBody @Valid Photo photo, @RequestHeader(name = "idToken") String idToken) throws exception, FirebaseAuthException, IOException {
        firebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {
            return photoService.savePhoto(photo);
        }
        return null;
    }

    @DeleteMapping("/photo")
    public Photo deletePhoto(@RequestParam(name = "id") int id, @RequestBody User user, @RequestHeader(name = "idToken") String idToken) throws exception, FirebaseAuthException, IOException {

        firebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if (firebaseUser != null) {
            return photoService.deletePhoto(id);
        }
        return null;
    }
}
