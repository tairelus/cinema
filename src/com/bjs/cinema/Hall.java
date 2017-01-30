package com.bjs.cinema;

/**
 * Created by U-1 on 24.01.2017.
 * Presents information about cinema hall
 */
public class Hall {
    /**Cinema hall identifier*/
    private int id;
    /**Array of all seats in the current hall*/
    Seat[][] seatsArray;
    /**Movie shown in this hall*/
    Movie movie;

    public Hall(int id, Movie movie, int lines, int seats) {
        this.id = id;
        this.movie = movie;
        seatsArray = new Seat[lines][seats];
    }

    /**
     * Occupy seat in the current hall
     * @param seat
     * @return True if seat was free and occupied successfully, otherwise returns false
     */
    public boolean occupySeat(Seat seat) {
        if (IsOccupied(seat)) {
            return false;
        }

        seatsArray[seat.getLine()][seat.getSeat()] = seat;
        return true;
    }

    /**
     * Print seats matrix. Free seats marked as '0', occupied seats marked as 'X'
     */
    public void printSeats() {
        System.out.printf("%2s", "");
        for (int i = 0; i < seatsArray[0].length; ++i) {
            System.out.printf("%3d", i + 1);
        }
        System.out.println();

        for (int i = 0; i < seatsArray.length; ++i) {
            System.out.printf("%2d", i + 1);

            for (int j = 0; j < seatsArray[i].length; ++j) {
                char seatMarker = 'O';
                if (seatsArray[i][j] != null) {
                    seatMarker = 'X';
                }

                System.out.printf("%3s", seatMarker);
            }

            System.out.println();
        }

        System.out.println();
    }

    public boolean IsHallFilled() {
        return getOccupiedSeatsNumber() == seatsArray.length + seatsArray[0].length;
    }

    public int getOccupiedSeatsNumber() {
        int result = 0;
        for (int i = 0; i < seatsArray.length; ++i) {
            for (int j = 0; j < seatsArray[i].length; ++j) {
                if (seatsArray[i][j] != null) {
                    ++result;
                }
            }
        }

        return result;
    }

    public boolean IsOccupied(Seat seat) {
        return IsOccupied(seat.getLine(), seat.getSeat());
    }

    public boolean IsOccupied(int line, int seat) {
        return seatsArray[line][seat] != null;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getId() {
        return id;
    }

    public int getLinesNumber() {
        return seatsArray.length;
    }

    public int getSeatsNumber() {
        return seatsArray[0].length;
    }
}
