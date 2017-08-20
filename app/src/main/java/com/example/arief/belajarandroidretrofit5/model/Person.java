package com.example.arief.belajarandroidretrofit5.model;

/**
 * Created by Arief on 8/19/2017.
 */

public class Person {

    private int id;
    private String name;

    public Person(){}

    public Person(int id,String name){
        this.id=id;
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
