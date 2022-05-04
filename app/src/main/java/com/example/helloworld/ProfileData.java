package com.example.helloworld;

public class ProfileData {
    int age;
    String name;
    String occupation;
    String description;

    public ProfileData(int age, String name, String occupation, String description){
        this.age = age;
        this.name = name;
        this.occupation = occupation;
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getDescription() {
        return description;
    }
}
