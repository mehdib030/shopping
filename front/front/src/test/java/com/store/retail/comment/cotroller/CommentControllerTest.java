package com.store.retail.comment.cotroller;

import com.store.retail.comment.controller.CommentController;
import com.store.retail.comment.model.Comment;
import com.store.retail.comment.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@WebMvcTest(value = CommentController.class, secure = false)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWebTestClient
//@WithMockUser(username="admin",roles={"USER","ADMIN"})
//@WithMockUser(setupBefore = TestExecutionEvent.TEST_EXECUTION)
//@WebFluxTest
//@ContextConfiguration
//@WithMockUser
public class CommentControllerTest {

   /* @Autowired
    private MockMvc mockMvc;*/

    @MockBean
    private CommentService commentService;

    @Autowired
    WebTestClient client ;

    private String comment = "{\"title\":\"Buy this Book!\",\"content\":\"Great Book!\",\"creator\":\"user\",\"productId\":\"1\"}";

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

   // @Test
    //@WithMockUser(username="admin",roles={"USER","ADMIN"})
   /* public void readCommentsTest() throws Exception{

        Flux<Comment> nonEmptyFlux = Flux.just(c1, c2);

        when(commentService.readComments(any())).thenReturn(nonEmptyFlux);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/comments/read").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println("Response = " + result.getResponse().getContentAsString());

        String expected = "[{\"id\":\"1\",\"title\":\"Buy this Book!\",\"content\":\"Great Book!\",\"creator\":\"user\",\"productId\":\"1\"}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), true);


    }*/

    @Test
    public void readCommentsTest1(){
        Flux<Comment> nonEmptyFlux = Flux.just(c1, c2);
        
        given(commentService.readComments(any()))
                .willReturn(nonEmptyFlux);
        
        client.get().uri("/comments/read").exchange()
                .expectStatus().isOk()
                .expectBody();
                /*.jsonPath("$[0].title").isEqualTo("my first post")
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[0].content").isEqualTo("content of my first post");*/

       // verify(this.posts, times(1)).findAll();
       // verifyNoMoreInteractions(this.posts);
    }


}
