package com.example.request;

public class Model {
    String Status,address,city,landmark,name,phone,state,wastetype,zip;

    public Model() {
    }

    public Model(String status, String address, String city, String landmark, String name, String phone, String state, String wastetype, String zip) {
        Status = status;
        this.address = address;
        this.city = city;
        this.landmark = landmark;
        this.name = name;
        this.phone = phone;
        this.state = state;
        this.wastetype = wastetype;
        this.zip = zip;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWastetype() {
        return wastetype;
    }

    public void setWastetype(String wastetype) {
        this.wastetype = wastetype;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
