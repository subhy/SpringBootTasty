package com.example.TastyFoodSpring.service;

import com.example.TastyFoodSpring.dto.ItemDTO;
import com.example.TastyFoodSpring.model.Item;

import java.util.List;


public interface ItemService {

    ItemDTO getItem(String code);

    List<Item> findAll();

    List<ItemDTO> getItemsByOrderId(String orderid);



}
