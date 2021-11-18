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

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


@Service
@EnableScheduling
public class NetworkCallsImpl implements NetworkCalls {



    //Logger
    Logger rootLogger = Logger.getLogger("");
    FileHandler fileHandler;

    {
        try {
            fileHandler = new FileHandler("SBerror.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            rootLogger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






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

        if( responsePayload.getReturnStatus().equals("FAIL") ){
            System.out.println("Fail");
        }else {
            oDetailsService.deleteSentData();
        }

        System.out.println(result);


        //return jsonParsedPayload;
        return result;

    }

    // @Override
    private String postItemData() {
        return oDetailsService.getOrderDetails();


    }


    @Scheduled(fixedRate = 900000)
    public void testScheduler() {
        // System.out.println("************Sheduler **********"+ LocalDateTime.now()  );
        String token = this.getToken();

    }
}
