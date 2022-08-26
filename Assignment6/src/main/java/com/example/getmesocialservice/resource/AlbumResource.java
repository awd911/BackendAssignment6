package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.Exception.exception;
import com.example.getmesocialservice.model.Album;
import com.example.getmesocialservice.model.User;
import com.example.getmesocialservice.model.firebaseUser;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.getmesocialservice.service.AlbumService;
import com.example.getmesocialservice.service.FirebaseService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class AlbumResource {

    @Autowired
    private  AlbumService albumService;

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping("/album")
    public Album getAlbum(){
        return albumService.getAlbum();
    }

    @GetMapping("/albums")
    public List<Album> getAllAlbums(){
        return albumService.getAllAlbums();
    }

    @GetMapping("/album/{id}")
    public Album getAlbumById(@PathVariable("id") int id){
        return albumService.getAlbumById(id);
    }

    @PostMapping("/album")
    public Album saveAlbum(@RequestBody @Valid Album album, @RequestHeader(name ="idToken") String idToken ) throws exception, FirebaseAuthException, IOException
    {
        firebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if(firebaseUser !=null){
            return albumService.saveAlbum(album);
        }

        return null;
    }

    @DeleteMapping("/album")
    public Album deleteAlbum(@RequestParam(name="id") int id, @RequestBody User user, @RequestHeader(name ="idToken") String idToken ) throws exception,FirebaseAuthException, IOException
    {
        firebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if(firebaseUser !=null) {
            return albumService.deleteAlbum(id);
        }
        return null;
    }
}
