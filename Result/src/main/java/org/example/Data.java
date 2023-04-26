package org.example;

public class Data {
    private String name;
    private int id;  // 72
    private long number;

    Data(String name, int id, long number){
        this.name = name;
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public long getNumber() {
        return number;
    }
}
