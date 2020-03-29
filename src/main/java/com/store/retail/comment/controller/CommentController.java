package com.store.retail.comment.controller;

import com.store.retail.comment.model.Comment;
import com.store.retail.comment.service.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/comments/read")
    public Flux<Comment> getComments(Pageable pageable){

       /* System.out.println("Number of pages = "+pageable.getPageNumber());

        System.out.println("Page size = "+pageable.getPageSize());*/


        return this.commentService.readComments(pageable);

    }

    //@GetMapping("/create")
   // @RequestMapping(value = "/addNewItem", method = RequestMethod.POST, consumes =    MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/comments/add",consumes = MediaType.APPLICATION_JSON_VALUE)
    //public @ResponseBody Comment createComment(@RequestBody MultiValueMap<String, String> formData){
    public Comment createComment(@RequestBody Comment comment){

        System.out.println("Creating comment");

        System.out.println("Title : "+comment.getTitle());
        System.out.println("Content : "+comment.getContent());
        /*mongodb+srv://mehdib:<PASSWORD>@cluster0-misai.mongodb.net/test?retryWrites=true*/

        return this.commentService.createComment(comment).block();


    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/comments/update",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateComment(@RequestBody Comment comment){
        System.out.println("Updating comment");
        System.out.println("Id: "+comment.getId());
        System.out.println("Title: "+comment.getTitle());
        System.out.println("Content: "+comment.getContent());
       // ObjectId objectId = new ObjectId("");
        this.commentService.updateComment(comment);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/comments/read/{id}")
    public Mono<Comment> readComment(@PathVariable String id){
        return this.commentService.readComment(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/comments/read1/{productId}")
    public List<Comment> readCommentsByProductId(@PathVariable String productId){
        return this.commentService.readCommentsByProductId(productId).collectList().block();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/comments/delete/{id}")
    public void deleteComment(@PathVariable String id){
        this.commentService.deleteComment(id);
    }

}
