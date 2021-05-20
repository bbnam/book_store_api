package com.example.demo;

public class Messger {
    private String mess;

    public Messger(){

    }

    public Messger(String mess){
        this.mess = mess;
    }
    public String getMess() {
        return mess;
    }
    public void setMess(String mess) {
        this.mess = mess;
    }
    @Override
    public String toString() {
        return new StringBuffer("Messeger : ").append(this.mess).toString();
    }
}
