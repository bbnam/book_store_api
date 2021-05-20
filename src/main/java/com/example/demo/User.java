package com.example.demo;

import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private List<Book> book;
    public User(){

    }
    public User(String name, String email){
        this.username = name;

        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
