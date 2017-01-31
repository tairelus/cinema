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
    /**Current price*/
    int price;

    public Hall(int id, Movie movie, int lines, int seats) {
        this.id = id;
        this.movie = movie;
        seatsArray = new Seat[lines][seats];
    }

    /**
     * Occupy seat in the current hall
     * @param seat
     */
    public void occupySeat(Seat seat) {
        seatsArray[seat.getLine() - 1][seat.getSeat() - 1] = seat;
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

    public boolean IsOccupied(int line, int seat) {
        return seatsArray[line - 1][seat - 1] != null;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
