package com.vmc.crud.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "ORDERS_TBL")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Better for MySQL
    private Integer id; // Changed from int to Integer

    private String name;
    private Integer qty;
    private Double price;

    public Order(){

    }
    public Order(Integer id, String name, Integer qty, Double price) {
        this.id = id;
        this.name = name;
        this.qty = qty;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                '}';
    }
}