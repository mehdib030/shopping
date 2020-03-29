package com.store.retail;



import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@SpringBootApplication
public class RetailApplication {

    public static void main(String[] args) {

        SpringApplication.run(RetailApplication.class, args);}


        /*@Autowired
        MongoClient mongoClient;

        @Bean
        public ReactiveMongoTemplate reactiveMongoTemplate() {

            return new ReactiveMongoTemplate(mongoClient, "retaildb");
        }*/


}

