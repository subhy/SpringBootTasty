package com.example.TastyFoodSpring.controller;


import com.example.TastyFoodSpring.network.NetworkCalls;
import com.example.TastyFoodSpring.service.ODetailsService;
import com.example.TastyFoodSpring.service.PayLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/Item")
public class ItemController {

   /* @Autowired
    private ItemService itemService;*/

    @Autowired
    private NetworkCalls networkCalls;

    @Autowired
    private ODetailsService oDetailsService;

    @Autowired
    private PayLoadService payLoadService;

  /*  @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO saveItem(@RequestBody ItemDTO itemDTO) {
        System.out.println("*********************");
        System.out.println(itemDTO.toString());
        return itemService.saveItem(itemDTO);
    }*/

//    @GetMapping("/{iCode}")
//    public ItemDTO findItem(@PathVariable String iCode) {
//        System.out.println(itemService.getItem(iCode));
//        return itemService.getItem(iCode);
//
//    }



    @GetMapping("/testTokenProcess")
    public String testToken(){
       // NetworkCallsImpl networkCalls = new NetworkCallsImpl();
        return networkCalls.getToken();
    }

   /* @GetMapping("/testByOrder/{orderid}")
    public List<ItemDTO> getItemsByOrder(@PathVariable String orderid){
        return itemService.getItemsByOrderId(orderid);
    }*/

    @GetMapping("/orders")
    public String getOrders(){
      //  return oDetailsService.getOrderDetails();
      return payLoadService.getPrimaryUpdatePayload();
    }

    /*@GetMapping("/testJson")
    public String testJSon(){
        // NetworkCallsImpl networkCalls = new NetworkCallsImpl();
        System.out.println("**********************");
        return networkCalls.postItemData();
    }*/

}
