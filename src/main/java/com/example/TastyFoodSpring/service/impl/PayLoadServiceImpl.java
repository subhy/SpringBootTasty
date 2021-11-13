package com.example.TastyFoodSpring.service.impl;

import com.example.TastyFoodSpring.service.ODetailsService;
import com.example.TastyFoodSpring.service.PayLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayLoadServiceImpl implements PayLoadService {

    @Autowired
    ODetailsService oDetailsService;

    @Override
    public String getPrimaryUpdatePayload() {
       // ODetailsService orderService =new ODetailsServiceImpl();
        return String.valueOf(oDetailsService.getOrderDetails());
       // return null;

    }
}
