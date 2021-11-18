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
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ODetailsServiceImpl implements ODetailsService {

    @Autowired
    ODetailsReporsitory oDetailsReporsitory;


    List<ItemPayload> itemPayloadList = new ArrayList<>();


    @Override
    public String getOrderDetails() {

        List<Integer> orderIdList = oDetailsReporsitory.findDistinctByReceiptno();
        List<PosSalesPayload> posSalesPayloads = new ArrayList<>();

        System.out.println(orderIdList);
        System.out.println("*******************");

        // List<ODetails> oDetailsByOrderId = new ArrayList<>(); // All Data will be iterated

        PrimaryPayload primaryPayload = new PrimaryPayload();
        primaryPayload.setAppCode("POS-02");
        primaryPayload.setPropertyCode("CCB1");
        primaryPayload.setClientID("CCB1-PS-21-00000018");
        primaryPayload.setClientSecret("e4gUOM7nEhCfJAmOMkxrhg==");
        primaryPayload.setPOSInterfaceCode("CCB1-PS-21-00000018");


        for (int i : orderIdList) {
            List<ODetails> oDetailsByOrderId1 = oDetailsReporsitory.findODetailsByReceiptno(i); //Get Data from Same orderId
            System.out.println(oDetailsByOrderId1);
            List<ItemPayload> itemPayloadViaOrder = new ArrayList<>();

            double paymentAmount = 0;

            for (ODetails oDetails : oDetailsByOrderId1) {
                ItemPayload itemPayload = new ItemPayload();
                itemPayload.setItemDesc(oDetails.getIdesc());
                itemPayload.setItemDiscountAmt("0");
                double amount = oDetails.getUprice() * oDetails.getQty();
                itemPayload.setItemAmt(Double.toString(amount));
                paymentAmount += amount;
                itemPayloadViaOrder.add(itemPayload);
            }

            PosSalesPayload posSalesPayload = new PosSalesPayload();
            posSalesPayload.setReceiptNo("R" + oDetailsByOrderId1.get(0).getReceiptno());
            posSalesPayload.setReceiptDate(oDetailsByOrderId1.get(0).getReceiptdate());
            posSalesPayload.setReceiptTime(oDetailsByOrderId1.get(0).getReceipttime());
            posSalesPayload.setPaymentAmt(Double.toString(paymentAmount));
            posSalesPayload.setPropertyCode("CCB1");
            posSalesPayload.setPOSInterfaceCode("CCB1-PS-21-00000018");
            posSalesPayload.setNoOfItems(Integer.toString(oDetailsByOrderId1.size()));
            posSalesPayload.setSalesCurrency("LKR");
            posSalesPayload.setTotalSalesAmtB4Tax(String.format("%.2f", paymentAmount));
            posSalesPayload.setTotalSalesAmtAfterTax(String.format("%.2f", paymentAmount));
            posSalesPayload.setSalesTaxRate("0");
            posSalesPayload.setServiceChargeAmt(String.format("%.2f", paymentAmount));
            posSalesPayload.setPaymentCurrency("LKR");
            posSalesPayload.setPaymentMethod("Cash");
            posSalesPayload.setSalesType("Sales");
            posSalesPayload.setItems(itemPayloadViaOrder);

            posSalesPayloads.add(posSalesPayload);
            primaryPayload.setBatchCode(oDetailsByOrderId1.get(0).getBatchcode());

            primaryPayload.setPosSales(posSalesPayloads);


        }

        System.out.println(primaryPayload);
        Logger.getLogger("com.example.TastyFoodSpring").log(Level.SEVERE, primaryPayload+"AAAAAA");


        String result = new Gson().toJson(primaryPayload);


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

    @Override
    public void deleteSentData() {
        oDetailsReporsitory.deleteAll();
    }


}
