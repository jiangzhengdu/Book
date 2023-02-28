package com.du.book.RESTful.model;

import lombok.Data;

@Data
public class Book {
    private int id;
    private String name;
    private double price;
    private int inventory;
}
