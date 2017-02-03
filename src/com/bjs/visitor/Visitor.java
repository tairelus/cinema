package com.bjs.visitor;

import com.bjs.cinema.Movie;
import com.bjs.cinema.Order;

/**
 * Created by U-1 on 24.01.2017.
 */
public class Visitor {
    /**Visitor's first name*/
    private String firstName;
    /**Visitor's secont name*/
    private String secondName;
    /**Visitor's age*/
    private int age;
    /**Visitor's identity card*/
    private String idCard;

    /**
     * Use this version while personal visit
     * @param age
     */
    public Visitor(int age) {
        this.age = age;
    }

    /**
     * Use this version for online booking
     * @param age
     * @param firstName
     * @param secondName
     */
    public Visitor(int age, String firstName, String secondName) {
        this(age); //Overloaded constructor call. Must be the first statement
        this.firstName = firstName;
        this.secondName = secondName;
    }

    /**
     * Checks whether visitor's age is allowed
     * @param movie
     * @return True if age is allowed
     */
    public boolean checkAge(Movie movie) {
        return age >= movie.getAgeRestriction();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
