package com.example.dashboard.ui.model;

public class Employee {
    private String name;
    private int age;
    private boolean overtimeWork;
    private double salary;
    private String boundary;

    public Employee(String name, int age, boolean overtimeWork, double salary, String boundary) {
        this.name = name;
        this.age = age;
        this.overtimeWork = overtimeWork;
        this.salary = salary;
        this.boundary = boundary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isOvertimeWork() {
        return overtimeWork;
    }

    public double getSalary() {
        return salary;
    }

    public String getBoundary() {
        return boundary;
    }
}

