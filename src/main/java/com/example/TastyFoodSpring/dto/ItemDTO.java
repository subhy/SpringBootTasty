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

    String code;
    String name;
    Double price;

}
