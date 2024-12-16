package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class OrderDTO {
    private int ordernum;
    private int orderusernum;
    private Timestamp orderdate;
    private String name;
    private String phone;
    private int servicetype;
    private int tire;
    private int quantity;

    public OrderDTO(Timestamp orderdate, int orderusernum, int servicetype, int tire, int quantity) {
        this.orderdate = orderdate;
        this.orderusernum = orderusernum;
        this.servicetype = servicetype;
        this.tire = tire;
        this.quantity = quantity;
    }

    public OrderDTO(Timestamp orderdate, int orderusernum, int servicetype) {
        this.orderdate = orderdate;
        this.orderusernum = orderusernum;
        this.servicetype = servicetype;
    }

    public OrderDTO(Timestamp orderdate, String name, String phone, int servicetype, int tire, int quantity) {
        this.orderdate = orderdate;
        this.name = name;
        this.phone = phone;
        this.servicetype = servicetype;
        this.tire = tire;
        this.quantity = quantity;
    }

    public OrderDTO(Timestamp orderdate, String name, String phone, int servicetype) {
        this.orderdate = orderdate;
        this.name = name;
        this.phone = phone;
        this.servicetype = servicetype;
    }

    public OrderDTO(int ordernum, int orderusernum, Timestamp orderdate, String name, String phone, int servicetype, int tire) {
        this.ordernum = ordernum;
        this.orderusernum = orderusernum;
        this.orderdate = orderdate;
        this.name = name;
        this.phone = phone;
        this.servicetype = servicetype;
        this.tire = tire;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public int getOrderusernum() {
        return orderusernum;
    }

    public void setOrderusernum(int orderusernum) {
        this.orderusernum = orderusernum;
    }

    public Timestamp getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Timestamp orderdate) {
        this.orderdate = orderdate;
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

    public int getServicetype() {
        return servicetype;
    }

    public void setServicetype(int servicetype) {
        this.servicetype = servicetype;
    }

    public int getTire() {
        return tire;
    }

    public void setTire(int tire) {
        this.tire = tire;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "ordernum=" + ordernum +
                ", orderusernum=" + orderusernum +
                ", orderdate=" + orderdate +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", servicetype=" + servicetype +
                ", tire=" + tire +
                ", quantity=" + quantity +
                '}';
    }
}