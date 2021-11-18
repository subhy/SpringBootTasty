package com.example.TastyFoodSpring.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ODetailsDTO {
    private int sno;  //Sequential No with Auto increment
    private String batchcode; //Batch Code ddmmyyyyhhmmss
    private String receiptdate; //Receipt Date DD/MM/YYYY
    private String receipttime; //Receipt Time HH:MM:SS
    private int receiptno; //Receipt No
    private int itemno;  //Item counting no
    private String icode;
    private String idesc;
    private double qty;
    private double uprice;
    private double salestaxrate;  //Sales Tax Rate

}
