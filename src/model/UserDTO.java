package model;

import javax.swing.*;

public class UserDTO {
    private int usernum;
    private String id;
    private String name;
    private String phonenumber;
    private String address;
    private String password;
    private String role;
    private String car;


    public UserDTO(String id, String password, String role, int usernum) {
        this.id = id;
        this.password = password;
        this.role = role;
        this.usernum = usernum;
    }


    public UserDTO(String id, String name, String phonenumber, String address, String password, String car) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
        this.address = address;
        this.password = password;
        this.car = car;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUsernum() {
        return usernum;
    }

    public void setUsernum(int usernum) {
        this.usernum = usernum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCar() {
        return car;
    }


    public void setCar(String car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "usernum=" + usernum +
                ", name='" + name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", car='" + car + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
