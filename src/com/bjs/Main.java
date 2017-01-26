package com.bjs;

import com.bjs.cinema.Cinema;
import com.bjs.cinema.Hall;
import com.bjs.cinema.Seat;
import com.bjs.cinema.Ticket;
import com.bjs.visitor.Visitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        Cinema cinema = Cinema.getCinema();
        cinema.printMovieInfo();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Hall hall = getHall(reader, cinema);
        Visitor visitor = getVisitor(reader);

        hall.printSeats();
        Seat seat = getSeat(reader, hall);

        Ticket ticket = getTicket(visitor, hall, seat, 10000);
    }

    /**
     * Check restrictions, check seat, proceed ticket order
     * @param visitor
     * @param hall
     * @param seat
     * @param price
     * @return
     */
    public static Ticket getTicket(Visitor visitor, Hall hall, Seat seat, int price) {
        if (!visitor.checkAge(hall.getMovie())) {
            System.out.println("Your age does not correspond to the movie restriction. Select another movie/hall");
            return null;
        }

        if (!hall.occupySeat(seat)) {
            System.out.println("Selected seat (" + seat.getLine() + ", " + seat.getSeat() + ") is already occupied. " +
                    "Please, select another seat.");
            return null;
        }

        System.out.println("Ticket order completed successfully. Have a nice view :-)!");
        visitor.setTicket(new Ticket(hall, seat, price));
        return visitor.getTicket();
    }

    /**
     * Select seat in the hall
     * @param reader
     * @param hall
     * @return
     */
    public static Seat getSeat(BufferedReader reader, Hall hall) {
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

        return new Seat(line, seat);
    }

    /**
     * Select movie/hall
     * @param reader
     * @param cinema
     * @return
     */
    public static Hall getHall(BufferedReader reader, Cinema cinema) {
        System.out.println("Please, chose movie/hall and enter hall id:");
        int id = getIntegerValue(reader);

        Hall result = cinema.getHall(id);
        while (result == null) {
            System.out.println("There is no hall with entered id = " + id + ". Please enter correct id:");
            id = getIntegerValue(reader);
            result = cinema.getHall(id);
        }

        System.out.println("Selected movie/hall has age restriction " + result.getMovie().getAgeRestriction() + " years old");

        return result;
    }

    /**
     * Create visitor
     * @param reader
     * @return
     */
    public static Visitor getVisitor(BufferedReader reader) {
        System.out.println("Enter your age:");
        int age = getIntegerValue(reader);
        return new Visitor(age);
    }

    /**
     * Helper. Provides reading positive integer
     * @param reader
     * @return
     */
    private static int getIntegerValue (BufferedReader reader) {
        int result = 0;
        while (result <= 0) {
            try {
                result = Integer.parseInt(reader.readLine());
            } catch (Exception e) { //Base exception class to cover all cases
                System.out.println("Entered value is invalid. Error: " + e.getMessage());
            }

            if (result <= 0) {
                System.out.println("Entered value must be a number > 0. Enter new one:");
            }
        }

        return result;
    }
}
