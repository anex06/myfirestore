package com.dextrosoft.firestore;

public class User {
    public String name,mobile,address,email;

    public User() {
    }

    public User(String name, String mobile, String address, String email) {
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
}
