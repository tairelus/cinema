package com.bjs.cinema;

import com.bjs.TerminateException;
import com.bjs.visitor.Visitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by U-1 on 24.01.2017.
 */
public class Cinema implements OrderInterface {
    /**Value to quit program*/
    public static final String QUIT_CODE = "quit";

    /**Cinema name*/
    private String name;
    /**Array of the cinema halls*/
    private ArrayList<Hall> halls;
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
        Hall hall = new Hall(1, movie, 14, 22);
        hall.setPrice(1000);
        addHall(hall);

        movie = new Movie("xXx: The Return of Xander Cage", "USA", MovieType.THREE_DIMENSIONAL,
                new GregorianCalendar(2017, 01, 5), 16);
        hall = new Hall(2, movie, 14, 22);
        hall.setPrice(900);
        addHall(hall);

        movie = new Movie("Moana", "USA", MovieType.THREE_DIMENSIONAL, new GregorianCalendar(2016, 12, 14), 6);
        hall = new Hall(3, movie, 15, 23);
        hall.setPrice(600);
        addHall(hall);

        movie = new Movie("Live by Night", "USA", MovieType.TWO_DIMENSIONAL, new GregorianCalendar(2016, 12, 13), 18);
        hall = new Hall(4, movie, 15, 23);
        hall.setPrice(800);
        addHall(hall);

        movie = new Movie("La La Land", "USA", MovieType.TWO_DIMENSIONAL, new GregorianCalendar(2016, 8, 31), 16);
        hall = new Hall(5, movie, 15, 20);
        hall.setPrice(1000);
        addHall(hall);

        this.reader = reader;
    }

    /**
     * Helper. Provides reading positive integer
     * @return Valid integer value from keyboard or -1 to quit
     */
    public static int getIntegerValue(BufferedReader reader) throws TerminateException {
        int result = -1;
        try {
            result = Integer.parseInt(getStringValue(reader));
        } catch (TerminateException e) {
            throw e; //Pass terminate exception to the upper level
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
     * Helper. Provides reading string from keyboard
     * @return Input string
     */
    public static String getStringValue(BufferedReader reader) throws TerminateException {
        String result = null;

        try {
            result = reader.readLine();
            if (result.equals(QUIT_CODE)) {
                throw new TerminateException("Program terminated, bye!");
            }
        } catch (IOException e) {
            System.out.println("Input error: " + e.getMessage());
        }

        return result;
    }

    /**
     * Gets ticket for the current visitor
     * @param visitor Current visitor
     * @return Order object
     */
    public Order getOrder(Visitor visitor) throws TerminateException {
        Hall hall = selectHall();
        if (hall.IsHallFilled()) {
            System.out.println("Hall '" + hall.getId() + "' is filled. Please, select another movie/hall.\n");
            return getOrder(visitor);
        }

        int ageRestriction = hall.getMovie().getAgeRestriction();
        int visitorAge = visitor.getAge();
        if (visitorAge < ageRestriction) {
            System.out.println("Selected movie/hall has age restriction " + ageRestriction + " years old.");
            System.out.println("Your age is " + visitorAge + ". Please, select another movie/hall.\n");
            return getOrder(visitor);
        }

        Seat seat = selectSeat(hall);

        if (confirmOrder()) {
            hall.occupySeat(seat);
            return new Order(hall, seat);
        }

        return null;
    }

    /**
     * Select movie/hall
     * @return
     */
    public Hall selectHall() throws TerminateException {
        printMovieInfo();

        System.out.println("Please, chose movie/hall and enter hall id:");
        int id = getIntegerValue(reader);

        Hall result = getHall(id);
        while (result == null) {
            System.out.println("There is no hall with entered id = " + id + ". Please enter correct id:");
            id = getIntegerValue(reader);
            result = getHall(id);
        }

        return result;
    }

    /**
     * Select seat in the hall
     * @param hall
     * @return
     */
    public Seat selectSeat(Hall hall) throws TerminateException {
        hall.printSeats();
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
     * Asks for order confirmation
     */
    private boolean confirmOrder() throws TerminateException {
        final String YES_CODE = "yes";
        final String NO_CODE = "no";

        System.out.println("Please, type '" + YES_CODE + "' to confirm order or '" + NO_CODE  + "' to reject.");
        String value = getStringValue(reader);
        if (value.equals(YES_CODE)) {
            return true;
        } else if (value.equals(NO_CODE)) {
            return  false;
        } else {
            confirmOrder();
        }

        return false;
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
