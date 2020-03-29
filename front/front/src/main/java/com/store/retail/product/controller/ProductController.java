package com.store.retail.product.controller;

import com.store.retail.comment.model.Comment;
import com.store.retail.comment.service.CommentService;
import com.store.retail.product.model.Product;
import com.store.retail.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CommentService commentService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products")
    public List<Product> readProducts(){

       // List<Comment> comments=  new Arr

        List<Product> products = productService.readProducts();

        products.forEach(product -> {
            Flux<Comment> comments = commentService.readCommentsByProductId(String.valueOf(product.getId()));
            //Mono<List<Comment>> mono = comments.collectList();
            comments.subscribe(com ->{
                product.getComments().add(com);
            });

        });



        products.forEach(c -> System.out.println(c.getName()));

        return products;

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products/product/{id}")
    public @ResponseBody
    Product readProductById(@PathVariable Integer id){

        Product product = productService.readProductById(id);

        Flux<Comment> comments = commentService.readCommentsByProductId(String.valueOf(id));
        //Mono<List<Comment>> mono = comments.collectList();
        product.setComments(comments.collectList().block());

        /*        .subscribe(com ->{
            product.getComments().add(com);
        });*/

        System.out.println("--"+product.getName());
        System.out.println("--"+product.getSku());
        System.out.println("--"+product.getDescription());
        System.out.println("--"+product.getComments().size());

        return product;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/products/productbyname/{name}")
    public List<Product> readProductByName(@PathVariable String name){
        Optional<List<Product>> prods = productService.readProductByName(name);
        List<Product> products=new ArrayList<>();

        if(StringUtils.isEmpty(name)){
            return readProducts();
        }

        if(prods.isPresent()) {
            products = prods.get();

            products.forEach(product -> {
                Flux<Comment> comments = commentService.readCommentsByProductId(String.valueOf(product.getId()));
                //Mono<List<Comment>> mono = comments.collectList();
                product.setComments(comments.collectList().block());
                System.out.println("--" + product.getName());
                System.out.println("--" + product.getSku());
                System.out.println("--" + product.getDescription());
                System.out.println("--" + product.getComments().size());
            });


        /*        .subscribe(com ->{
            product.getComments().add(com);
        });*/



        }

        return products;
    }

}
