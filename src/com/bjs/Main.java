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
        System.out.println("Type '" + Cinema.QUIT_CODE + "' to terminate program.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Visitor visitor = getVisitor(reader);
        if (visitor == null) {
            return;
        }

        Cinema cinema = new Cinema("Butterfly Country", reader);
        Hall hall = cinema.selectHall(visitor);
        if (hall == null) {
            return;
        }

        Seat seat = cinema.selectSeat(hall);
        if (seat == null) {
            return;
        }
    }

    /**
     * Create and store current visitor
     */
    public static Visitor getVisitor(BufferedReader reader) {
        System.out.println("Hello! Please, enter your age:");
        int retVal = Cinema.getIntegerValue(reader);
        if (retVal == -1) {
            return null;
        }

        return new Visitor(retVal);
    }

    /*public static Ticket getTicket(Visitor visitor, Hall hall, Seat seat, int price) {
        if (!visitor.checkAge(hall.getMovie())) {
            System.out.println("Your age does not correspond to the movie restriction. Select another movie/hall");
            return null;
        }

        System.out.println("Ticket order completed successfully. Have a nice view :-)!");
        visitor.setTicket(new Ticket(hall, seat, price));
        return visitor.getTicket();
    }*/


}
