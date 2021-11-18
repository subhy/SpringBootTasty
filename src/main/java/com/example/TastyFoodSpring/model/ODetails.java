package com.example.TastyFoodSpring.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="odetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ODetails {

    @Id
    private int sno;  //Sequential No with Auto increment
    private String batchcode; //Batch Code ddmmyyyyhhmmss
    private String receiptdate; //Receipt Date DD/MM/YYYY
    private String receipttime; //Receipt Time HH:MM:SS
    private int receiptno; //Receipt No
    private int itemno;  //Item counting no
    private String icode;
    private String idesc;
    private double qty;
    private double uprice;
    private double salestaxrate;  //Sales Tax Rate


    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getBatchcode() {
        return batchcode;
    }

    public void setBatchcode(String batchcode) {
        this.batchcode = batchcode;
    }

    public String getReceiptdate() {
        return receiptdate;
    }

    public void setReceiptdate(String receiptdate) {
        this.receiptdate = receiptdate;
    }

    public String getReceipttime() {
        return receipttime;
    }

    public void setReceipttime(String receipttime) {
        this.receipttime = receipttime;
    }

    public int getReceiptno() {
        return receiptno;
    }

    public void setReceiptno(int receiptno) {
        this.receiptno = receiptno;
    }

    public int getItemno() {
        return itemno;
    }

    public void setItemno(int itemno) {
        this.itemno = itemno;
    }

    public String getIcode() {
        return icode;
    }

    public void setIcode(String icode) {
        this.icode = icode;
    }

    public String getIdesc() {
        return idesc;
    }

    public void setIdesc(String idesc) {
        this.idesc = idesc;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getUprice() {
        return uprice;
    }

    public void setUprice(double uprice) {
        this.uprice = uprice;
    }

    public double getSalestaxrate() {
        return salestaxrate;
    }

    public void setSalestaxrate(double salestaxrate) {
        this.salestaxrate = salestaxrate;
    }
}
