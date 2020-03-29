package com.store.retail.order;

import com.store.retail.customer.Customer;
import com.store.retail.product.model.Product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class LineOrder {

    @Id
    private int id;
    private int number;
    private String name;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<Product>  products = new ArrayList<>();
}
