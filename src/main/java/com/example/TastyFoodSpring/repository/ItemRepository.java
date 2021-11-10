package com.example.TastyFoodSpring.repository;

import com.example.TastyFoodSpring.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String> {


}
