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
    private int oNo;
    private Date orderDate;
    private int orderId;
    private int orderNo;
    private String iCode;
    private String iName;
    private double qty;
    private double uPrice;
    private String pMethod;
    private String uId;
}
