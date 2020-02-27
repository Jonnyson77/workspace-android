package com.java.demo.child;

public class Child {
    private int age;
    private String name;

    public Child(){};
    public Child(String name, int age){
        this.age = age;
        this.name = name;
    }
    public Child(String name){
        this.name = name;
    }
    public Child(int age){
        this.age =age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


