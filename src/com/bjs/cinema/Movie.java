package com.bjs.cinema;
import java.util.Calendar;

/**
 * Created by U-1 on 24.01.2017.
 * Presents information about movie
 */
public class Movie {
    /**Movie title*/
    private String title;
    /**Country where movie have been created*/
    private String country;
    /**Producer name*/
    private String producer;
    /**Movie technology, e.g. 2D, 3D*/
    private MovieType type;
    /**Date of movie release*/
    private Calendar releaseDate;
    /**Age restriction*/
    private int ageRestriction;

    public Movie(String title, String country, MovieType type, Calendar releaseDate, int ageRestriction) {
        this.title = title;
        this.country = country;
        this.type = type;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public MovieType getType() {
        return type;
    }

    public Calendar getReleaseDate() {
        return releaseDate;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }
}
