package com.du.bookServer.bookRPCAPI.book.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDomain implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private Integer inventory;

    public BookDomain(int id, String name, double price, int inventory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
    }
}
