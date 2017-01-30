package com.bjs.cinema;

import com.bjs.visitor.Visitor;

/**
 * Created by U-1 on 30.01.2017.
 * Describes actions required to solve ticket.
 */
public interface TicketOrder {
    /**
     * Select movie/hall
     * @return
     */
    Hall selectHall(Visitor visitor);

    /**
     * Select seat in the hall
     * @param hall
     * @return
     */
    Seat selectSeat(Hall hall);


}
