package com.du.bookServer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private int id;
    private String name;
    private String author;
    private double price;
    private int inventory;
    private int source;
}
