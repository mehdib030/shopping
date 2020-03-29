package com.store.retail.customer;

import com.store.retail.order.LineOrder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    private int id;
    private String name;
    @ManyToMany(mappedBy = "customer")
    private List<LineOrder> orders= new ArrayList<>();




}
