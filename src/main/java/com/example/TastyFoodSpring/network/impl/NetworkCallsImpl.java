package com.example.TastyFoodSpring.network.impl;

import com.example.TastyFoodSpring.dto.TokenDTO;
import com.example.TastyFoodSpring.dto.payloadDTO.ItemPayload;
import com.example.TastyFoodSpring.dto.payloadDTO.PosSalesPayload;
import com.example.TastyFoodSpring.dto.payloadDTO.PrimaryPayload;
import com.example.TastyFoodSpring.dto.payloadDTO.ResponsePayload;
import com.example.TastyFoodSpring.network.NetworkCalls;
import com.example.TastyFoodSpring.service.ODetailsService;
import com.example.TastyFoodSpring.service.impl.ODetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@EnableScheduling
public class NetworkCallsImpl implements NetworkCalls {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ODetailsService oDetailsService;



    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public String getToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");
        map.add("client_id", "CCB1-PS-21-00000018");
        map.add("client_secret", "e4gUOM7nEhCfJAmOMkxrhg==");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.exchange("https://uat2-pos.imonitor.center/connect/token",
                HttpMethod.POST,
                entity,
                String.class);

        String tokenResponse = response.getBody();
       //// System.out.println(tokenResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String access_token = null;
        try {
            TokenDTO tokenDTO = objectMapper.readValue(tokenResponse, TokenDTO.class);
            access_token = tokenDTO.getAccess_token();
            // return access_token;
        ////    System.out.println("************ " + access_token);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.setBearerAuth(access_token);

        String jsonParsedPayload = this.postItemData();

        HttpEntity<String> request =
                new HttpEntity<String>(jsonParsedPayload, headers2);

      //  System.out.println(request);
        ResponsePayload responsePayload = restTemplate.postForObject("https://uat2-pos.imonitor.center/api/possale/importpossaleswithitems",
                request, ResponsePayload.class);

        String result = new Gson().toJson(responsePayload);

        System.out.println(result);


        //return jsonParsedPayload;
        return result;

    }

    // @Override
    private String postItemData() {




       /* PrimaryPayload primaryPayload = new PrimaryPayload();
        primaryPayload.setAppCode("POS-02");
        primaryPayload.setPropertyCode("CCB1");
        primaryPayload.setClientID("CCB1-PS-21-00000018");
        primaryPayload.setClientSecret("e4gUOM7nEhCfJAmOMkxrhg==");
        primaryPayload.setPOSInterfaceCode("CCB1-PS-21-00000018");
        //primaryPayload.setBatchCode("2021110321211612");
        primaryPayload.setBatchCode(LocalDateTime.now().toString());

        ////////////////////// set Order /////////////////////
        List<PosSalesPayload> PosSales = new ArrayList<>();

        PosSalesPayload order1 = new PosSalesPayload();
        order1.setPropertyCode("CCB1");
        order1.setPOSInterfaceCode("CCB1-PS-21-00000018");
        order1.setReceiptDate("06/11/2021");
        order1.setReceiptTime("10:15:12");
        order1.setReceiptNo("R00003");
        order1.setNoOfItems("3");
        order1.setSalesCurrency("LKR");
        order1.setTotalSalesAmtB4Tax("500.00");
        order1.setTotalSalesAmtAfterTax("525.00");
        order1.setSalesTaxRate("5");
        order1.setServiceChargeAmt("0.00");
        order1.setPaymentAmt("525.00");
        order1.setPaymentCurrency("LKR");
        order1.setPaymentMethod("Cash");
        order1.setSalesType("Sales");

        ///////////////////   Item Setup  ///////////////////
        ItemPayload item1 = new ItemPayload();

        item1.setItemDesc("Apple");
        item1.setItemAmt("300");
        item1.setItemDiscountAmt("0");

        ItemPayload item2 = new ItemPayload();
        item2.setItemDesc("Apple2");
        item2.setItemAmt("300");
        item2.setItemDiscountAmt("0");

        ItemPayload item3 = new ItemPayload();
        item3.setItemDesc("Apple3");
        item3.setItemAmt("300");
        item3.setItemDiscountAmt("0");

        ArrayList<ItemPayload> Items = new ArrayList<>();
        Items.add(item1);
        Items.add(item2);
        Items.add(item3);
        ///////////////////////////////////////////////////////////

        order1.setItems(Items);
        PosSales.add(order1);
        primaryPayload.setPosSales(PosSales);


        String jsonParsedPayload = new Gson().toJson(primaryPayload);

        return jsonParsedPayload;*/


        return oDetailsService.getOrderDetails();


    }


    @Scheduled(fixedRate = 5000)
    public void testScheduler() {
        // System.out.println("************Sheduler **********"+ LocalDateTime.now()  );
        String token = this.getToken();

    }
}
