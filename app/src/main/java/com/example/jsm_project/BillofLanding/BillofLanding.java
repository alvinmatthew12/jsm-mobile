package com.example.jsm_project.Container;

import java.util.Date;

public class BillofLanding {
    public String billoflandingNo, shippingID, shippingDate, dateofReceipt, customerID, status;
    public int id;

    public BillofLanding(int id,  String billoflandingNo, String shippingDate, String dateofReceipt, String shippingID, String customerID, String status) {
        this.id = id;
        this.shippingDate = shippingDate;
        this.dateofReceipt = dateofReceipt;
        this.billoflandingNo = billoflandingNo;
        this.shippingID = shippingID;
        this.customerID = customerID;
        this.status = status;
    }

    public String getBilloflandingNo() {
        return billoflandingNo;
    }

    public void setBilloflandingNo(String billoflandingNo) {
        this.billoflandingNo = billoflandingNo;
    }

    public String getShippingID() {
        return shippingID;
    }

    public void setShippingID(String shippingID) {
        this.shippingID = shippingID;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getDateofReceipt() {
        return dateofReceipt;
    }

    public void setDateofReceipt(String dateofReceipt) {
        this.dateofReceipt = dateofReceipt;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
