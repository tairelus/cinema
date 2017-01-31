package com.bjs.cinema;

/**
 * Created by U-1 on 24.01.2017.
 * Class describes ticket for seat in the cinema hall
 */
public class Ticket {
    /**Cinema hall*/
    private Hall hall;
    /**Current seat in the hall*/
    private Seat seat;

    /**Ticket identifier*/
    private String ticketId;
    /**Ticket price in cents*/
    private int price;

    public Ticket(Hall hall, Seat seat) {
        this.hall = hall;
        this.seat = seat;
        this.price = hall.getPrice();
        this.ticketId =  Integer.toString(hashCode());
    }

    public String getTicketId() {
        return ticketId;
    }

    public Seat getSeat() {
        return seat;
    }
}
