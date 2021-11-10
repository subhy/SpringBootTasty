package com.example.TastyFoodSpring.service.impl;


import com.example.TastyFoodSpring.dto.ItemDTO;
import com.example.TastyFoodSpring.model.Item;
import com.example.TastyFoodSpring.repository.ItemRepository;
import com.example.TastyFoodSpring.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;


    @Override
    public ItemDTO getItem(String code) {
        ItemDTO itemDTO = new ItemDTO();
        Optional<Item> itemById = itemRepository.findById(code);
        if (itemById!=null){
            BeanUtils.copyProperties(itemById.get(),itemDTO);
        }
        return itemDTO;
    }

    @Override
    public ItemDTO saveItem(ItemDTO itemDTO) {
        Item item = new Item();
        BeanUtils.copyProperties(itemDTO,item);
        Item saveItem = itemRepository.save(item);
        ItemDTO itemDTORet = new ItemDTO();
        BeanUtils.copyProperties(saveItem,itemDTORet);
        return itemDTORet;
    }

    @Override
    public void deleteItem(String code) {
        ItemDTO itemDTO = new ItemDTO();
        itemRepository.deleteById(code);


    }

    @Override
    public List<Item> findAll() {
        return null;
    }
}
