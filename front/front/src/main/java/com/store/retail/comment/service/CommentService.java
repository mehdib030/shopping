package com.store.retail.comment.service;

import com.store.retail.comment.model.Comment;
import com.store.retail.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    ReactiveMongoTemplate template;

    @Autowired
    CommentRepository commentRepository;

    public Flux<Comment> readComments(Pageable pageable){
        return this.template.findAll(Comment.class);//.collectList().block();
        //try also using Query with pageable so that you can use reactive sol

        //return commentRepository.findAll(pageable).getContent();
    }

    public Mono<Comment> readComment(String id){
        //return this.template.find(Query.query(Criteria.where("lastname").is("White")), Comment.class);

        return this.template.findOne(Query.query(Criteria.where("id").is(id)), Comment.class);
    }

    public Flux<Comment> readCommentsByProductId(String productId) {
        return this.template.find(Query.query(Criteria.where("productId").is(productId)), Comment.class);
    }

    public Mono<Comment> createComment(Comment comment){
        return this.template.save(comment);
    }

    public void updateComment(Comment comment){
        this.template.save(comment).subscribe();
    }

    public void deleteComment(String id){
        this.template.findAndRemove(Query.query(Criteria.where("id").is(id)),Comment.class).subscribe();
    }
}
