package com.bjs.cinema;

/**
 * Created by U-1 on 24.01.2017.
 * Present seat in the cinema hall
 */
public class Seat {
    /**Line number (vertical coordinate) of the current seat*/
    private int line;
    /**Seat number (horizontal coordinate) of the current seat*/
    private int seat;
    /**Optional seat description ('sofa', 'vip', etc.)*/
    private String option;

    public Seat(int line, int seat) {
        this.line = line;
        this.seat = seat;
    }

    public int getLine() {
        return line;
    }

    public int getSeat() {
        return seat;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
