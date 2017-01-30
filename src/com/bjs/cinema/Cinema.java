package com.bjs.cinema;

import com.bjs.visitor.Visitor;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by U-1 on 24.01.2017.
 */
public class Cinema implements TicketOrder {
    /**Value to quit program*/
    public static final String QUIT_CODE = "quit";

    /**Cinema name*/
    private String name;
    /**Array of the cinema halls*/
    private ArrayList<Hall> halls;
    /**Information about current visitor*/
    private Visitor visitor;
    /**Reader object to read input from keyboard*/
    BufferedReader reader;

    /**
     * Construct cinema object by name
     * @param name Cinema name
     * @param reader Reader object
     */
    public Cinema(String name, BufferedReader reader) {
        this.name = name;
        halls = new ArrayList<>();

        Movie movie = new Movie("Assassin's Creed", "USA, UK, France, Hong Kong", MovieType.THREE_DIMENSIONAL,
                new GregorianCalendar(2016, 12, 14), 16);
        addHall(new Hall(1, movie, 14, 22));

        movie = new Movie("xXx: The Return of Xander Cage", "USA", MovieType.THREE_DIMENSIONAL,
                new GregorianCalendar(2017, 01, 5), 16);
        addHall(new Hall(2, movie, 14, 22));

        movie = new Movie("Moana", "USA", MovieType.THREE_DIMENSIONAL, new GregorianCalendar(2016, 12, 14), 6);
        addHall(new Hall(3, movie, 15, 23));

        movie = new Movie("Live by Night", "USA", MovieType.TWO_DIMENSIONAL, new GregorianCalendar(2016, 12, 13), 18);
        addHall(new Hall(4, movie, 15, 23));

        movie = new Movie("La La Land", "USA", MovieType.TWO_DIMENSIONAL, new GregorianCalendar(2016, 8, 31), 16);
        addHall(new Hall(5, movie, 15, 20));

        this.reader = reader;
    }

    /**
     * Helper. Provides reading positive integer
     * @return Valid integer value from keyboard or -1 to quit
     */
    public static int getIntegerValue (BufferedReader reader) {
        int result = -1;

        try {
            String value = reader.readLine();
            if (value.equals(QUIT_CODE)) {
                System.out.println("Program terminated, bye!");
                return result;
            }

            result = Integer.parseInt(value);
        } catch (Exception e) { //Base exception class to cover all cases
            System.out.println("Entered value is invalid. Error: " + e.getMessage());
        }

        if (result <= 0) {
            System.out.println("Entered value must be a number > 0. Enter new one:");
            return getIntegerValue (reader);
        }

        return result;
    }

    /**
     * Select movie/hall
     * @return
     */
    public Hall selectHall(Visitor visitor) {
        printMovieInfo();

        System.out.println("Please, chose movie/hall and enter hall id:");
        int id = getIntegerValue(reader);

        Hall result = getHall(id);
        while (result == null) {
            System.out.println("There is no hall with entered id = " + id + ". Please enter correct id:");
            id = getIntegerValue(reader);
            result = getHall(id);
        }

        int ageRestriction = result.getMovie().getAgeRestriction();
        int visitorAge = visitor.getAge();
        if (visitorAge < ageRestriction) {
            System.out.println("Selected movie/hall has age restriction " + ageRestriction + " years old.");
            System.out.println("Your age is " + visitorAge + ". Please, select another movie/hall.\n");

            return selectHall(visitor);
        }

        return result;
    }

    /**
     * Select seat in the hall
     * @param hall
     * @return
     */
    public Seat selectSeat(Hall hall) {
        hall.printSeats();

        if (hall.IsHallFilled()) {
            System.out.println("Hall '" + hall.getId() + "' is filled.");
            return null;
        }

        System.out.println("Please, select desired seat in the '" + hall.getId() + "' hall");

        System.out.println("Enter line number:");
        int line = getIntegerValue(reader);
        while (line > hall.getLinesNumber()) {
            System.out.println("There is no entered line number = " + line + ". Please, enter correct line:");
            line = getIntegerValue(reader);
        }

        System.out.println("Enter seat number:");
        int seat = getIntegerValue(reader);
        while (seat > hall.getSeatsNumber()) {
            System.out.println("There is no entered seat number = " + seat + ". Please, enter correct seat:");
            seat = getIntegerValue(reader);
        }

        if (hall.IsOccupied(line, seat)) {
            System.out.println("Selected seat (" + line + ", " + seat + ") is already occupied. " +
                    "Please, select another seat.");
            return selectSeat(hall);
        }

        return new Seat(line, seat);
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

    /**
     * Adds hall object to the list
     * @param hall Hall object to add
     */
    private void addHall(Hall hall) {
        this.halls.add(hall);
    }

    /**
     * Prints information about movies in this cinema
     */
    private void printMovieInfo() {
        System.out.printf("%-8s %-4s %s\n", "Hall", "Age", "Movie Title");
        for (Hall item: halls) {
            Movie movie = item.getMovie();
            System.out.printf("%-8s %-4s %s\n", item.getId(), movie.getAgeRestriction(), movie.getTitle());
        }

        System.out.println();
    }
}
