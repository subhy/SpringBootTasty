package com.example.TastyFoodSpring.dto.payloadDTO;

public class ItemPayload {
    String ItemDesc;
    String ItemAmt;
    String  ItemDiscountAmt;

    public String getItemDesc() {
        return ItemDesc;
    }

    public void setItemDesc(String itemDesc) {
        ItemDesc = itemDesc;
    }

    public String getItemAmt() {
        return ItemAmt;
    }

    public void setItemAmt(String itemAmt) {
        ItemAmt = itemAmt;
    }

    public String getItemDiscountAmt() {
        return ItemDiscountAmt;
    }

    public void setItemDiscountAmt(String itemDiscountAmt) {
        ItemDiscountAmt = itemDiscountAmt;
    }
}
