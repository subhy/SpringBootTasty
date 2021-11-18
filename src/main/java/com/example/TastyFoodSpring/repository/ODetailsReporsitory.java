package com.example.TastyFoodSpring.repository;

import com.example.TastyFoodSpring.model.ODetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ODetailsReporsitory extends CrudRepository<ODetails, Integer> {
    @Query("SELECT DISTINCT od.receiptno FROM ODetails od")
    List<Integer> findDistinctByReceiptno();

    List<ODetails> findODetailsByReceiptno(int receiptno);




}
