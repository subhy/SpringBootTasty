package com.example.TastyFoodSpring.controller;


import com.example.TastyFoodSpring.dto.ItemDTO;
import com.example.TastyFoodSpring.network.NetworkCalls;
import com.example.TastyFoodSpring.network.impl.NetworkCallsImpl;
import com.example.TastyFoodSpring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/Item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private NetworkCalls networkCalls;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO saveItem(@RequestBody ItemDTO itemDTO) {
        System.out.println("*********************");
        System.out.println(itemDTO.toString());
        return itemService.saveItem(itemDTO);
    }

    @GetMapping("/{iCode}")
    public ItemDTO findItem(@PathVariable String iCode) {
        System.out.println(itemService.getItem(iCode));
        return itemService.getItem(iCode);

    }



    @GetMapping("/testTokenProcess")
    public String testToken(){
       // NetworkCallsImpl networkCalls = new NetworkCallsImpl();
        return networkCalls.getToken();
    }

    @GetMapping("/testJson")
    public String testJSon(){
        // NetworkCallsImpl networkCalls = new NetworkCallsImpl();
        System.out.println("**********************");
        return networkCalls.postItemData();
    }

}
