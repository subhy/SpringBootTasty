package com.example.TastyFoodSpring.service;

import com.example.TastyFoodSpring.model.ODetails;

import java.util.List;

public interface ODetailsService {

    public String getOrderDetails();

    public List<Integer> getDistinctOrders();


    public List<ODetails> getODetailsByOrderId(int orderid);

    public void deleteSentData();



}
