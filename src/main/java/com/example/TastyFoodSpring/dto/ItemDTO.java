package com.example.TastyFoodSpring.dto;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemDTO {

    private String icode;
    private String orderid;
    private String description;
    private double unitPrice;
    private double qtyOnHand;
    private double shout;
    private double balance;
    private double vary;
}
