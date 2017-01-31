package com.bjs.cinema;

import com.bjs.TerminateException;
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
    Hall selectHall() throws TerminateException;

    /**
     * Select seat in the hall
     * @param hall
     * @return
     */
    Seat selectSeat(Hall hall) throws TerminateException;

    /**
     * Gets ticket for the current visitor
     * @param visitor Current visitor
     * @return Ticket object
     */
    Ticket getTicket(Visitor visitor) throws TerminateException;
}
