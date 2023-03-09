package com.du.bookServer.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Book implements Serializable {
    private int id;
    private String name;
    private String author;
    private double price;
    private int inventory;
    private int source;
}
