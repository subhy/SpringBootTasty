package com.example.TastyFoodSpring.dto.payloadDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrimaryPayload {
    String AppCode;
    String PropertyCode;
    String ClientID;
    String ClientSecret;
    String POSInterfaceCode;
    String BatchCode;
    List<PosSalesPayload> PosSales;

    public String getAppCode() {
        return AppCode;
    }

    public void setAppCode(String appCode) {
        AppCode = appCode;
    }

    public String getPropertyCode() {
        return PropertyCode;
    }

    public void setPropertyCode(String propertyCode) {
        PropertyCode = propertyCode;
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public String getClientSecret() {
        return ClientSecret;
    }

    public void setClientSecret(String clientSecret) {
        ClientSecret = clientSecret;
    }

    public String getPOSInterfaceCode() {
        return POSInterfaceCode;
    }

    public void setPOSInterfaceCode(String POSInterfaceCode) {
        this.POSInterfaceCode = POSInterfaceCode;
    }

    public String getBatchCode() {
        return BatchCode;
    }

    public void setBatchCode(String batchCode) {
        BatchCode = batchCode;
    }

    public List<PosSalesPayload> getPosSales() {
        return PosSales;
    }

    public void setPosSales(List<PosSalesPayload> posSales) {
        this.PosSales = posSales;
    }
}
