package com.bjs.cinema;

import com.bjs.visitor.Visitor;

/**
 * Created by U-1 on 24.01.2017.
 * Class describes ticket for seat in the cinema hall
 */
public class Order {
    /**Cinema hall*/
    private Hall hall;
    /**Current seat in the hall*/
    private Seat seat;
    /**Order identifier*/
    private String orderId;
    /**Order price in cents*/
    private int price;
    /**Information about current visitor*/
    private Visitor visitor;

    public Order(Hall hall, Seat seat) {
        this.hall = hall;
        this.seat = seat;
        this.price = hall.getPrice();
        this.orderId =  Integer.toString(hashCode());
    }

    public String getOrderId() {
        return orderId;
    }

    public Seat getSeat() {
        return seat;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
