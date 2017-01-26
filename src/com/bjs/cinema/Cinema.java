package com.bjs.cinema;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by U-1 on 24.01.2017.
 */
public class Cinema {
    /**Cinema name*/
    private String name;
    /**Array of the cinema halls*/
    private ArrayList<Hall> halls;

    private Cinema(String name) {
        this.name = name;
        halls = new ArrayList<>();
    }

    /**
     * Provides universal way to initialize cinema object
     * @return
     */
    public static Cinema getCinema() {
        Cinema result = new Cinema("Butterfly Country");

        Movie movie = new Movie("Assassin's Creed", "USA, UK, France, Hong Kong", MovieType.THREE_DIMENSIONAL,
                new GregorianCalendar(2016, 12, 14), 16);
        result.addHall(new Hall(1, movie, 14, 22));

        movie = new Movie("xXx: The Return of Xander Cage", "USA", MovieType.THREE_DIMENSIONAL,
                new GregorianCalendar(2017, 01, 5), 16);
        result.addHall(new Hall(2, movie, 14, 22));

        movie = new Movie("Moana", "USA", MovieType.THREE_DIMENSIONAL, new GregorianCalendar(2016, 12, 14), 6);
        result.addHall(new Hall(3, movie, 15, 23));

        movie = new Movie("Live by Night", "USA", MovieType.TWO_DIMENSIONAL, new GregorianCalendar(2016, 12, 13), 18);
        result.addHall(new Hall(4, movie, 15, 23));

        movie = new Movie("La La Land", "USA", MovieType.TWO_DIMENSIONAL, new GregorianCalendar(2016, 8, 31), 16);
        result.addHall(new Hall(5, movie, 15, 20));

        return result;
    }

    /**
     * Search hall by identifier
     * @param id
     * @return
     */
    public Hall getHall(int id) {
        for (Hall item: halls) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    public void printMovieInfo() {
        System.out.printf("%-8s %s\n", "Hall ID", "Movie Title");
        for (Hall item: halls) {
            System.out.printf("%-8s %s\n", item.getId(), item.getMovie().getTitle());
        }

        System.out.println();
    }

    public void addHall(Hall hall) {
        this.halls.add(hall);
    }
}
