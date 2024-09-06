package com.prooktatas.traindriverdb;

import java.util.HashSet;

/**
 *
 * @author KomlosyT
 */
public class TD {
    private String lastName;
    private String firstName;
    private int age;
    private String category;
    private String type;
    private String line;

    
    public TD(){
        
    }
    
    public TD(String lastName, String firstName, int age, String category, String type, String line) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.category = category;
        this.type = type;
        this.line = line;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getLine() {
        return line;
    }



        @Override
    public String toString() {
    return "Mozdonyvezető:" + 
           "\n vezeteknev: " + this.lastName +
           ",\n keresztnev: " + this.firstName +
           ",\n kor: " + this.age +
           ",\n kategoria: " + this.category +
           ",\n tipusok: " + this.type +
           ",\n vonalak: " + this.line;
    }
}