package com.example.TastyFoodSpring.repository;

import com.example.TastyFoodSpring.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, String> {

    List<Item> findItemsByOrderid(String orderid);

}
