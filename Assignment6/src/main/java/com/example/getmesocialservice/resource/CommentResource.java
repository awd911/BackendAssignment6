package com.example.getmesocialservice.resource;

import com.example.getmesocialservice.Exception.exception;
import com.example.getmesocialservice.model.Comment;
import com.example.getmesocialservice.model.User;
import com.example.getmesocialservice.model.firebaseUser;
import com.example.getmesocialservice.service.CommentService;
import com.example.getmesocialservice.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentResource {

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public Comment getComment(){
        return commentService.getComment();
    }

    @GetMapping("/comments")
    public List<Comment> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/comments/{id}")
    public Comment getCommentById(@PathVariable("id") int id){
        return commentService.getCommentById(id);
    }

    @PostMapping("/comment")
    public Comment saveComment(@RequestBody @Valid Comment comment, @RequestHeader(name ="idToken") String idToken ) throws exception, FirebaseAuthException, IOException
    {
        firebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if(firebaseUser != null){
            return commentService.saveComment(comment);
        }

        return null;
    }

    @DeleteMapping("/comment")
    public Comment deleteComment(@RequestParam(name ="id") int id, @RequestBody User user, @RequestHeader(name ="idToken") String idToken ) throws exception,FirebaseAuthException, IOException
    {
        firebaseUser firebaseUser = firebaseService.authenticate(idToken);

        if(firebaseUser != null) {
            return commentService.deleteComment(id);
        }
        return null;
    }
}
