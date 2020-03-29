package com.store.retail.comment.cotroller;

import com.store.retail.comment.controller.CommentController;
import com.store.retail.comment.model.Comment;
import com.store.retail.comment.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc(secure = false)
//@WebFluxTest(value = CommentController.class)
//@WebMvcTest(value = CommentController.class)
//@WebFluxTest(CommentController.class)
public class CommentControllerOLDTest {

    //@Autowired
    private MockMvc mockMvc;

    //@Autowired
   //private WebTestClient webClient;

    @MockBean
    private CommentService commentService;

    private String comment = "{\"title\":\"Buy this Book!\",\"content\":\"Great Book!\",\"creator\":\"user\",\"productId\":\"1\"}";


    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

   @Test
   @WithMockUser(username="admin",roles={"USER","ADMIN"})
   public void readCommentsTest() throws Exception{
      // when(commentService.readComments(any())).thenReturn(Flux.);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/comments/read").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println("Response = " + result.getResponse().getContentAsString());

        String expected = "[{\"id\":\"1\",\"title\":\"Buy this Book!\",\"content\":\"Great Book!\",\"creator\":\"user\",\"productId\":\"1\"}]";

          JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), true);


    }
   // @Test
   /* public void post() throws Exception {

        webClient.post().uri("/comments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(comment))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class)
                .isEqualTo("Post Successfully!");
    }*/
   //@Test
   //@WithMockUser(username="admin",roles={"USER","ADMIN"})
   public void createCommentTest() throws Exception{
        Comment c1=  new Comment();

        c1.setId("1");
        c1.setContent("Great Book!");
        c1.setTitle("Buy this Book!");
        c1.setCreator("user");
        c1.setProductId("1");
       // when(commentService.createComment(any(Comment.class))).thenReturn(Mono.just(c1));

       given(this.commentService.createComment(any(Comment.class))).willReturn(Mono.just(c1));

      /* given(commentService.createComment(c1))
               .willReturn(Mono.just(c1));*/

        // Send course as body to /comments/add
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/comments/add")
                .accept(MediaType.APPLICATION_JSON).content(comment).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost//comments/add",
                response.getHeader(HttpHeaders.LOCATION));

    }



    private List<Comment> buildListOfComments(){

        Comment c1=  new Comment();

        c1.setId("1");
        c1.setContent("Great Book!");
        c1.setTitle("Buy this Book!");
        c1.setCreator("user");
        c1.setProductId("1");

        Comment c2=  new Comment();

        c2.setId("2");
        c2.setContent("Nice head phones!");
        c2.setTitle("Thumbs up!");
        c2.setCreator("user");
        c2.setProductId("2");

        List<Comment> list = new ArrayList<>();
        Collections.addAll(list, c1);

        return list;

    }

    /*private Flux<Comment> buildFluxOfComments(){

        Comment c1=  new Comment();

        c1.setId("1");
        c1.setContent("Great Book!");
        c1.setTitle("Buy this Book!");
        c1.setCreator("user");
        c1.setProductId("1");

        Comment c2=  new Comment();

        c2.setId("2");
        c2.setContent("Nice head phones!");
        c2.setTitle("Thumbs up!");
        c2.setCreator("user");
        c2.setProductId("2");

        List<Comment> list = new ArrayList<>();
        Collections.addAll(list, c1);

        return list;

    }*/
}
