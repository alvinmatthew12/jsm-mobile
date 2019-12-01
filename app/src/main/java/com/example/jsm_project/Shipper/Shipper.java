package com.example.jsm_project.Shipper;

public class Shipper {
    public String shipperName, shipperAddress, shipperContact, shipperCity, shipperCountry;
    public int id;

    public Shipper (int id, String shipperName, String shipperAddress, String shipperContact, String shipperCity, String shipperCountry) {
        this.id = id;
        this.shipperName = shipperName;
        this.shipperAddress = shipperAddress;
        this.shipperContact = shipperContact;
        this.shipperCity = shipperCity;
        this.shipperCountry = shipperCountry;
    }

    public String getShipperName() {
        return shipperName;
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public String getShipperContact() {
        return shipperContact;
    }

    public String getShipperCity() {
        return shipperCity;
    }

    public String getShipperCountry() {
        return shipperCountry;
    }

    public int getId() {
        return id;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }

    public void setShipperContact(String shipperContact) {
        this.shipperContact = shipperContact;
    }

    public void setShipperCity(String shipperCity) {
        this.shipperCity = shipperCity;
    }

    public void setShipperCountry(String shipperCountry) {
        this.shipperCountry = shipperCountry;
    }

    public void setId(int id) {
        this.id = id;
    }

}
