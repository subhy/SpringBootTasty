package com.example.TastyFoodSpring.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Item {

    @Id
    private String icode;
    private String orderid;
    private String description;
    private double unitPrice;
    private double qtyOnHand;
    private double shout;
    private double balance;
    private double vary;



}
