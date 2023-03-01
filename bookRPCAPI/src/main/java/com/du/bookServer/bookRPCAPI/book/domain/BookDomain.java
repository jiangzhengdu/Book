package com.du.bookServer.bookRPCAPI.book.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class BookDomain implements Serializable {
    private Integer id;
    private String name;
    private Double price;

    private String author;
    private Integer inventory;

    private int source;

    public BookDomain(int id, String name, String author,double price, int inventory, int source) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.inventory = inventory;
        this.source = source;
    }
}
