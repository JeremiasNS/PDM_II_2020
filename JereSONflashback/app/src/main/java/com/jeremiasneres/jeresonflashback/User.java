
package com.jeremiasneres.jeresonflashback;



public class User {

    private String name;
    private String designation;
    private String location;

    public User() {
    }

    public User(String name, String designation, String location) {
        super();
        this.name = name;
        this.designation = designation;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
