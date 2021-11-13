package com.example.TastyFoodSpring.repository;

import com.example.TastyFoodSpring.model.ODetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ODetailsReporsitory extends CrudRepository<ODetails, Integer> {
    @Query("SELECT DISTINCT od.orderId FROM ODetails od")
    List<Integer> findDistinctByOrderId();

    List<ODetails> findODetailsByOrderId(int orderId);


}
