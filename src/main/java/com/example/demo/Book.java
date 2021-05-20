package com.example.demo;

public class Book {
    private String name;
    private String publisher;
    private int amount;
    public Book(){

    }
    public Book(String name,String publisher,int amount){
        this.name = name;
        this.publisher = publisher;
        this.amount = amount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }
}
