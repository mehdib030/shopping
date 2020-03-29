package com.store.retail.comment.service;

import com.store.retail.comment.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
//@Slf4j
public class CommentServiceTest {

    @MockBean
    ReactiveMongoTemplate template;


    private CommentService commentService = new CommentService();

    Comment c1=  new Comment();

    Comment c2=  new Comment();

    @Before
    public void setUp(){


        c1.setId("1");
        c1.setContent("Great Book!");
        c1.setTitle("Buy this Book!");
        c1.setCreator("user");
        c1.setProductId("1");



        c2.setId("2");
        c2.setContent("Nice head phones!");
        c2.setTitle("Thumbs up!");
        c2.setCreator("user");
        c2.setProductId("2");

        //client =  WebTestClient.bindToController(new CommentController()).build();
    }

    @Test
    public void readCommentsTest(){

        given(template.findAll(any())).willReturn(Flux.just(c1,c2));

        StepVerifier.create(commentService.readComments(null)).expectNextCount(2)
                .expectComplete();
    }
}
