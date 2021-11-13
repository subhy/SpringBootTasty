package com.example.TastyFoodSpring.service.impl;

import com.example.TastyFoodSpring.dto.payloadDTO.ItemPayload;
import com.example.TastyFoodSpring.dto.payloadDTO.PosSalesPayload;
import com.example.TastyFoodSpring.dto.payloadDTO.PrimaryPayload;
import com.example.TastyFoodSpring.model.ODetails;
import com.example.TastyFoodSpring.repository.ODetailsReporsitory;
import com.example.TastyFoodSpring.service.ODetailsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ODetailsServiceImpl implements ODetailsService {

    @Autowired
    ODetailsReporsitory oDetailsReporsitory;



    List<ItemPayload> itemPayloadList = new ArrayList<>();


    @Override
    public String getOrderDetails() {

        List<Integer> orderIdList = oDetailsReporsitory.findDistinctByOrderId();
        List<PosSalesPayload> posSalesPayloads = new ArrayList<>();

        System.out.println(orderIdList);
        System.out.println("*******************");

        // List<ODetails> oDetailsByOrderId = new ArrayList<>(); // All Data will be iterated

        PrimaryPayload primaryPayload = new PrimaryPayload();


        for (int i : orderIdList) {
            List<ODetails> oDetailsByOrderId1 = oDetailsReporsitory.findODetailsByOrderId(i); //Get Data from Same orderId
            System.out.println(oDetailsByOrderId1);
            List<ItemPayload> itemPayloadViaOrder = new ArrayList<>();

            double paymentAmount = 0;

            for (ODetails oDetails : oDetailsByOrderId1) {
                ItemPayload itemPayload = new ItemPayload();
                itemPayload.setItemDesc(oDetails.getiName());
                itemPayload.setItemDiscountAmt("0");
                double amount = oDetails.getuPrice() * oDetails.getQty();
                itemPayload.setItemAmt(Double.toString(amount));
                paymentAmount += amount;
                itemPayloadViaOrder.add(itemPayload);
            }

            PosSalesPayload posSalesPayload = new PosSalesPayload();
            posSalesPayload.setReceiptNo(Integer.toString(oDetailsByOrderId1.get(0).getOrderId()));
            posSalesPayload.setReceiptNo(oDetailsByOrderId1.get(0).getOrderDate().toString());
            posSalesPayload.setReceiptNo(oDetailsByOrderId1.get(0).getOrderDate().toString());
            posSalesPayload.setPaymentAmt(Double.toString(paymentAmount));
            posSalesPayload.setPropertyCode("CCB1");
            posSalesPayload.setPOSInterfaceCode("CCB1-PS-21-00000018");
            posSalesPayload.setReceiptTime("12:21");
            posSalesPayload.setNoOfItems(Integer.toString(oDetailsByOrderId1.size()));
            posSalesPayload.setSalesCurrency("LKR");
            posSalesPayload.setTotalSalesAmtB4Tax(Double.toString(paymentAmount));
            posSalesPayload.setTotalSalesAmtAfterTax(Double.toString(paymentAmount));
            posSalesPayload.setSalesTaxRate("0");
            posSalesPayload.setServiceChargeAmt("0");
            posSalesPayload.setPaymentCurrency("LKR");
            posSalesPayload.setPaymentMethod("Cash");
            posSalesPayload.setSalesType("Sales");
            posSalesPayload.setItems(itemPayloadViaOrder);

            posSalesPayloads.add(posSalesPayload);

            primaryPayload.setPosSales(posSalesPayloads);

        }

        System.out.println(primaryPayload);

        primaryPayload.setAppCode("CCB!");
        primaryPayload.setBatchCode("CCB1");
        primaryPayload.setClientID("huhweg");
        primaryPayload.setClientSecret("rgrg4");

        String result = new Gson().toJson(primaryPayload);


        //return primaryPayload.toString();
        return result;
    }

    @Override
    public List<Integer> getDistinctOrders() {
        return null;
    }

    @Override
    public List<ODetails> getODetailsByOrderId(int orderid) {
        return null;
    }


}
